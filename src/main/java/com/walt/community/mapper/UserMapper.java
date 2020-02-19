package com.walt.community.mapper;

import com.walt.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @FileName: community
 * @Author: walt1012
 * @Description: TODO
 * @Date: 2020/2/19 8:39 下午
 */
@Mapper
public interface UserMapper {

    /**
     * 插入用户信息
     *
     * @param user
     */
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);

    /**
     * 查询用户信息
     *
     * @param token
     * @return
     */
    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);
}
