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
public class NoveltyRequestDTO {
    public Long noveltyId;
    @NotNull
    public String dateNovelty;
    @NotNull
    public String cuttoffDate;
    @NotNull
    public Float valueConcept;
    @NotNull
    public Long cuantityConcept;
    @NotNull
    public String noveltyType;
    @NotNull
    public Long employeeId;
    @NotNull
    public Long conceptId;
    @NotNull
    public Long periodId;
}
