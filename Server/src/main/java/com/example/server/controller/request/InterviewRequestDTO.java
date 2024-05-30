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
public class InterviewRequestDTO {
    @NotNull
    public Long interviewId;
    @NotNull
    public String interviewDate;
    @NotNull
    public String interviewTime;
    @NotNull
    public String result;
    @NotNull
    public String interviewStatus;
    @NotNull
    public String interviewType;
    @NotNull
    public Long candidateApplicationId;
}
