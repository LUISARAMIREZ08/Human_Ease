package com.example.server.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateApplicationUpdateRequestDTO {
    @NotNull
    private Long candidateApplicationId;

    @NotNull
    private String applicationStatus;
}
