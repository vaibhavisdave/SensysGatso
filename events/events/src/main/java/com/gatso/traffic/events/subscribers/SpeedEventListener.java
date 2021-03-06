package com.gatso.traffic.events.subscribers;

import com.gatso.traffic.events.SpeedEvent;
import com.gatso.traffic.events.data.TrafficEventEntity;
import com.gatso.traffic.events.data.Violation;
import com.gatso.traffic.events.data.setup.TrafficEventDataSetup;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Subscriber for SpeedEvent.
 *
 * @author vighn
 *
 */
@Component
public class SpeedEventListener implements ApplicationListener<SpeedEvent> {

  private boolean isPaid;
  private static Logger log =  LoggerFactory.getLogger(SpeedEventListener.class);
  
  @Override
  public void onApplicationEvent(SpeedEvent event) {
    TrafficEventEntity entity = event.getEntity();
    log.info("TrafficEventEntity in SpeedEventListener: " + entity.toString());
    if (entity.getSpeed() > entity.getLimit()) {
      Violation voilation = new Violation(new Random().nextLong(1, 10000),
                                            entity.getId(), 50, isPaid, "SPEED");
      TrafficEventDataSetup.addVoilation(voilation);
      entity.setProcessed(true);
      log.info("TrafficEventEntity in SpeedEventListener: " + entity.toString());
      isPaid = !isPaid;
    }

  }

}
