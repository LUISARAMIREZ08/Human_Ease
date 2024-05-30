package com.example.server.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateApplicationRequestDTO {
    public Long candidateApplicationId;
    @NotNull
    public String applicationDate;
    @NotNull
    public String applicationStatus;
    @NotNull
    public Long userEntity;
    @NotNull
    public Long jobOffer;
}
