package org.homework.questions_bank.mapper;

import org.apache.ibatis.annotations.*;
import org.homework.questions_bank.entity.Users;

import java.util.List;

/**
* @author Administrator
* @description 针对表【users(成员)】的数据库操作Mapper
* @createDate 2024-10-02 15:58:43
* @Entity org.homework.questions_bank.entity.Users
*/
@Mapper
public interface UsersMapper {
    @Select("select * from users")
    List<Users> list(); // 确保方法名称是 selectList
    /*@Select(" SELECT * FROM users\n" +
            "        WHERE member_name = #{username}\n" +
            "          AND password = #{password}")*/
    Users login(@Param("username") String username, @Param("password") String password);
    @Select(" SELECT * FROM users WHERE member_name = #{username} LIMIT 1;")
    Users findByUsername(String username);
    @Insert("INSERT INTO users (member_name, password,role) VALUES (#{memberName}, #{password},#{role});")
    void insertUser(Users user);
    @Delete(" DELETE FROM users WHERE member_name = #{memberName};")
    void deleteUser(String memberName);

    @Select("SELECT * FROM users WHERE uid = #{userId};")
    Users selectById(int userId);
    @Update("UPDATE users SET member_name = #{memberName}, password = #{password}, role = #{role} WHERE uid = #{uid};")
    void updateUser(Users user);
}




