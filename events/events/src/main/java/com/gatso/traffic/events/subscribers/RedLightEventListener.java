package com.gatso.traffic.events.subscribers;

import com.gatso.traffic.events.RedLightEvent;
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
public class RedLightEventListener implements ApplicationListener<RedLightEvent> {

  private boolean isPaid;
  private static Logger log =  LoggerFactory.getLogger(RedLightEventListener.class);
  
  @Override
  public void onApplicationEvent(RedLightEvent event) {
    TrafficEventEntity entity = event.getEntity();
    log.info("TrafficEventEntity in RedLightEventListener: " + entity.toString());
    if (entity.getSpeed() > entity.getLimit()) {
      Violation voilation = new Violation(new Random().nextLong(1, 10000),
                                            entity.getId(), 100, isPaid, "RED_LIGHT");
      TrafficEventDataSetup.addVoilation(voilation);
      entity.setProcessed(true);
      log.info("TrafficEventEntity in RedLightEventListener: " + entity.toString());
      isPaid = !isPaid;
    }

  }

}
