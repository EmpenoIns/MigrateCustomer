package com.example.demo.kafka;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.demo.entity.AceMigMaster;
import com.example.demo.service.AceMigService;

@Component
public class KafkaManager {

	private static final Logger logger = LoggerFactory.getLogger(KafkaManager.class);

	@Autowired
	AceMigService aceMigService;

	@KafkaListener(topics = "migForCust", groupId = "group_id_1", containerFactory = "KafkaListnerContainerFactory")
	public void readFromKafkaQueue(@Payload AceMigMaster message, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
		logger.info("Received message with key: {} and value: {}", key, message);

		if (message.getLegacyCusomerId() != null) {
			List<Long> ids = Collections.singletonList(message.getLegacyCusomerId());
			aceMigService.pushCustomerToDatabase(ids);
			logger.info("Stored legecyCustomerId {} into DB with genarated TAR ID.", message.getLegacyCusomerId());
		}else {
			logger.warn("Invalid message received: {}", message);
		}

	}

}
