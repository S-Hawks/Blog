package dev.faiaz.blog.payloads;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDto {
   private Integer id;

   @NotEmpty
   @Size(min=4, message = "Username must be min of 4 character")
   private String name;

   @Email(message = "Email address is not valid!!")
   private String email;

   @NotEmpty
   @Size(min = 3, max = 10, message = "Password must be minimum of 3 chars and maximum of 10 chars")
   @Pattern(regexp =  "^[a-zA-Z0-9]{6,12}$")
   private String password;

   @NotEmpty
   private String about;
}
