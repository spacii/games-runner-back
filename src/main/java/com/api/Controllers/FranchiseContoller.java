package com.api.Controllers;

import com.api.Entities.Franchise;
import com.api.Services.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class FranchiseContoller {

    @Autowired
    FranchiseService franchiseService;

    @GetMapping("/franchises")
    public CollectionModel<EntityModel<Franchise>> getFranchises(){
        return franchiseService.getFranchises();
    }

    @GetMapping("/franchises/{id}")
    public EntityModel<Franchise> getFranchiseById(@PathVariable Long id){
        return franchiseService.getFranchiseById(id);
    }
}