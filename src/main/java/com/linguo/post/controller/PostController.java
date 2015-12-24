package com.linguo.post.controller;

import com.linguo.common.dto.PageableDTO;
import com.linguo.post.dto.PostDTO;
import com.linguo.post.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/posts")
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PostDTO get(@PathVariable Long id){
        return postService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<PostDTO> getPage(PageableDTO pageableDTO){
        return postService.findByPage(pageableDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public PostDTO create(@RequestBody PostDTO dto){
        return postService.create(dto);
    }
}
