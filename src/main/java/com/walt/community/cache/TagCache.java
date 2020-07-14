package com.walt.community.cache;

import com.walt.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: walt1012
 * @date: 2020/7/13
 */
public class TagCache {

    public static List<TagDTO> get() {
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js", "php", "css", "java", "python", "golang"));
        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring", "django", "flask", "vue"));
        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("nginx", "tomcat", "apache", "unix", "hadoop", "ubuntu", "centos"));
        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql", "redis", "sql", "oracle", "postgresql", "sqlserver"));
        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git", "github", "idea", "vim", "maven", "sublime", "xcode", "eclipse"));

        tagDTOS.add(program);
        tagDTOS.add(framework);
        tagDTOS.add(server);
        tagDTOS.add(db);
        tagDTOS.add(tool);
        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tagDTO -> tagDTO.getTags().stream()).collect(Collectors.toList());
        return Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
    }
}
