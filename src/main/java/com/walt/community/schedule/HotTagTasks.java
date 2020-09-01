package com.walt.community.schedule;

import com.walt.community.cache.HotTagCache;
import com.walt.community.mapper.QuestionMapper;
import com.walt.community.model.Question;
import com.walt.community.model.QuestionExample;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author: walt1012
 * @date: 2020/8/31
 */
@Component
public class HotTagTasks {

    private static final Logger log = LoggerFactory.getLogger(HotTagTasks.class);

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void hotTagSchedule() {
        int offset = 0;
        int limit = 20;
        log.info("hotTagSchedule start {}", new Date());
        List<Question> list = new ArrayList<>();
        Map<String, Integer> tagPriority = new HashMap<>();
        while (offset == 0 || list.size() == limit) {
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, limit));
            for (Question question : list) {
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer priority = tagPriority.get(tag);
                    if (priority != null) {
                        tagPriority.put(tag, priority + 5 + question.getCommentCount());
                    } else {
                        tagPriority.put(tag, 5 + question.getCommentCount());
                    }
                }
            }
            offset += limit;
        }
        hotTagCache.updateTags(tagPriority);
        log.info("hotTagSchedule end {}", new Date());
    }
}
