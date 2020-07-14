package com.walt.community.dto;

import java.util.List;

/**
 * @author: walt1012
 * @date: 2020/7/13
 */
public class TagDTO {

    private String categoryName;
    private List<String> tags;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
