package com.linguo.post.controller;

import com.linguo.common.dto.VoteDTO;
import com.linguo.post.service.PostVoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/posts/{id}/vote")
public class PostVoteController {
    @Autowired
    PostVoteServiceImpl postVoteService;

    @RequestMapping(method = RequestMethod.POST)
    public VoteDTO create(@PathVariable Long id, @RequestBody VoteDTO dto){
        return postVoteService.create(id, dto);
    }
}
