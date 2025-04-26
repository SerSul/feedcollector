package io.github.sersul.feedcollector.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review extends BaseEntity {

     @Column(nullable = false)
     String title;

     @Column(nullable = true)
     String description;

     @Column(nullable = false)
     String review;

     String url;

     @Range(min = 0, max = 5, message = "Rating must be between 0 and 5")
     Integer rating;

     boolean processed;

     @Column(nullable = true, name = "user_name")
     String userName = "Anonymous";

     @OneToMany(cascade = CascadeType.ALL)
     List<Comment> comments = new ArrayList<>();

     @OneToMany(cascade = CascadeType.ALL)
     List<Tag> tags;

}
