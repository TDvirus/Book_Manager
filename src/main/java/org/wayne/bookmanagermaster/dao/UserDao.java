package org.wayne.bookmanagermaster.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wayne.bookmanagermaster.model.User;

@Repository
@Mapper
public interface UserDao {

    @Insert("insert into user (user_name, user_email, user_password) values (#{userName},#{userEmail},#{userPassword})")
    void addUser(User user);

    @Select("select * from user where user_id = #{uid}")
    User selectById(int uid);

    @Select("select * from user where user_email = #{email}")
    User selectByEmail(String email);
}
