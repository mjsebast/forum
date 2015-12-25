package com.linguo.post_translate.controller;

import com.linguo.common.dto.VoteDTO;
import com.linguo.post_translate.service.PostTitleTranslationVoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/post_title_translations/{id}/vote")
public class PostTitleTranslationVoteController {

    @Autowired
    PostTitleTranslationVoteServiceImpl postTitleTranslationVoteService;

    @RequestMapping(method = RequestMethod.POST)
    public VoteDTO create(@PathVariable Long id, @RequestBody VoteDTO dto){
        return postTitleTranslationVoteService.create(id, dto);
    }

}
