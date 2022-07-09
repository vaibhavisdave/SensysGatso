package com.gatso.traffic.events.data;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TrafficEventEntity data object.
 *
 * @author vighn
 *
 */
@Data
@AllArgsConstructor
public class TrafficEventEntity {

  private long id; // ; : // d9bb7458-d5d9-4de7-87f7-7f39edd51d18,
  private Date eventDate; // ; : // 2022-02-09T00; : //25; : //20.529,
  private String eventType; // SPEED,
  private String licensePlate; // ABC-123,
  private long speed; // 87,
  private long limit; // 50,
  private String unity; // km/h,
  private boolean processed; // false

}
