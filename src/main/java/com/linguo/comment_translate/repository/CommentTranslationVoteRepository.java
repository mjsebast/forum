package com.linguo.comment_translate.repository;

import com.linguo.comment_translate.model.CommentTranslationVote;
import org.springframework.data.repository.CrudRepository;

public interface CommentTranslationVoteRepository extends CrudRepository<CommentTranslationVote, Long> {
    CommentTranslationVote findByUserIdAndCommentTranslationId(Long userId, Long translationId);
}
