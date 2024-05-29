package com.example.server.services;

import com.example.server.controller.request.NoveltyRequestDTO;
import com.example.server.entity.Concept;
import com.example.server.entity.Employee;
import com.example.server.entity.Novelty;
import com.example.server.entity.Periods;
import com.example.server.entity.enums.NoveltyType;
import com.example.server.repository.IConceptRepository;
import com.example.server.repository.IEmployeeRepository;
import com.example.server.repository.INoveltyRepository;
import com.example.server.repository.IPeriodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoveltyServices {
    @Autowired
    INoveltyRepository noveltyRepository;

    @Autowired
    IEmployeeRepository employeeRepository;

    @Autowired
    IConceptRepository conceptRepository;

    @Autowired
    IPeriodsRepository periodsRepository;

    //This method return all the novelties
    public List<NoveltyRequestDTO> getAllNovelties() {
        return noveltyRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private NoveltyRequestDTO toDTO(Novelty novelty) {
        NoveltyRequestDTO dto = new NoveltyRequestDTO();
        dto.setNoveltyId(novelty.getNoveltyId());
        dto.setDateNovelty(novelty.getDateNovelty().toString());
        dto.setCuttoffDate(novelty.getCuttoffDate().toString());
        dto.setValueConcept(novelty.getValueConcept());
        dto.setCuantityConcept(novelty.getCuantityConcept());
        dto.setNoveltyType(novelty.getNoveltyType().name());
        dto.setEmployeeId(novelty.getEmployeeId().getEmployeeId());
        dto.setConceptId(novelty.getConcept().getConceptId());
        dto.setPeriodId(novelty.getPeriod().getPeriodId());
        return dto;
    }

    //This method return novelty by id
    public NoveltyRequestDTO getNoveltyById(Long id){
        Optional<Novelty> novelty = noveltyRepository.findById(id);
        if (novelty.isPresent()) {
            return toDTO(novelty.get());
        } else {
            return null;
        }
    }

    //This method save a novelty to the database
    public NoveltyRequestDTO saveNovelty(NoveltyRequestDTO request, Novelty novelty) {
        novelty.setDateNovelty(LocalDate.parse(request.getDateNovelty()));
        novelty.setCuttoffDate(LocalDate.parse(request.getCuttoffDate()));
        novelty.setValueConcept(request.getValueConcept());
        novelty.setCuantityConcept(request.getCuantityConcept());
        novelty.setNoveltyType(NoveltyType.valueOf(request.getNoveltyType()));
        novelty.setEmployeeId(findEmployeeById(request.getEmployeeId()));
        novelty.setConcept(findConceptById(request.getConceptId()));
        novelty.setPeriod(findPeriodById(request.getPeriodId()));
        Novelty savedNovelty = noveltyRepository.save(novelty);

        return toDTO(savedNovelty);
    }

    private Periods findPeriodById(Long periodId) {
        Optional<Periods> period = periodsRepository.findById(periodId);
        return period.orElse(null);
    }

    private Concept findConceptById(Long conceptId) {
        Optional<Concept> concept = conceptRepository.findById(conceptId);
        return concept.orElse(null);
    }

    private Employee findEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return employee.orElse(null);
    }


}
