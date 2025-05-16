package io.github.sersul.feedcollector.entity.security;

import io.github.sersul.feedcollector.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@Entity
public class Role extends BaseEntity {

    @Column(name = "name_p", nullable = false, unique = true, length = 50)
     String name;

    @ManyToMany(mappedBy = "roles")
     Set<User> users = new HashSet<>();

    public void setName(String name) {
        if (name != null && !name.startsWith("ROLE_")) {
            this.name = "ROLE_" + name;
        } else {
            this.name = name;
        }
    }

    public String getName() {
        if (name != null && name.startsWith("ROLE_")) {
            return name.substring(5); // удаляет первые 5 символов, то есть "ROLE_"
        }
        return name;
    }

}
