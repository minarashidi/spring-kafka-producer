package com.rashidi.poc.spring.kafka.producer;

import com.rashidi.poc.spring.kafka.producer.model.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

public interface Bootstrap {
  static void main(String[] args) throws InterruptedException {
    ApplicationContext context = new AnnotationConfigApplicationContext("com.rashidi.poc.spring.kafka.producer");
    EventProducer producer = context.getBean(EventProducer.class);

    Event adminEvent = new Event.EventBuilder(UUID.randomUUID().toString())
      .withTimestamp(Instant.now())
      .withParams(Collections.singletonMap("type", "admin"))
      .build();
    producer.publish(adminEvent);

    Event userEvent = new Event.EventBuilder(UUID.randomUUID().toString())
      .withTimestamp(Instant.now())
      .withParams(Collections.singletonMap("type", "user"))
      .build();
    producer.publish(userEvent);
  }
}
