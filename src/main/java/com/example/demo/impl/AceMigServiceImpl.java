package com.example.demo.impl;

import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AceMigMaster;
import com.example.demo.repo.AceMigMasterRepository;
import com.example.demo.service.AceMigService;

@Service
public class AceMigServiceImpl implements AceMigService {

	private static final Logger logger = LoggerFactory.getLogger(AceMigServiceImpl.class);
	
	public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	public static final int MAX_LENGTH = 30;
	
	@Autowired
	AceMigMasterRepository aceMigMasterRepository;

	@Override
	public void pushCustomerToDatabase(List<Long> list) {
		List<Long> existingId = aceMigMasterRepository.findAll()
				.stream().map(AceMigMaster::getLegacyCusomerId).collect(Collectors.toList());

		for(Long id : list) {
			if(!existingId.contains(id)) {
				AceMigMaster newEntry = new AceMigMaster();
				Date date = Date.valueOf(LocalDate.now());
				newEntry.setLegacyCusomerId(id);
				newEntry.setTargetCustomerId(generateRandomString(MAX_LENGTH));
				newEntry.setCreateDate(date);
				newEntry.setProcIndicator("IN");
				newEntry.setProcDescription("Customer Intilized");
				newEntry.setExecutionSequence(Thread.currentThread().getName());
				aceMigMasterRepository.save(newEntry);
				logger.info("Inserted legecyCustomerId {} with TAR Id {} into DB.", id, newEntry.getLegacyCusomerId());
			}else {
				logger.info("legecyCustomerId {} already exists, skipping inserted.", id);
			}
		}
	}
	
	public String generateRandomString(int length) {
		SecureRandom random = new SecureRandom();
		StringBuilder builder = new StringBuilder(length);
		for(int i = 0; i < 30; i++) {
			builder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
		}
		return builder.toString();
	}
	
	
	
	
	

}
