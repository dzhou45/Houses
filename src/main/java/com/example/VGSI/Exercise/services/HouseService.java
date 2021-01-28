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

        houses.remove(0);
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

    public Integer updateHouseById(String houseId, House newHouse) {
        int index = -1;
        for(int i = 0; i < this.houses.size(); i++) {
            if(this.houses.get(i).getHouseId().equals(houseId)) {
                this.houses.set(i, newHouse);
                index = i;
            }
        }
        return index;
    }
}