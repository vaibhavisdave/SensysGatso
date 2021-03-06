package com.gatso.traffic.events.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatso.traffic.events.data.Violation;
import com.gatso.traffic.events.data.setup.TrafficEventDataSetup;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;





/**
 *class to test ViolationController.
 *
 * @author vighn
 *
 */
@SpringBootTest(classes = {com.gatso.traffic.events.EventsApplication.class})
@AutoConfigureMockMvc
public class ViolationControllerTest {

  @Autowired
  MockMvc mvc;
  
  @Autowired
  private ObjectMapper objectMapper;
  
  @Test
  void testGetFines() throws JsonProcessingException, Exception {
    try (MockedStatic<TrafficEventDataSetup> datasetup = 
        Mockito.mockStatic(TrafficEventDataSetup.class)) {
      datasetup.when(TrafficEventDataSetup::getVoilations).thenReturn(getEvents());
      mvc.perform(get("/fines")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(result -> {
            assertEquals(result.getResponse().getStatus(), 200);
            List<Violation> list = objectMapper.readValue(
                                    result.getResponse().getContentAsString(), 
                                    new TypeReference<List<Violation>>(){});
            assertEquals(2, list.size());
          });
    }
  }
  
  @Test
  void testGetSize() throws JsonProcessingException, Exception {
    try (MockedStatic<TrafficEventDataSetup> datasetup = 
        Mockito.mockStatic(TrafficEventDataSetup.class)) {
      datasetup.when(TrafficEventDataSetup::getVoilations)
           .thenReturn(getEvents());
      mvc.perform(get("/fines/size")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(result -> {
            assertEquals(result.getResponse().getStatus(), 200);
            long size = Long.valueOf(result.getResponse().getContentAsString());
            assertEquals(2, size);
          });
    }
  }
  
  @Test
  void testGetSpeed() throws JsonProcessingException, Exception {
    try (MockedStatic<TrafficEventDataSetup> datasetup = 
        Mockito.mockStatic(TrafficEventDataSetup.class)) {
      datasetup.when(TrafficEventDataSetup::getVoilations).thenReturn(getSpeedEvents());
      mvc.perform(get("/fines/speed")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(result -> {
            assertEquals(result.getResponse().getStatus(), 200);
            List<Violation> list = objectMapper
                                  .readValue(result.getResponse().getContentAsString(), 
                                     new TypeReference<List<Violation>>(){});
            assertEquals(list.size(), 2);
          });
    }
  }
  
  @Test
  void testGetRedLight() throws JsonProcessingException, Exception {
    try (MockedStatic<TrafficEventDataSetup> datasetup = 
                          Mockito.mockStatic(TrafficEventDataSetup.class)) {
      datasetup.when(TrafficEventDataSetup::getVoilations).thenReturn(getRedLightEvents());
      mvc.perform(get("/fines/redLight")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(result -> {
            assertEquals(result.getResponse().getStatus(), 200);
            List<Violation> list = objectMapper
                                   .readValue(result.getResponse().getContentAsString(), 
                                              new TypeReference<List<Violation>>(){});
            assertEquals(list.size(), 2);
          });
    }
  }
  
  @Test
  void testGetSpeedPending() throws JsonProcessingException, Exception {
    try (MockedStatic<TrafficEventDataSetup> datasetup = 
                                          Mockito.mockStatic(TrafficEventDataSetup.class)) {
      datasetup.when(TrafficEventDataSetup::getVoilations).thenReturn(getSpeedEvents());

      mvc.perform(get("/fines/speedPending")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(result -> {
            assertEquals(result.getResponse().getStatus(), 200);
            long fine = Long.valueOf(result.getResponse().getContentAsString());
            assertEquals(fine, 50);
          });
    }
  }
  
  
  @Test
  void testGetRedLightPending() throws JsonProcessingException, Exception {
    try (MockedStatic<TrafficEventDataSetup> datasetup =  
                                            Mockito.mockStatic(TrafficEventDataSetup.class)) {
      datasetup.when(TrafficEventDataSetup::getVoilations).thenReturn(getRedLightEvents());
      mvc.perform(get("/fines/redLightPending")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(result -> {
            assertEquals(result.getResponse().getStatus(), 200);
            long fine = Long.valueOf(result.getResponse().getContentAsString());
            assertEquals(fine, 100);
          });
    }
   
  }

  private List<Violation> getSpeedEvents() {

    return List.of(
        new Violation(new Random().nextLong(1, 10000),
                      new Random().nextLong(1, 10000), 50, true, "SPEED"),
        new Violation(new Random().nextLong(1, 10000),
                      new Random().nextLong(1, 10000), 50, false, "SPEED"));
  }
  
  private List<Violation> getRedLightEvents() {

    return List.of(
        new Violation(new Random().nextLong(1, 10000), 
                      new Random().nextLong(1, 10000), 100, true, "RED_LIGHT"),
        new Violation(new Random().nextLong(1, 10000), 
                      new Random().nextLong(1, 10000), 100, false, "RED_LIGHT"));
  }
  
  private List<Violation> getEvents() {

    return List.of(
        new Violation(new Random().nextLong(1, 10000), 
                      new Random().nextLong(1, 10000), 100, true, "RED_LIGHT"),
        new Violation(new Random().nextLong(1, 10000),
                      new Random().nextLong(1, 10000), 50, true, "SPEED"));
  }
}
