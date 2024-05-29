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
public class JobOfferRequestDTO {
    @NotNull
    public String jobOfferName;
    @NotNull
    public String jobOfferDescription;
    @NotNull
    public String jobOfferDate;
    @NotNull
    public String jobOfferExpirationDate;
    @NotNull
    public String jobOfferStatus;
    @NotNull
    public Long positionId;
    @NotNull
    public Long employeeId;
}
