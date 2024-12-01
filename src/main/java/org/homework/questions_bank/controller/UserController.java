package org.homework.questions_bank.controller;

import lombok.extern.slf4j.Slf4j;
import org.homework.questions_bank.entity.*;
import org.homework.questions_bank.service.UsersService;
import org.homework.questions_bank.util.JwtUtil;
import org.homework.questions_bank.util.LoginRequest;
import org.homework.questions_bank.util.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UsersService usersService;

    @GetMapping("/all")
    public Result getAllUsers() {
        List<Users> usersList=usersService.list();
        return Result.success(usersList); // 获取所有用户
    }
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // 验证用户
        Users user = usersService.validateUser(loginRequest);
        if (user != null) {
            // 用户验证成功，生成 JWT
            String token = jwtUtil.generateToken(user.getUsername());
            Users users=usersService.getUers(user.getUsername());
            // 返回用户信息和 JWT
            Map<String, Object> response = new HashMap<>();
            response.put("user", users);
            response.put("token", token);
            log.info(token);
            return ResponseEntity.ok(response);
        } else {
            // 用户验证失败，返回 401 状态
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestBody TokenRequest tokenRequest) {
        log.info(tokenRequest.getToken());
        boolean isValid = jwtUtil.validateToken(tokenRequest.getToken());
        return ResponseEntity.ok(isValid);
    }
    @PostMapping("/register")
    public Result register(@RequestBody RegisterRequest registerRequest)
    {
        log.info("注册用户");
        String message=usersService.registerUser(registerRequest);
        return Result.success(message);
    }
    @DeleteMapping("/{member_name}")
    public Result delete(@PathVariable String member_name)
    {
        log.info("删除用户");
        return Result.success( usersService.deleteUser(member_name));

    }
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody PasswordUpdateRequest passwordUpdateRequest)
    {
        log.info("修改密码");
        return Result.success(usersService.updatePassword(passwordUpdateRequest.getUserId(),passwordUpdateRequest.getNewPassword()));
    }

}

