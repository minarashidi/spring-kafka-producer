package com.rashidi.poc.spring.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rashidi.poc.spring.kafka.producer.model.Event;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Component
public class EventProducer {

  private static final Logger LOG = LoggerFactory.getLogger(EventProducer.class);

  private KafkaTemplate<String, byte[]> kafkaTemplate;
  private ObjectMapper objectMapper;

  @Autowired
  public EventProducer(KafkaTemplate<String, byte[]> kafkaTemplate, ObjectMapper objectMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
  }

  public void publish(Event event) {
    try {
      byte[] json = objectMapper.writeValueAsBytes(event);
      ListenableFuture<SendResult<String, byte[]>> future = kafkaTemplate.send("event", event.getId(), json);
      RecordMetadata metadata = future.get().getRecordMetadata();
      LOG.info("Event {} published, partition {}, offset {}", event.getId(), metadata.partition(), metadata.offset());
    } catch (JsonProcessingException e) {
      LOG.warn("Could not serialize Event.", e);
    } catch (InterruptedException | ExecutionException e) {
      LOG.warn("Could not publish Event.", e);
    }

  }
}
