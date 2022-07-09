package com.gatso.traffic.events.data.setup;

import com.gatso.traffic.events.data.TrafficEventEntity;
import com.gatso.traffic.events.data.Violation;
import com.gatso.traffic.events.producers.TrafficEventProducer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Utility class to maintain in memory data.
 *
 * @author vighn
 *
 */
public class TrafficEventDataSetup {

  private static Logger log =  LoggerFactory.getLogger(TrafficEventDataSetup.class);
  private static List<TrafficEventEntity> trafficEventList;
  private static List<Violation> voilationList;
 
  
  /**
   * Initialize traffic event and violation data.
   */
  public static void initializeData() {
    try {
      trafficEventList = Collections.synchronizedList(new ArrayList<>());
      voilationList = Collections.synchronizedList(new ArrayList<>());
      Executors.newSingleThreadExecutor().submit(() -> {
        try {
          TrafficEventProducer.startEvents();
        } catch (InterruptedException e) {
          log.error(e.getMessage(), e);
        }
      });
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  public static void recordEventEntity(TrafficEventEntity event) {
    trafficEventList.add(event);
    log.error("trafficEventList.size(): " + trafficEventList.size());
  }

  public static void addVoilation(Violation voilation) {
    voilationList.add(voilation);
    log.error("voilationList.size(): " + voilationList.size());
  }

  public static List<Violation> getVoilations() {
    return voilationList;
  }
}
