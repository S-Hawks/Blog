//package dev.faiaz.blog.config;
//
//import dev.faiaz.blog.entities.Role;
//import dev.faiaz.blog.repositories.RoleRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Component
//public class RoleConfig implements CommandLineRunner {
//    private final RoleRepository roleRepository;
//    @Override
//    public void run(String... args) throws Exception {
//        try {
//            Role role = new Role();
//            role.setId(ApiConfig.ROLE_ADMIN);
//            role.setName("ROLE_ADMIN");
//
//            Role role1 = new Role();
//            role1.setId(ApiConfig.ROLE_USER);
//            role1.setName("ROLE_USER");
//
//            Role role2 = new Role();
//            role2.setId(ApiConfig.ROLE_MODERATOR);
//            role2.setName("ROLE_MODERATOR");
//
//            List<Role> roles = List.of(role, role1, role2);
//            List<Role> result = roleRepository.saveAll(roles);
//            result.forEach(r -> {
//                System.out.println(r.getName());
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
