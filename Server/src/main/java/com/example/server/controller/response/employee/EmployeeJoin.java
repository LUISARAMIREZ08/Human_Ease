package com.example.server.controller.response.employee;

import com.example.server.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeJoin {
    private Long cardId;
    private String name;
    private String lastName;
    private String positionName;
    private Float salaryBase;
    private String phone;
}
