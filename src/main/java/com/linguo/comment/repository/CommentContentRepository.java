package com.linguo.comment.repository;


import com.linguo.comment.model.CommentContent;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentContentRepository extends PagingAndSortingRepository<CommentContent, Long> {
    CommentContent findFirstByLanguageIdAndCommentId(String languageId, Long commentId);
}
