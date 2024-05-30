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
public class AffiliationsRequestDTO {
    public Long affiliationId;
    @NotNull
    public Long entityCode;
    @NotNull
    public String entityType;
    @NotNull
    public String entityName;
    @NotNull
    public String affiliationDate;
    @NotNull
    public String affiliationStatus;
    @NotNull
    public Long employeeId;
}
