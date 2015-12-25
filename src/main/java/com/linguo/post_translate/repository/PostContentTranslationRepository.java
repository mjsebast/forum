package com.linguo.post_translate.repository;

import com.linguo.post_translate.model.PostContentTranslation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PostContentTranslationRepository extends PagingAndSortingRepository<PostContentTranslation, Long> {
    Page<PostContentTranslation> findByPostId(Long postId, Pageable pageable);
    PostContentTranslation findFirstByPostIdAndLanguageIdOrderByPointsDesc(Long postId, String languageId);
}
