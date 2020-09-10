package com.learning.rest.webservice.restfulwebservices.versoning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersoningController {
	
	//Approach 1 versoning through URI
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		
		return new PersonV1("Richa Patel");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		
		return new PersonV2(new Name("Richa", "Patel"));
	}
	
	//Approach 2 versoning using request param
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 paramV1() {
		
		return new PersonV1("Richa Patel");
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 paramV2() {
		
		return new PersonV2(new Name("Richa", "Patel"));
	}
	
	//Approach 3 versoning using header param
		@GetMapping(value="/person/header", headers ="X-API-VERSION=1")
		public PersonV1 headerV1() {
			
			return new PersonV1("Richa Patel");
		}
		
		@GetMapping(value="/person/header", headers="X-API-VERSION=2")
		public PersonV2 headerV2() {
			
			return new PersonV2(new Name("Richa", "Patel"));
		}
	
		
		// Approach4 MIME type or ACCEPT Header versioning
		@GetMapping(value="/person/produces", produces ="application/vnd.company.app-v1+json")
		public PersonV1 producesV1() {
			
			return new PersonV1("Richa Patel");
		}
		
		@GetMapping(value="/person/produces", produces="application/vnd.company.app-v2+json")
		public PersonV2 producesV2() {
			
			return new PersonV2(new Name("Richa", "Patel"));
		}	

}
