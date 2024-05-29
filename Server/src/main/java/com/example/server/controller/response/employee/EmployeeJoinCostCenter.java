package com.example.server.controller.response.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeJoinCostCenter {
    public String employeeName;
    public String employeeLastName;
    public Long employeeId;
    public Long cardId;
    public Long costCenterId;
}
