package com.gatso.traffic.events.data.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * runner to read dta on startup.
 *
 * @author vighn
 *
 */
@Component
public class EventRunnerImpl implements ApplicationRunner {

  private static Logger log =  LoggerFactory.getLogger(EventRunnerImpl.class);
  /**
   * Setups  data.
   */
  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.error("Insid public class EventRunnerImpl.run");
    TrafficEventDataSetup.initializeData();
  }

}
