package com.example.VGSI.Exercise.services;

import com.example.VGSI.Exercise.models.House;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HouseService {
    private List<House> houses;

    public HouseService() {
        this.houses = readHousesFromCSV("src/main/resources/houses.csv");
    }

    private List<House> readHousesFromCSV(String fileName) {
        List<House> houses = new ArrayList<>();
        Path pathToData = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToData,
                StandardCharsets.US_ASCII)) {

            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                House house = new House(attributes[0], attributes[1], attributes[2], attributes[3], attributes[4],
                        attributes[5], attributes[6], attributes[7]);
                houses.add(house);
                line = br.readLine();
            }
        } catch (
                IOException ioe) {
            ioe.printStackTrace();
        }


        // Remove the first line in the data that contains all the names of all the fields, we just want the house data
        if (!houses.isEmpty()) {
            houses.remove(0);
        }
        return houses;
    }

    public List<House> findAllHouses() {
        return this.houses;
    }

    public House findHouseById(String houseId) {
        for (House h: this.houses) {
            if (h.getHouseId().equals(houseId)) {
                return h;
            }
        }
        return null;
    }

    public House updateHouseById(String houseId, House newHouse) {
        House house = new House();
        for(int i = 0; i < this.houses.size(); i++) {
            if(this.houses.get(i).getHouseId().equals(houseId)) {
                // We want to preserve the id and uri because the id is in the put request path variable, not the
                // request body
                String location = this.houses.get(i).getLocation();

                // replace with new house object, but preserve the id and uri
                this.houses.set(i, newHouse);
                this.houses.get(i).setHouseId(houseId);
                this.houses.get(i).setLocation(location);
                house = this.houses.get(i);
            }
        }
        return house;
    }
}