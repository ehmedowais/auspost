package com.auspost.codingtest.service;

import java.util.List;

import com.auspost.codingtest.entity.Locations;

public interface ILocationService {
     List<Locations> getAllLocations();
     Locations getLocationById(int locationId);
     boolean addLocation(Locations loacation);
     List<Locations> getLocationByDetail(String suburb,String postcode);
     
}
