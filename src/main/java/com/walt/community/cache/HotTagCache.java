package com.walt.community.cache;

import com.walt.community.dto.HotTagDTO;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author: walt1012
 * @date: 2020/9/1
 */
@Component
public class HotTagCache {

    private List<String> hots;

    public List<String> getHots() {
        return hots;
    }

    public void setHots(List<String> hots) {
        this.hots = hots;
    }

    public void updateTags(Map<String, Integer> tags) {
        int max = 10;
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);

        tags.forEach((name, priority) -> {
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            if (priorityQueue.size() < max) {
                priorityQueue.add(hotTagDTO);
            } else {
                HotTagDTO minHot = priorityQueue.peek();
                if (hotTagDTO.compareTo(minHot) > 0) {
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }
        });

        List<String> sorted = new ArrayList<>(max);
        HotTagDTO poll = priorityQueue.poll();
        while (poll != null) {
            sorted.add(0, poll.getName());
            poll = priorityQueue.poll();
        }
        setHots(sorted);
    }
}
