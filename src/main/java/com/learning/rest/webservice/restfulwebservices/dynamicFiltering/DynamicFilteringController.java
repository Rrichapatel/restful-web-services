package com.learning.rest.webservice.restfulwebservices.dynamicFiltering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


@RestController
public class DynamicFilteringController {
	
	@GetMapping("/dynamicFiltering")
	public MappingJacksonValue retrieveSomeBean() {
		
		DynoSomeBean dynoBean = new DynoSomeBean("value1", "value2", "value3");
		
		// following code for dynamic filtering
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1" , "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("dynoBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(dynoBean);
		mapping.setFilters(filters);
		
		return mapping;
 	}
	
	@GetMapping("/dynamicFilteringList")
	public MappingJacksonValue retrieveSomeBeanList() {
		
		List<DynoSomeBean> dynoBeanList = Arrays.asList(new  DynoSomeBean("11", "22", "33"),
				new DynoSomeBean("111", "222", "333"));
		
		
		// following code for dynamic filtering
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1" , "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("dynoBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(dynoBeanList);
		mapping.setFilters(filters);
				
		return mapping;
	}

}
