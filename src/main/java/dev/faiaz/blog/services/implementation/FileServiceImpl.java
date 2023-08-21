package dev.faiaz.blog.services.implementation;

import dev.faiaz.blog.services.FileService;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //File name
        String name = file.getOriginalFilename();
        //abc.png

        //random name generate file
        String randomID = UUID.randomUUID().toString();
        String fineName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

        //Fullpath
        String filepath = path + File.separator + fineName1;

        //create folder if not present
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }
//        cropping and compressing image
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage originalImage = ImageIO.read(file.getInputStream());
        int cropWidth = 200;
        int cropHeight = 200;
        float compressionQuality = 0.1f;
        //TODO: This process will compress in default size base on width and height
//        BufferedImage croppedAndCompressImage =Thumbnails.of(originalImage)
//                .sourceRegion(Positions.CENTER, cropWidth, cropHeight)
//                .size(cropWidth, cropHeight)
//                .asBufferedImage();
        //TODO: In this process size will decided based on compressionQuality in that case 0.1f
        //TODO: (Image Magick,Java Advance Imaging(JAI)) these library can also be use
        Thumbnails.of(originalImage)
                        .sourceRegion(Positions.CENTER, cropWidth, cropHeight)
                        .size(cropWidth, cropHeight)
                        .outputQuality(compressionQuality).outputFormat("jpg")
                        .toOutputStream(byteArrayOutputStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        BufferedImage croppedAndCompressImage = ImageIO.read(inputStream);

        try(OutputStream os = new FileOutputStream(filepath)){
            ImageIO.write(croppedAndCompressImage, "jpg", os);
        }


//        Files.copy(file.getInputStream(), Paths.get(filepath));

        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);

        return is;

    }

}
