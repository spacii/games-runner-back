package com.api.Services;

import com.api.Controllers.FranchiseContoller;
import com.api.Entities.Franchise;
import com.api.Exceptions.NotFoundException;
import com.api.ModelAssemblers.FranchiseModelAssembler;
import com.api.Repositories.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FranchiseService {
    @Autowired
    FranchiseRepository franchiseRepository;

    @Autowired
    FranchiseModelAssembler franchiseModelAssembler;

    public CollectionModel<EntityModel<Franchise>> getFranchises(){
        List<EntityModel<Franchise>> franchises = franchiseRepository.findAll()
                .stream()
                .map(franchiseModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                franchises,
                linkTo(methodOn(FranchiseContoller.class).getFranchises()).withSelfRel()
        );
    }

    public EntityModel<Franchise> getFranchiseById(Long id){
        Franchise franchise = franchiseRepository.findById(id).orElseThrow(() -> new NotFoundException("franchise", id));
        return franchiseModelAssembler.toModel(franchise);
    }

    public EntityModel<Franchise> getFranchiseByGameId(Long id){
        Franchise franchise = franchiseRepository.findByGameGameId(id);
        return franchiseModelAssembler.toModel(franchise);
    }
}
