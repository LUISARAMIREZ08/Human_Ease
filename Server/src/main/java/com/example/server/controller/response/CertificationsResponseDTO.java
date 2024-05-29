package com.example.server.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationsResponseDTO {
    public Long certificationId;
    public String certificationName;
    public String certificationEntity;
    public String certificationDateOfIssue;
    public Long employeeId;
}
