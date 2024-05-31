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
public class PsycotechnicalTestRequestDTO {
    public Long psychotechnicalTestId;
    @NotNull
    public String testType;
    @NotNull
    public String testDate;
    @NotNull
    public String testTime;
    @NotNull
    public String testResult;
    @NotNull
    public Long candidateApplicationId;
}
