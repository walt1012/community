package com.walt.community.controller;

import com.walt.community.dto.CommentDTO;
import com.walt.community.mapper.CommentMapper;
import com.walt.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: walt1012
 * @date: 2020/7/9
 */
@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @PostMapping(value = "comment")
    public Object post(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        commentMapper.insert(comment);
        Map<Object, Object> map = new HashMap<>();
        map.put("message","成功");
        return map;
    }
}
