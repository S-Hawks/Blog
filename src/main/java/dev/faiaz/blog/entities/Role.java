package dev.faiaz.blog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

public enum Role {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_MODERATOR
}
