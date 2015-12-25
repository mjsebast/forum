package com.linguo.post_translate.repository;

import com.linguo.post_translate.model.PostTitleTranslation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PostTitleTranslationRepository extends PagingAndSortingRepository<PostTitleTranslation, Long> {
    Page<PostTitleTranslation> findByPostId(Long postId, Pageable pageable);
    PostTitleTranslation findFirstByPostIdAndLanguageIdOrderByPointsDesc(Long postId, String languageId);
}
