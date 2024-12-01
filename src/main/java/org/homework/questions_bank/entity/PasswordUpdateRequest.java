package org.homework.questions_bank.entity;

import lombok.Data;

@Data
public class PasswordUpdateRequest {
    private String newPassword;
    private int userId;
}
