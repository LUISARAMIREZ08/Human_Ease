package com.example.server.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewUpdateRequestDTO {
    @NotNull
    private String result;

    @NotNull
    private String interviewStatus;

    @NotNull
    private Long candidateApplicationId;
}
