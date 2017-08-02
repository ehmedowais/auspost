package com.auspost.codingtest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.auspost.codingtest.dao.ILocationDAO;
import com.auspost.codingtest.entity.Locations;
@Service
@Lazy
public class LocationService implements ILocationService {
	@Autowired
	private ILocationDAO locationDAO;
	@Override
	public Locations getLocationById(int locationId) {
		Locations obj = locationDAO.getLocationById(locationId);
		return obj;
	}	
	@Override
	public List<Locations> getAllLocations(){
		return locationDAO.getAllLocations();
	}
	@Override
	public synchronized boolean addLocation(Locations location){
       if (locationDAO.locationExists(location.getSuburb(), location.getPostcode())) {
    	   return false;
       } else {
    	   locationDAO.addLocation(location);
    	   return true;
       }
	}
	@Override
	public List<Locations> getLocationByDetail(String suburb,String postcode) {
		if(StringUtils.isEmpty(suburb) && StringUtils.isEmpty(postcode))
			return new ArrayList();
		if(StringUtils.isEmpty(postcode))
			return locationDAO.getLocationBySuburb(suburb);
		if(StringUtils.isEmpty(suburb))
			return locationDAO.getLocationByPostcode(postcode);
		else
			return locationDAO.getLocationBySuburbAndPostcode(suburb, postcode);
		
		
	}
	
	
}
