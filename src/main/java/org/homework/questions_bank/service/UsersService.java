package org.homework.questions_bank.service;

import org.homework.questions_bank.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import org.homework.questions_bank.util.LoginRequest;
import org.homework.questions_bank.util.RegisterRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【users(成员)】的数据库操作Service
* @createDate 2024-10-02 15:58:43
*/
@Service
public interface UsersService  {

    List<Users> list();
    public Users validateUser(LoginRequest loginRequest);
    String registerUser(RegisterRequest registerRequest);

    String deleteUser(String memberName);
    Users getUers(String username);

    Object updatePassword(int userId, String newPassword);
}
