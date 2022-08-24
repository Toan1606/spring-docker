package com.codedecode.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.dto.CityResponseDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.exception.CityNotFound;
import com.codedecode.demo.repository.CityRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;

	public CityService( CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

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
	
	public List<City> findAllCityByProvinceId(Long provinceId){
		return cityRepository.findAllCityByProvinceId(provinceId);
	}

	public List<CityResponseDTO> findAllCityDto() {
		return cityRepository.findAllCityDto();
	}
	
	public List<Map<String, String>> findByAddress(Set<Address> addresss) {
		List<Map<String, String>> cities = new ArrayList<Map<String,String>>();
		for (Address address : addresss) {
			Map<String, String> citiesMap = new HashMap<String, String>();
			City city = cityRepository.findByAddressId(address.getId());
			if (city != null) {
				citiesMap.put("id", String.valueOf(city.getId()));
				citiesMap.put("name", city.getName());
				cities.add(citiesMap);
			}
		}
		return cities;
	}
	
	public City findByAddressId(Long addressId) {
		return cityRepository.findByAddressId(addressId);
	}
	
	public List<CityResponseDTO> convertToDTO(List<City> cities) {
		List<CityResponseDTO> citiesDTO = new ArrayList<CityResponseDTO>();
		for(City city : cities) {
			citiesDTO.add(new CityResponseDTO(city.getId(), city.getName()));
		}
		return citiesDTO;
	}
	
	public List<City> findCityByProvinceId(Long provinceId) {
		return cityRepository.findCityByProvinceId(provinceId);
	}
}
