package com.gatso.traffic.events.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Violation data object.
 *
 * @author vighn
 *
 */
@Data
@AllArgsConstructor
public class Violation {

  private long id; // b11e87f0-89ef-11ec-a8a3-0242ac120002
  private long eventId; // d9bb7458-d5d9-4de7-87f7-7f39edd51d18
  private long fine; // 50
  private boolean paid; // false
  private String type; // false
}
