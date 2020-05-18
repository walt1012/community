package com.walt.community.service;

import com.walt.community.dto.PaginationDTO;
import com.walt.community.dto.QuestionDTO;
import com.walt.community.mapper.QuestionMapper;
import com.walt.community.mapper.UserMapper;
import com.walt.community.model.Question;
import com.walt.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: walt1012
 * @date: 2020/5/13
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);

        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }

        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        questionList.forEach(a -> {
            User user = userMapper.findUserById(a.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(a, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        });

        paginationDTO.setQuestionDTOList(questionDTOList);
        return paginationDTO;
    }
}
