package com.example.server.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferJoin {
    public String jobOfferName;
    public LocalDate jobOfferDate;
    public String name;
    public String lastName;
    public Long cardId;
    public Enum jobOfferStatus;
}
