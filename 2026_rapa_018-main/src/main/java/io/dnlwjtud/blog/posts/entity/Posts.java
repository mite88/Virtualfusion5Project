package io.dnlwjtud.blog.posts.entity;

import io.dnlwjtud.blog.members.entity.Member;
import io.dnlwjtud.blog.posts.dto.EditPostRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member author;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Builder
    public Posts(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(EditPostRequest request) {

        this.title = request.title();
        this.content = request.content();

        this.updatedAt = LocalDateTime.now();

    }

}
