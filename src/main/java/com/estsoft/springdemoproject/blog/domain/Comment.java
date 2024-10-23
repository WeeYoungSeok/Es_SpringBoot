package com.estsoft.springdemoproject.blog.domain;

import com.estsoft.springdemoproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springdemoproject.blog.domain.dto.CommentResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String body;

    @CreatedDate
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;   // created_at

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    public Comment(String body, Article article) {
        this.body = body;
        this.article = article;
    }

    public CommentResponse convert() {
        return new CommentResponse(id, article.getId(), body, createdAt, article.convert());
    }

    public CommentResponse convertToCommentFindById() {
        return new CommentResponse(id, article.getId(), body, createdAt);
    }

    public void update(String body) {
        this.body = body;
    }
}
