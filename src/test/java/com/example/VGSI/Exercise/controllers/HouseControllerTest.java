package com.example.VGSI.Exercise.controllers;

import com.example.VGSI.Exercise.models.House;
import com.example.VGSI.Exercise.services.HouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = HouseController.class)
public class HouseControllerTest {
    private List<House> houses;

    // initially reads the list of houses from the given data file, would use a database in actual application
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

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HouseService houseService;

    @Test
    public void incorrectGetUrl() throws Exception {
        Mockito.when(houseService.findAllHouses()).thenReturn(this.houses);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/house").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void incorrectPutUrl() throws Exception {
        Mockito.when(houseService.findAllHouses()).thenReturn(this.houses);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/houses").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void putRequestNoBody() throws Exception {
        Mockito.when(houseService.findAllHouses()).thenReturn(this.houses);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/houses/1").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void findHouseIdDoesNotExist() throws Exception {
        Mockito.when(houseService.findAllHouses()).thenReturn(this.houses);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/houses/11").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void findAllHouses() throws Exception {
        Mockito.when(houseService.findAllHouses()).thenReturn(this.houses);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/houses").accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void findHouseById() throws Exception {
        Mockito.when(houseService.findAllHouses()).thenReturn(this.houses);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/houses/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expected = "{\n" +
                "    \"ownerfirstName\": \" Jack\",\n" +
                "    \"ownerlastName\": \" Smith\",\n" +
                "    \"street\": \" South St\",\n" +
                "    \"city\": \" Hudson\",\n" +
                "    \"state\": \" MA\",\n" +
                "    \"zip\": \" 01749\",\n" +
                "    \"propertyType\": \" Single Family\",\n" +
                "    \"location\": \"http://localhost:8080/api/houses/1\"\n" +
                "}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void updateHouseById() throws Exception {
        String exampleHouse = "{\n" +
                "    \"ownerfirstName\": \" John\",\n" +
                "    \"ownerlastName\": \" Baker\",\n" +
                "    \"street\": \" Broad St\",\n" +
                "    \"city\": \" Hudson\",\n" +
                "    \"state\": \" MA\",\n" +
                "    \"zip\": \" 01749\",\n" +
                "    \"propertyType\": \" Multi Family\",\n" +
                "    \"location\": \"http://localhost:8080/api/houses/2\"\n" +
                "}";

        Mockito.when(houseService.findAllHouses()).thenReturn(this.houses);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/houses/2").accept(MediaType.APPLICATION_JSON)
                .content(exampleHouse).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expected = "{\n" +
                "    \"ownerfirstName\": \" John\",\n" +
                "    \"ownerlastName\": \" Baker\",\n" +
                "    \"street\": \" Broad St\",\n" +
                "    \"city\": \" Hudson\",\n" +
                "    \"state\": \" MA\",\n" +
                "    \"zip\": \" 01749\",\n" +
                "    \"propertyType\": \" Multi Family\",\n" +
                "    \"location\": \"http://localhost:8080/api/houses/2\"\n" +
                "}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}
