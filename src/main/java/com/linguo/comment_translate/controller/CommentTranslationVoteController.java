package com.linguo.comment_translate.controller;

import com.linguo.comment_translate.dto.CommentTranslationVoteDTO;
import com.linguo.comment_translate.service.CommentTranslationVoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/comment_translations/{id}/vote")
public class CommentTranslationVoteController {
    @Autowired
    CommentTranslationVoteServiceImpl commentTranslationVoteService;

    @RequestMapping(method = RequestMethod.POST)
    public CommentTranslationVoteDTO create(@PathVariable Long id, @RequestBody CommentTranslationVoteDTO dto){
        return commentTranslationVoteService.create(id, dto);
    }
}
