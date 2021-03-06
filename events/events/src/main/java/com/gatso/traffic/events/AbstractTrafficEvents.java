package com.gatso.traffic.events;

import com.gatso.traffic.events.data.TrafficEventEntity;
import org.springframework.context.ApplicationEvent;

/**
 * Abstract Traffic Events.
 *
 * @author vighn
 *
 */
public abstract class AbstractTrafficEvents  extends ApplicationEvent {

  public AbstractTrafficEvents(Object source) {
    super(source);
  }

  public abstract TrafficEventEntity getEntity();
}
