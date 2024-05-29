package com.example.server.services;

import com.example.server.entity.Position;
import com.example.server.entity.Requirements;
import com.example.server.repository.IPositionRepository;
import com.example.server.repository.IRequirementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequirementsServices {
    @Autowired
    IRequirementsRepository requirementsRepository;

    @Autowired
    IPositionRepository positionRepository;

    //method to return all requirements
    public List<Requirements> getAllRequirements() {
        return requirementsRepository.findAll();
    }

    //method to return requirement by id
    public Optional<Requirements> getRequirementById(Long id) {
        return requirementsRepository.findById(id);
    }

    //method to save a requirement to the database
    public Requirements saveRequirement(String descriptionRequirement, Long position){
        Requirements requirement = new Requirements();
        requirement.setDescriptionRequirement(descriptionRequirement);

        Optional<Position> positionOptional = positionRepository.findById(position);
        if(positionOptional.isPresent()){
            requirement.setPosition(positionOptional.get());
            return requirementsRepository.save(requirement);
        }else{
            throw new RuntimeException("Position not found with id " + position);
        }
    }
}
