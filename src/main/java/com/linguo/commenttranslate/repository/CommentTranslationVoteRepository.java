package com.linguo.commenttranslate.repository;

import com.linguo.commenttranslate.model.CommentTranslationVote;
import org.springframework.data.repository.CrudRepository;

public interface CommentTranslationVoteRepository extends CrudRepository<CommentTranslationVote, Long> {
    CommentTranslationVote findByUserIdAndThreadCommentTranslationId(Long userId, Long translationId);
}
