package org.homework.questions_bank.mapper;

import org.homework.questions_bank.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UsersMapperTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void testFindByUsernameAndPassword() {
        // 假设数据库中存在一条记录，username 为 "testUser"，password 为 "testPass"

        String username = "admin";
        String password = "admin";

        Users user = usersMapper.login("admin", password);

        // 验证查询结果
        assertNotNull(user, "用户应存在");
        assertEquals(username, user.getMemberName(), "用户名应匹配");
        // 如果你知道用户的 password，也可以添加如下验证
        // assertEquals(password, user.getPassword(), "密码应匹配");
    }
}
