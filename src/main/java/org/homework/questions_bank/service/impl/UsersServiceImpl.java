package org.homework.questions_bank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.homework.questions_bank.entity.Question;
import org.homework.questions_bank.entity.Users;
import org.homework.questions_bank.service.UsersService;
import org.homework.questions_bank.mapper.UsersMapper;
import org.homework.questions_bank.util.LoginRequest;
import org.homework.questions_bank.util.MD5Util;
import org.homework.questions_bank.util.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

import java.util.List;

/**
* @author Administrator
* @description 针对表【users(成员)】的数据库操作Service实现
* @createDate 2024-10-02 15:58:43
*/
@Service
public class UsersServiceImpl implements UsersService{
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public List<Users> list() {
        List<Users> deptList = usersMapper.list();
        return deptList;
    }
    @Override
    public Users validateUser(LoginRequest loginRequest) {
        return usersMapper.login(loginRequest.getUsername(),MD5Util.md5(loginRequest.getPassword().toString()));
    }
    @Override
    public String registerUser(RegisterRequest registerRequest){
        Users existingUser = usersMapper.findByUsername(registerRequest.getMemberName());
        if (existingUser != null) {
            return "Username already exists!";
        }
        String encryptedPassword = MD5Util.md5(registerRequest.getPassword().toString());
        Users user = new Users();
        user.setUid(registerRequest.getUid());
        user.setMemberName(registerRequest.getMemberName());
        user.setPassword(encryptedPassword);
        user.setRole(registerRequest.getRole());
        usersMapper.insertUser(user);
        return "Ok";
    }
    @Override
    public String deleteUser(String memberName)
    {
        Users existingUser = usersMapper.findByUsername(memberName);
        if (existingUser == null) {
            return "Username not exists!";
        }
        usersMapper.deleteUser(memberName);
        return  "OK";

    }
    @Override
    public Users getUers(String username) {
        Users user = usersMapper.findByUsername(username);
        return user;
    }

    @Override
    public Object updatePassword(int userId, String newPassword) {
        Users users= usersMapper.selectById(userId);
        users.setPassword(MD5Util.md5(newPassword));
        usersMapper.updateUser(users);
        return "密码修改成功";
    }


}




