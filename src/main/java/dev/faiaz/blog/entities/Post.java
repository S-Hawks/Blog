package dev.faiaz.blog.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "post_title", length = 100, nullable = false)
    private String title;

    @Column(length = 100)
    private String content;

    private String imageName;

    private Date date;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
