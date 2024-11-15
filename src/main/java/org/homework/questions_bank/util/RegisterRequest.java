package org.homework.questions_bank.util;

import lombok.Data;

@Data
public class RegisterRequest {
    public String getMemberName() {
        return member_name;
    }

    private String member_name;
    private String password;

    private int role;

    // Getters and setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
