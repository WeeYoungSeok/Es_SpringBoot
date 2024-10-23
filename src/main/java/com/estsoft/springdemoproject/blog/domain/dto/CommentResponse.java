package com.estsoft.springdemoproject.blog.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "댓글 결과")
public class CommentResponse {
    @Schema(description = "댓글 ID", type = "Long")
    private Long id;

    @Schema(description = "댓글 내용", type = "String")
    private String body;

    @Schema(description = "댓글 작성 시간", type = "LocalDataTime")
    private LocalDateTime createdAt;

    @Schema(description = "게시물", type = "ArticleResponse")
    private ArticleResponse article;
}
