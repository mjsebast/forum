package com.linguo.thread.controller;

import com.linguo.common.dto.PageableDTO;
import com.linguo.thread.dto.ThreadDTO;
import com.linguo.thread.service.ThreadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/threads")
public class ThreadController {

    @Autowired
    ThreadServiceImpl threadService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ThreadDTO get(@PathVariable Long id){
        return threadService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<ThreadDTO> getPage(PageableDTO pageableDTO){
        return threadService.findByPage(pageableDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ThreadDTO create(@RequestBody ThreadDTO dto){
        return threadService.create(dto);
    }
}
