package com.example.VGSI.Exercise.controllers;

import com.example.VGSI.Exercise.models.House;
import com.example.VGSI.Exercise.services.HouseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class HouseController {
    HouseService service = new HouseService();

    @GetMapping("/api/houses")
    public List<House> findAllHouses() {
        return service.findAllHouses();
    }

    @GetMapping("/api/houses/{hid}")
    public House findHouseById(
            @PathVariable("hid") String houseId) {
        return service.findHouseById(houseId);
    }

    @PutMapping("/api/houses/{hid}")
    public House updateHouseByID(
            @PathVariable("hid") String houseId,
            @RequestBody House newHouse) {
        return service.updateHouseById(houseId, newHouse);
    }
}
