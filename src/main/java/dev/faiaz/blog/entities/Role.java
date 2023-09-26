package dev.faiaz.blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static dev.faiaz.blog.entities.Permission.*;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of(
                    ADMIN_CREATE,
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    MODERATOR_CREATE,
                    MODERATOR_UPDATE,
                    MODERATOR_READ,
                    MODERATOR_DELETE
            )
    ),
    USER(Collections.emptySet()),
    MODERATOR(
            Set.of(
                    MODERATOR_CREATE,
                    MODERATOR_CREATE,
                    MODERATOR_UPDATE,
                    MODERATOR_DELETE
            )
    )
    ;

    @Getter
    private final Set<Permission> permissions;

    //TODO:Using java vararg method of java..
//    private static Set<Permission> createPermission(Permission... permissions){
//        return Set.of(permissions);
//    }

    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities =  getPermissions().stream().map(
                permission -> new SimpleGrantedAuthority(permission.name())).toList();
       authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
