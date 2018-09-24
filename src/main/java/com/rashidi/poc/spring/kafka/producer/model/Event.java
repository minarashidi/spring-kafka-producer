package com.rashidi.poc.spring.kafka.producer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;

public class Event {

  @JsonProperty("id")
  private final String id;

  @JsonProperty("timestamp")
  private final Instant timestamp;

  @JsonProperty("params")
  private final Map<String, Object> params;

  private Event(EventBuilder builder) {
    this.id = builder.id;
    this.timestamp = builder.timestamp;
    this.params = builder.params;
  }

  public String getId() {
    return id;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  @Override
  public String toString() {
    return "Event{" +
      "id='" + id + '\'' +
      ", timestamp=" + timestamp +
      ", params=" + params +
      '}';
  }

  public static class EventBuilder {

    private final String id;

    private Instant timestamp;

    private Map<String, Object> params = Collections.emptyMap();

    public EventBuilder(String id) {
      this.id = id;
    }

    public EventBuilder withTimestamp(Instant timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public EventBuilder withParams(Map<String, Object> params) {
      this.params = Collections.unmodifiableMap(params);
      return this;
    }

    public Event build() {
      return new Event(this);
    }
  }
}
