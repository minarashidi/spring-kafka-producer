package com.rashidi.poc.spring.kafka.producer;

import com.rashidi.poc.spring.kafka.producer.model.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public interface Bootstrap {
  static void main(String[] args) throws InterruptedException {
    ApplicationContext context = new AnnotationConfigApplicationContext("com.rashidi.poc.spring.kafka.producer");
    EventProducer producer = context.getBean(EventProducer.class);

    Event event = new Event.EventBuilder(UUID.randomUUID().toString())
      .withTimestamp(Instant.now())
      .withParams(Collections.singletonMap("action", "start"))
      .build();

    producer.publish(event);
    TimeUnit.SECONDS.sleep(2);
  }
}
