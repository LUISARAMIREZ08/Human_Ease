package com.example.server.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationsRequestDTO {
    private String certificationName;
    private String certificationEntity;
    private String certificationDateOfIssue;
    private Long employeeId;
}
