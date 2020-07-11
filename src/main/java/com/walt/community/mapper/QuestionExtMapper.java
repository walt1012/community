package com.walt.community.mapper;

import com.walt.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

public interface QuestionExtMapper {

    int incView(Question record);

    int incCommentCount(Question record);
}