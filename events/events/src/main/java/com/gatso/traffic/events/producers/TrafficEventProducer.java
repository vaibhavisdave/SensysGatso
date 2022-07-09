package com.gatso.traffic.events.producers;

import com.gatso.traffic.events.AbstractTrafficEvents;
import com.gatso.traffic.events.RedLightEvent;
import com.gatso.traffic.events.SpeedEvent;
import com.gatso.traffic.events.data.setup.TrafficEventDataSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;





/**
 * Produces traffic event.
 *
 * @author vighn
 *
 */
@Component
public class TrafficEventProducer implements ApplicationEventPublisherAware {
  @Autowired
  private static ApplicationEventPublisher eventPublisher;
  
  /**
   *Pushes the event the list.
   *
   *@throws throws InterruptedException
   */
  public static void startEvents() throws InterruptedException {
    int i = 0;
    boolean alternateFlag = false;
    AbstractTrafficEvents event = null;
    while (i < 50) {
      if (alternateFlag) {
        event = new SpeedEvent(TrafficEventProducer.class, getSpeed(i));
      } else {
        event = new RedLightEvent(TrafficEventProducer.class, getSpeed(i));
      }
      TrafficEventDataSetup.recordEventEntity(event.getEntity());
      eventPublisher.publishEvent(event);
      Thread.sleep(6000L);
      alternateFlag = !alternateFlag;
      i++;
    }
  }

  private static long getSpeed(int i) {
    return (i % 5 == 0 || i % 6 == 0) ? 40 : 60;
  }

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    eventPublisher = applicationEventPublisher;

  }

 
}
