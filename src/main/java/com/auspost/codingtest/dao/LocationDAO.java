package com.auspost.codingtest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.auspost.codingtest.entity.Locations;
/**
 * 
 * @author Muhammad Owais Ahmed
 * This is the DAO class this class works with LocationService class
 *
 */
@Transactional
@Repository
public class LocationDAO implements ILocationDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Locations getLocationById(int locationId) {
		return entityManager.find(Locations.class, locationId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Locations> getAllLocations() {
		String hql = "FROM Locations ORDER BY locationId";
		return (List<Locations>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void addLocation(Locations location) {
		entityManager.persist(location);
	}

	@Override
	public boolean locationExists(String suburb, String postcode) {
		String hql = "FROM Locations as lctn WHERE lctn.suburb = ?1 and lctn.postcode = ?2";
		int count = entityManager.createQuery(hql).setParameter(1, suburb).setParameter(2, postcode).getResultList()
				.size();
		return count > 0 ? true : false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Locations> getLocationBySuburbAndPostcode(String suburb, String postcode) {
		List<Locations> locations = null;
		
		String hql = "FROM Locations as lctn WHERE lctn.suburb = ?1 and lctn.postcode = ?2";
		locations = (List<Locations>) entityManager.createQuery(hql).setParameter(1, suburb)
				.setParameter(2, postcode).getResultList();
		return locations;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Locations> getLocationBySuburb(String suburb) {
		List<Locations> locations;
		String hql = "FROM Locations as lctn WHERE upper(lctn.suburb) = ?1 ";
		locations = (List<Locations>) entityManager.createQuery(hql).setParameter(1, suburb.toUpperCase()).getResultList();
		return locations;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Locations> getLocationByPostcode(String postcode) {
		List<Locations> locations;
		String hql = "FROM Locations as lctn WHERE upper(lctn.postcode) = ?1 ";
		locations = (List<Locations>) entityManager.createQuery(hql).setParameter(1, postcode).getResultList();
		return locations;
	}
}
