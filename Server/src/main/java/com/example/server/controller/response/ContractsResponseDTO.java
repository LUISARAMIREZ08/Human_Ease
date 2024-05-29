package com.example.server.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractsResponseDTO {
    private Long contractId;
    private String contractStartDate;
    private String contractEndDate;
    private String contractPath;
    private String contractType;
    private Long employeeId;
}
