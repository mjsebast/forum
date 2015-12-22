package com.linguo.comment.controller;

import com.linguo.comment.dto.CommentDTO;
import com.linguo.common.dto.PageableDTO;
import com.linguo.comment.dto.CommentFilterDTO;
import com.linguo.comment.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/comments")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommentDTO get(@PathVariable Long id){
        return commentService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<CommentDTO> getPage(CommentFilterDTO filter, PageableDTO pageableDTO){
        return commentService.findByPage(filter, pageableDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public CommentDTO create(@RequestBody CommentDTO dto){
        return commentService.create(dto);
    }

}
