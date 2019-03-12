package com.rest.demo.restdemo.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.demo.restdemo.dto.SomeDTO;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filter1")
    public MappingJacksonValue dynamicFilter1() {
        List<SomeDTO> dtos = Arrays.asList(
                new SomeDTO(1, "field1", "field2", "field3", "field4"),
                new SomeDTO(1, "field1", "field2", "field3", "field4"));

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("dynamicFilter",simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dtos);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("/filter2")
    public MappingJacksonValue dynamicFilter2() {
        List<SomeDTO> dtos = Arrays.asList(
                new SomeDTO(1, "field1", "field2", "field3", "field4"),
                new SomeDTO(1, "field1", "field2", "field3", "field4"));

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field4");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("dynamicFilter",simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dtos);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }
}
