package com.example.server.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {
    @NotNull
    public Long employeeId;
    @NotNull
    public Long CardId;
    @NotNull
    public Long accountNumberEmployee;
    @NotNull
    public Float salaryBase;
    @NotNull
    public String statusEmployee;
    @NotNull
    public Long costCenterId;
    @NotNull
    public Long positionId;
}
