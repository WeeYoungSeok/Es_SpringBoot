package com.estsoft.springdemoproject.blog.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "댓글 결과")
public class CommentResponse {
    @Schema(description = "댓글 ID", type = "Long")
    private Long commentId;
    private Long articleId;

    @Schema(description = "댓글 내용", type = "String")
    private String body;

    @Schema(description = "댓글 작성 시간", type = "LocalDataTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Schema(description = "게시물", type = "ArticleResponse")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArticleResponse article;

    public CommentResponse(Long commentId, Long articleId, String body, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.articleId = articleId;
        this.body = body;
        this.createdAt = createdAt;
    }
}
