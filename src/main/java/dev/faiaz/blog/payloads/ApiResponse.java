package dev.faiaz.blog.payloads;

import lombok.*;

//@Getter
//@Setter
//@AllArgsConstructor
@Data
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private boolean success;

}
