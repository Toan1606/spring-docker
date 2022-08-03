package com.codedecode.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.exception.CityNotFound;
import com.codedecode.demo.repository.CityRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;

	public City findCityById(Long id) {
		return cityRepository.findById(id).orElseThrow(() -> new CityNotFound(ExceptionMessage.CITY_NOT_FOUND.getErrorMessage()));
	}
	
	public City findCityByCityName(String cityName) {
		City city = cityRepository.findByName(cityName).orElseThrow(() -> new CityNotFound(ExceptionMessage.CITY_NOT_FOUND.getErrorMessage()));
		return city;
	}
	
	public List<City> findAllCity() {
		return cityRepository.findAll();
	}
	
	public List<City> findCityByProvinceId(Province province) {
		return cityRepository.findByProvince(province);
	}

	public List<String> findByAddress(List<Address> addresss) {
		List<String> cities = new ArrayList<String>();
		for (Address address : addresss) {
			City city = cityRepository.findByAddressId(address.getId());
			if (city != null) {
				cities.add(city.getName());
			}
		}
		
		return cities;
	}
	
	public City findByAddressId(Long addressId) {
		return cityRepository.findByAddressId(addressId);
	}
}
