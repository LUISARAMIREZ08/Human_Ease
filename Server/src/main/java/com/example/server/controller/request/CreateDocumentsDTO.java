package com.example.server.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDocumentsDTO {
    @NotBlank
    private String documentName;
    @NotBlank
    private String documentPath;
    @NotNull
    private Long  cardId;
}
