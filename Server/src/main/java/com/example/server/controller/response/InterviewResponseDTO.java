package com.example.server.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewResponseDTO {
    private Long interviewId;
    private String interviewDate;
    private String interviewTime;
    private String result;
    private String link;
    private String interviewStatus;
    private String interviewType;
    private Long candidateApplicationId;
}
