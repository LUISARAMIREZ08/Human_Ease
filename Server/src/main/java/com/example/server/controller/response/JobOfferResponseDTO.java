package com.example.server.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferResponseDTO {
    public Long jobOfferId;
    public String jobOfferName;
    public String jobOfferDescription;
    public String jobOfferDate;
    public String jobOfferExpirationDate;
    public String jobOfferStatus;
    public Long positionId;
    public Long employeeId;
}
