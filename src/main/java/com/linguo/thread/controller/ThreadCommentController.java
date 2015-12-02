package com.linguo.thread.controller;

import com.linguo.common.dto.PageableDTO;
import com.linguo.thread.dto.ThreadCommentDTO;
import com.linguo.thread.dto.ThreadCommentFilterDTO;
import com.linguo.thread.service.ThreadCommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/thread_comments")
public class ThreadCommentController {

    @Autowired
    ThreadCommentServiceImpl threadCommentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ThreadCommentDTO get(@PathVariable Long id){
        return threadCommentService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Page<ThreadCommentDTO> getPage(ThreadCommentFilterDTO filter, PageableDTO pageableDTO){
        return threadCommentService.findByPage(filter, pageableDTO);
    }

}
