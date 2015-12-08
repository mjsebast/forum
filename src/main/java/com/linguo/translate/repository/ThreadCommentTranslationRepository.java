package com.linguo.translate.repository;


import com.linguo.translate.model.ThreadCommentTranslation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ThreadCommentTranslationRepository extends PagingAndSortingRepository<ThreadCommentTranslation, Long> {
    Page<ThreadCommentTranslation> findByThreadCommentId(Long commentId, Pageable pageable);
}