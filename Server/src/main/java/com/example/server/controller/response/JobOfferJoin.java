package com.example.server.controller.response;

import com.example.server.entity.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.EnumSet;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferJoin {
    public String name;
    public String lastName;
    public Long cardId;
    public LocalDate applicationDate;
    public ApplicationStatus applicationStatus;
}
