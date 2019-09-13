package com.mackenzie.br.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mackenzie.br.socialmedia.domain.InterestTopicDomain;
import com.mackenzie.br.socialmedia.service.InterestTopicService;

@RestController
public class InterestTopicController {
	
	@Autowired
	InterestTopicService interestTopicService;
	
	//@CrossOrigin(origins = "http://localhost:4200") // Comentar campo quando o angular estiver deployado no heroku
	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getInterestTopics")
	public ResponseEntity<List<InterestTopicDomain>> getInterestTopics() {
		return new ResponseEntity<>(interestTopicService.getInterestTopics(), HttpStatus.OK);
	}
	
}
