package com.api.ModelAssemblers;

import com.api.Controllers.FranchiseContoller;
import com.api.Entities.Franchise;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FranchiseModelAssembler implements RepresentationModelAssembler<Franchise, EntityModel<Franchise>> {
    @Override
    public EntityModel<Franchise> toModel(Franchise franchise) {

        return EntityModel.of(
                franchise,
                linkTo(methodOn(FranchiseContoller.class).getFranchiseById(franchise.getFranchiseId())).withSelfRel(),
                linkTo(methodOn(FranchiseContoller.class).getFranchises()).withRel("franchises")
        );
    }
}
