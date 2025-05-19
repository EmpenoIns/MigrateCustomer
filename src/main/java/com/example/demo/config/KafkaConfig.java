package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.demo.entity.AceMigMaster;


@Configuration
@EnableKafka
public class KafkaConfig {
	
	@Bean
	public ConsumerFactory<String, AceMigMaster> consumerFactory() {
	    Map<String, Object> config = new HashMap<>();
	    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	    config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
	    config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.example.demo.entity.AceMigMaster");
	    return new DefaultKafkaConsumerFactory<>(config);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, AceMigMaster> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, AceMigMaster> factory = 
	        new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());
	    return factory;
	}


}
