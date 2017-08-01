package com.auspost.codingtest.dao;
import java.util.List;

import com.auspost.codingtest.entity.Locations;
public interface ILocationDAO {
    List<Locations> getAllLocations();
    Locations getLocationById(int locationId);
    List<Locations> getLocationBySuburbAndPostcode(String suburb, String postcode);
    List<Locations> getLocationBySuburb(String suburb);
    List<Locations> getLocationByPostcode(String postcode);
    void addLocation(Locations location);
    
    boolean locationExists(String postCode, String suburb);
}
 