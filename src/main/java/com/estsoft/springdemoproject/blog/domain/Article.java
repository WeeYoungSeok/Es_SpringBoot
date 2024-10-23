package com.estsoft.springdemoproject.blog.domain;

import com.estsoft.springdemoproject.blog.domain.dto.ArticleResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;   // created_at

    @LastModifiedDate
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();

    // 생성자
    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse convert() {
        return new ArticleResponse(id, title, content, createdAt, updatedAt);
    }

    public ArticleResponse convertWithComments() {
        return new ArticleResponse(id, title, content, createdAt, updatedAt, comments.stream().map(Comment::convertToCommentFindById).toList());
    }

    public void update(String title, String content) {
//        if (!title.isBlank()) { this.title = title; }
//        if (!content.isBlank()) { this.content = content; }
        this.title = title;
        this.content = content;
    }
}
