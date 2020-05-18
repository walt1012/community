package com.walt.community.mapper;

import com.walt.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author: walt1012
 * @date: 2020/2/19 8:39 下午
 */
@Mapper
public interface UserMapper {

    /**
     * 插入用户信息
     *
     * @param user
     */
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insertUser(User user);

    /**
     * 查询用户信息
     *
     * @param token
     * @return
     */
    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findUserById(@Param("id") Integer id);
}
