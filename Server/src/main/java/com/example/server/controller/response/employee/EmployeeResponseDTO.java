package com.example.server.controller.response.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {
    public Long employeeId;
    public Long CardId;
    public Long accountNumberEmployee;
    public Float salaryBase;
    public String statusEmployee;
    public Long costCenterId;
    public Long positionId;
}
