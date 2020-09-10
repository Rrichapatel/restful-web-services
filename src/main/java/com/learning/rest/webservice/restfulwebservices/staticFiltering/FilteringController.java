package com.learning.rest.webservice.restfulwebservices.staticFiltering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		
		return new SomeBean("value1", "value2", "value3");
	}
	
	@GetMapping("/filteringList")
	public List<SomeBean> retrieveSomeBeanList() {
		
		return Arrays.asList(new  SomeBean("11", "22", "33"),
				new SomeBean("111", "222", "333"));
	}
	

}
