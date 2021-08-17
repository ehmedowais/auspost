package com.auspost.codingtest.controller;
import java.util.List;


import com.auspost.codingtest.util.RequestCorrelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.auspost.codingtest.entity.Locations;
import com.auspost.codingtest.exceptions.LocationException;
import com.auspost.codingtest.service.ILocationService;
import com.auspost.codingtest.util.Validator;

/**
 * 
 * @author Muhammad Owais Ahmed
 * This class is main controller class. The class defines operations as under
 * 1. 	http://localhost:8080/auspost/location/{id} this api is used to get the suburb/postcode information if you 
 * 		know the id of record
 * 2.	http://localhost:8080/auspost/locations this api is used to get a list of records from database without any
 * 		specific criteria
 * 3.	http://locahost:8080/auspost/suburbs/{suburb} api is used to get the suburb information if name of suburb is 
 * 		provided. it is possible in Australia that a suburb is located in multiple states with same name that is why
 * 		this api returns a list of suburbs if suburbs exist
 * 
 * 4.	http://locahost:8080/auspost/postcodes/{postcode} api is used to get the location information if postcode is 
 * 		provided. it is possible in Australia that a postcode relates to multiple suburbs with that is why
 * 		this api returns a list of postcodes if postcode exist
 * 
 * 5.	http://locahost:8080/auspost/location api is used to add the location information. This is a secured api
 * 		and if one wants to use this api s/he needs to provide basic aunthentication header. To keep the application
 * 		simple two users have been created in memory user1/password1 and user2/password2. LDAP is the recommended 
 * 		way to use for security. Same method from configuration class could be used to configure LDAP. If SSL
 * 		needs to enabled the properties file will have to be updated. I have commented SSL information in
 * 		application.properties file. If you want to test against SSL please provide related informatino in 
 * 		properties file. 
 *
 */
@RestController
@RequestMapping("auspost")
public class LocationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);
	@Autowired
	private ILocationService locationService;
	@GetMapping("location/{id}")
	public ResponseEntity<Locations> getLocationById(@PathVariable("id") Integer id) {
		Locations location = locationService.getLocationById(id);
		RequestCorrelation.logResponse( LOGGER, "SUCCESS");
		return new ResponseEntity<Locations>(location, HttpStatus.OK);
	}
	@GetMapping("locations")
	public ResponseEntity<List<Locations>> getAllLocations() {

		List<Locations> list = locationService.getAllLocations();

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(RequestCorrelation.CORRELATION_ID_HEADER, RequestCorrelation.getId());
		return ResponseEntity.ok().headers(responseHeaders).body(list);
	}
	@GetMapping("suburbs/{suburb}")
	public ResponseEntity<List<Locations>> getSuburbInfo(@PathVariable("suburb") String suburb) throws LocationException{

		if(StringUtils.isEmpty(suburb)){
			throw new LocationException("No suburb information provided, please provide a valid suburb.",HttpStatus.BAD_REQUEST);
		}
		List<Locations> suburbs = locationService.getLocationByDetail(suburb,null);
		if(suburbs.size() < 1) {
			throw new LocationException("No suburb information provided, please provide a valid suburb.",HttpStatus.BAD_REQUEST);

		}

		return new ResponseEntity<List<Locations>>(suburbs, HttpStatus.OK);
	}
	@GetMapping("postcodes/{postcode}")
	public ResponseEntity<List<Locations>> getPostcodeInfo(@PathVariable("postcode") String postcode)throws LocationException {
		
		if(!Validator.isValidPostcode(postcode)){
						
			throw new LocationException("Invalid postcode, please provide four digit australian postcode",HttpStatus.BAD_REQUEST);
		}
		List<Locations> postcodes = locationService.getLocationByDetail(null,postcode);

		return new ResponseEntity<List<Locations>>(postcodes, HttpStatus.OK);
	}
	@PostMapping("location")
	public ResponseEntity<Void> addLocation(@RequestBody Locations location, UriComponentsBuilder builder)throws LocationException {
		if(null == location || !Validator.isValidPostcode(location.getPostcode()) || StringUtils.isEmpty(location.getSuburb())){
			throw new LocationException("Invalid postcode or suburb, please provide four digit australian postcode with valid suburb",HttpStatus.BAD_REQUEST);
		}
        boolean flag = locationService.addLocation(location);
        if (flag == false) {
        	throw new LocationException("Postcode with suburb already exist",HttpStatus.CONFLICT);
        	
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/location/{id}").buildAndExpand(location.getLocationId()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

} 