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
public class RecordUserRequestDTO {
    public Long recordId;
    @NotNull
    public String recordType;
    @NotNull
    public String recordResult;
    @NotNull
    public String recordDateVerified;
    @NotNull
    public Long candidateApplicationId;
}

