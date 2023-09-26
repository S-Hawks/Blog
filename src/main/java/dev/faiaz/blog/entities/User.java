package dev.faiaz.blog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name= "user_name", nullable = false, length = 100)
    private String name;

    private String email;

    private String password;

    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    //TODO:This process is alternative if I am not using @ElementCollection annotation-> @ElementCollection act as ManyToMany
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role",
//            joinColumns = { @JoinColumn(name = "users", referencedColumnName = "id") },
//            inverseJoinColumns = { @JoinColumn(name = "role", referencedColumnName = "id") })
    //TODO:Not initialize this method because I have set of permission in my Role enum. If role enum only contain enum then this code is applicable
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    private Set<Role> roles = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role role;


}
