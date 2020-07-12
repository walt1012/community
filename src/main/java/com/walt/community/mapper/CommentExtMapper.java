package com.walt.community.mapper;

import com.walt.community.model.Comment;

public interface CommentExtMapper {

    int incCommentCount(Comment comment);
}