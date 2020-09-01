package com.walt.community.dto;

/**
 * @author: walt1012
 * @date: 2020/9/1
 */
public class HotTagDTO implements Comparable {
    private String name;
    private Integer priority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Object o) {
        return this.priority - ((HotTagDTO) o).getPriority();
    }
}
