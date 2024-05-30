package com.example.server.controller.response.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAdmin {
    private Long employeeId;
    private Long cardId;
    private String namePosition;
    private String employeeName;
    private String employeeLastName;
}