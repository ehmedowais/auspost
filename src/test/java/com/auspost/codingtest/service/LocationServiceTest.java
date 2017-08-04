package com.auspost.codingtest.service;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.auspost.codingtest.dao.ILocationDAO;
import com.auspost.codingtest.entity.Locations;

import static junit.framework.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
public class LocationServiceTest {
	
	@Autowired
	EntityManager em;
	@Autowired
	private ILocationDAO locationDAO;
	
	@Test
	public void testGetLocationById() {
		Locations locations = new Locations();
		locations.setLocationId(1);
		locations.setPostcode("3024");
		locations.setSuburb("Caroline springs");
		//when(locationDAO.getLocationById(1)).thenReturn(locations);
		Locations location =locationDAO.getLocationById(2);
		assertEquals(location.getPostcode(), "3037");
	}

}
