package com.estsoft.springdemoproject.blog.service;

import com.estsoft.springdemoproject.blog.domain.Comment;
import com.estsoft.springdemoproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springdemoproject.blog.domain.Article;
import com.estsoft.springdemoproject.blog.domain.dto.AddCommentRequest;
import com.estsoft.springdemoproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springdemoproject.blog.repository.BlogRepository;
import com.estsoft.springdemoproject.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {
    private final BlogRepository repository;
    private final CommentRepository commentRepository;

    public BlogService(BlogRepository repository, CommentRepository commentRepository) {
        this.repository = repository;
        this.commentRepository = commentRepository;
    }

    // blog 게시글 저장
    public Article saveArticle(AddArticleRequest request) {
        return repository.save(request.toEntity());
    }

    // blog 게시글 전체 조회
    public List<Article> findAll() {
        return repository.findAll();
    }

    // blog 게시글 단건 조회 id(PK) 1건
    public Article findBy(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found id: " + id));
    }

    // blog 게시글 삭제 (id)
    public void deleteBy(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = findBy(id);       // 수정하고싶은 article객체 가져오기
        article.update(request.getTitle(), request.getContent());
        return article;
    }

    // 댓글 작성
    public Comment saveComment(Long articleId, AddCommentRequest request) {
        Article article = repository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("not found id: " + articleId));
        Comment comment = Comment.builder()
                .body(request.getBody())
                .article(article)
                .build();
        return commentRepository.save(comment);
    }

    // 댓글 조회
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("not found id: " + commentId)
        );
    }

    // 댓글 수정
    public Comment update(Long commentId, AddCommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("not found id: " + commentId)
        );

        comment.update(request.getBody());
        return commentRepository.save(comment);
    }
}
