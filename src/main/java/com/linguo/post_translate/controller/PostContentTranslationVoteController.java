package com.linguo.post_translate.controller;


import com.linguo.common.dto.VoteDTO;
import com.linguo.post_translate.service.PostContentTranslationVoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/post_content_translations/{id}/vote")
public class PostContentTranslationVoteController {
    @Autowired
    PostContentTranslationVoteServiceImpl postContentTranslationVoteService;

    @RequestMapping(method = RequestMethod.POST)
    public VoteDTO create(@PathVariable Long id, @RequestBody VoteDTO dto){
        return postContentTranslationVoteService.create(id, dto);
    }
}
