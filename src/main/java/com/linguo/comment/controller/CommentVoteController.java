package com.linguo.comment.controller;

import com.linguo.comment.dto.CommentVoteDTO;
import com.linguo.comment.service.CommentVoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/comments/{id}/vote")
public class CommentVoteController {
    @Autowired
    CommentVoteServiceImpl commentVoteService;

    @RequestMapping(method = RequestMethod.POST)
    public CommentVoteDTO create(@PathVariable Long id, @RequestBody CommentVoteDTO dto){
        return commentVoteService.create(id, dto);
    }

}
