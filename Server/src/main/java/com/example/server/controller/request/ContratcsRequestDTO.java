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
public class ContratcsRequestDTO {
    @NotNull
    public String contractStartDate;
    @NotNull
    public String contractEndDate;
    public String contractPath;
    @NotNull
    public String contractType;
    @NotNull
    public Long employeeId;
}
