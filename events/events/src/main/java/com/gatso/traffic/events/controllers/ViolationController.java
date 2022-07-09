package com.gatso.traffic.events.controllers;

import com.gatso.traffic.events.data.Violation;
import com.gatso.traffic.events.data.setup.TrafficEventDataSetup;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Rest api to fetch Violation.
 *
 * @author vighn
 *
 */
@RestController
@RequestMapping("/fines")
public class ViolationController {

  @ApiOperation(value = "Fetches all fines.", response = List.class)
  @GetMapping
  public List<Violation> getFines() {
    return TrafficEventDataSetup.getVoilations();
  }
  
  @ApiOperation(value = "Fetches number of fines.", response = List.class)
  @GetMapping(path = "/size")
  public int getsize() {
    return TrafficEventDataSetup.getVoilations().size();
  }
  
  /**
   *Fetches all fines for speed event.
   *
   * @return List of Violation
   */
  @ApiOperation(value = "Fetches all fines for speed event.", response = List.class)
  @GetMapping(path = "/speed")
  public List<Violation> getSpeed() {
    return TrafficEventDataSetup.getVoilations()
                                .stream()
                                .filter(t -> "SPEED".equals(t.getType()))
                                .toList();
  }
  
  /**
   *Fetches all fines for Red Light event.
   *
   * @return List of Violation
   */
  @ApiOperation(value = "Fetches all fines for Red Light event.", response = List.class)
  @GetMapping(path = "/redLight")
  public List<Violation> getRedLight() {
    return TrafficEventDataSetup.getVoilations()
                                .stream()
                                .filter(t -> "RED_LIGHT".equals(t.getType()))
                                .toList();
  }
  
  /**
   *Fetches pending speed event fine.
   *
   * @return long
   */
  @ApiOperation(value = "Fetches pending speed event fine.", response = List.class)
  @GetMapping(path = "/speedPending")
  public long getSpeedPending() {
    return TrafficEventDataSetup.getVoilations()
                                .stream()
                                .filter(t -> "SPEED".equals(t.getType()) && !t.isPaid())
                                .map(v -> v.getFine())
                                .reduce(0L, (a, b) -> a + b);
  }
  
  /**
   *Fetches pending for Red Light event fine.
   *
   * @return long
   */
  @ApiOperation(value = "Fetches pending for Red Light event fine.", response = List.class)
  @GetMapping(path = "/redLightPending")
  public long getRedLightPending() {
    return TrafficEventDataSetup.getVoilations()
                                .stream()
                                .filter(t -> "RED_LIGHT".equals(t.getType()) && !t.isPaid())
                                .map(v -> v.getFine())
                                .reduce(0L, (a, b) -> a + b);
  }
}
