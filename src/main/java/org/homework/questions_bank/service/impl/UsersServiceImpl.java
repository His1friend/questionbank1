package org.homework.questions_bank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.homework.questions_bank.entity.Question;
import org.homework.questions_bank.entity.Users;
import org.homework.questions_bank.service.UsersService;
import org.homework.questions_bank.mapper.UsersMapper;
import org.homework.questions_bank.util.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;
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
        return usersMapper.login(loginRequest.getUsername(), loginRequest.getPassword());
    }


}




