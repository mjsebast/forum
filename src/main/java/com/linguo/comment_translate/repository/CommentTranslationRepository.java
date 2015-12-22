package com.linguo.comment_translate.repository;


import com.linguo.comment_translate.model.CommentTranslation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentTranslationRepository extends PagingAndSortingRepository<CommentTranslation, Long> {
    Page<CommentTranslation> findByCommentId(Long commentId, Pageable pageable);
    CommentTranslation findFirstByCommentIdAndLanguageIdOrderByPointsDesc(Long commentId, String languageId);
}
