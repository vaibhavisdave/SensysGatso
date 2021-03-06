package com.gatso.traffic.events;

import com.gatso.traffic.events.data.TrafficEventEntity;
import java.util.Date;
import java.util.Random;

/**
 * Speed traffic Event.
 *
 * @author vighn
 *
 */
public class SpeedEvent extends AbstractTrafficEvents {
  
  /**
   * UID.
   */
  private static final long serialVersionUID = 1L;
  private TrafficEventEntity speedEventEntity;

  /**
   * Constructor.
   *
   * @param source Object
   */
  public SpeedEvent(Object source, long speed) {
    super(source);
    this.speedEventEntity = new TrafficEventEntity(new Random().nextLong(1, 10000),
        new Date(), "SPEED",
        "ABC-123", speed, 50L, "km/h", false);;
  }

  @Override
  public TrafficEventEntity getEntity() {
    return speedEventEntity;
  }
  
}
