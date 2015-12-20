package com.linguo.commenttranslate.controller;

import com.linguo.commenttranslate.dto.CommentTranslationVoteDTO;
import com.linguo.commenttranslate.service.CommentTranslationVoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/thread_comment_translations/{id}/vote")
public class CommentTranslationVoteController {
    @Autowired
    CommentTranslationVoteServiceImpl commentTranslationVoteService;

    @RequestMapping(method = RequestMethod.POST)
    public CommentTranslationVoteDTO create(@PathVariable Long id, @RequestBody CommentTranslationVoteDTO dto){
        return commentTranslationVoteService.create(id, dto);
    }

}
