package com.library.project.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.Member.entity.Member;

@Service
public class ProjectService {
	
	private static String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	private final RestTemplate restTemplate;
	
	public ProjectService() {
		this.restTemplate= new RestTemplate();
	}

	public List<Member> retrieveData(){
	    String projectUrl = "http://localhost:8081/api/member";
	    ResponseEntity<Member[]> response = restTemplate.getForEntity(projectUrl, Member[].class);
	    Member[] membersArray = response.getBody();
	    return Arrays.asList(membersArray);
	}
	public String getMemberName(int memberId) {
	    String url = "http://localhost:8081/api/member/membername/"+memberId;
	    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
	    name = response.getBody();
	    return name;
	}

	
	
//	public List<String> retrieveData() {
//	    String projectUrl = "http://localhost:8081/api/member";
//	    ResponseEntity<Member[]> response = restTemplate.getForEntity(projectUrl, Member[].class);
//	    Member[] membersArray = response.getBody();
//
//	    // Extract first names from the Member objects and collect them in a list
//	    List<String> firstNames = Arrays.stream(membersArray)
//	            .map(Member::getMemberName)
//	            .collect(Collectors.toList());
//
//	    return firstNames;
//	}




}
