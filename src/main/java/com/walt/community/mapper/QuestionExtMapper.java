package com.walt.community.mapper;

import com.walt.community.model.Comment;
import com.walt.community.model.Question;
import com.walt.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);

    int incCommentCount(Question record);
}