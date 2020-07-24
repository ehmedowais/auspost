package com.auspost.codingtest.controller;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.auspost.codingtest.entity.Locations;
import com.auspost.codingtest.service.LocationService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = LocationController.class, secure = false)
public class LocationControllerTest {
	
		
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private LocationService locationService;
	    

	    @Test
	    public void testGetAllLocations() throws Exception {
	    	List<Locations> locations = new ArrayList<>();
	    	locations.add(new Locations(1,"HILSIDE","3037"));
	    	locations.add(new Locations(2,"DELAHEY","3037"));
	    	Mockito.when(locationService.getAllLocations()).thenReturn(locations);
	    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auspost/locations").
	    			accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
	        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());

	    }
	    @Test
	    public void testGetLocationBySuburb()throws Exception{
	    	List<Locations> locations = new ArrayList<>();
	    	locations.add(new Locations(1,"HILSIDE","3037"));
	    	Mockito.when(locationService.getLocationByDetail("HILSIDE", null)).thenReturn(locations);
	    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auspost/suburbs/HILSIDE").
	    			accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
	        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
	        String expected = "[{\"locationId\":1,\"suburb\":\"HILSIDE\",\"postcode\":\"3037\"}]";
	        
	        JSONAssert.assertEquals(expected, response.getContentAsString(), false);
		

	    }
	    @Test
	    public void testGetLocationByPostcode()throws Exception{
	    	List<Locations> locations = new ArrayList<>();
	    	locations.add(new Locations(1,"HILSIDE","3037"));
	    	Mockito.when(locationService.getLocationByDetail(null,"3037")).thenReturn(locations);
	    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auspost/postcodes/3037").
	    			accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
	        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
	        String expected = "[{\"locationId\":1,\"suburb\":\"HILSIDE\",\"postcode\":\"3037\"}]";
	        JSONAssert.assertEquals(expected, response.getContentAsString(), false);
		

	    }
	    @Test
	    public void testCreateLocation()throws Exception{
	    	Locations location = new Locations();
	    	location.setPostcode("3123");
	    	location.setSuburb("TARNIET");
	    	Mockito.when(locationService.addLocation(Mockito.any(Locations.class))).thenReturn(true);
	    	RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auspost/location")
	    			.accept(MediaType.APPLICATION_JSON)
	    			.contentType(MediaType.APPLICATION_JSON)
	    			.content("{\"postcode\":\"3123\",\"suburb\":\"DonCaster\"}");
	    	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    	MockHttpServletResponse response = result.getResponse();
	    	Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	    	
	    }
}
