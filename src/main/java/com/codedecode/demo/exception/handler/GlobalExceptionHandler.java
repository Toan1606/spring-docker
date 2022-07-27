package com.codedecode.demo.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codedecode.demo.exception.AddressNotFound;
import com.codedecode.demo.exception.CityNotFound;
import com.codedecode.demo.exception.IndexException;
import com.codedecode.demo.exception.NoBearerTokenError;
import com.codedecode.demo.exception.NotFoundProvince;
import com.codedecode.demo.exception.PostingNotFound;
import com.codedecode.demo.exception.ProvinceNotFound;
import com.codedecode.demo.exception.StreetNotFound;
import com.codedecode.demo.exception.UnauthenticatedException;
import com.codedecode.demo.exception.UserNotFoundException;
import com.codedecode.demo.utils.ExceptionMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException() {
		return new ResponseEntity<String>(ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AddressNotFound.class)
	public ResponseEntity<String> handleAddressNotFound() {
		return new ResponseEntity<String>(ExceptionMessage.ADDRESS_NOT_FOUND.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CityNotFound.class)
	public ResponseEntity<String> handleCityNotFound() {
		return new ResponseEntity<String>(ExceptionMessage.CITY_NOT_FOUND.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IndexException.class)
	public ResponseEntity<String> handleIndexException() {
		return new ResponseEntity<String>(ExceptionMessage.INDEX_EXCEPTION.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoBearerTokenError.class)
	public ResponseEntity<String> handleNoBearerTokenError() {
		return new ResponseEntity<String>(ExceptionMessage.NO_BEARER_TOKEN_ERROR.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFoundProvince.class)
	public ResponseEntity<String> handleNotFoundProvince() {
		return new ResponseEntity<String>(ExceptionMessage.PROVINCE_NOT_FOUND.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PostingNotFound.class)
	public ResponseEntity<String> handlePostingNotFound() {
		return new ResponseEntity<String>(ExceptionMessage.POSTING_NOT_FOUND.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProvinceNotFound.class)
	public ResponseEntity<String> handleProvinceNotFound() {
		return new ResponseEntity<String>(ExceptionMessage.PROVINCE_NOT_FOUND.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StreetNotFound.class)
	public ResponseEntity<String> handleStreetNotFound() {
		return new ResponseEntity<String>(ExceptionMessage.STREET_NOT_FOUND.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnauthenticatedException.class)
	public ResponseEntity<String> handleUnauthenticatedException() {
		return new ResponseEntity<String>(ExceptionMessage.UNAUTHENTICATED_EXCEPTION.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException() {
		return new ResponseEntity<String>(ExceptionMessage.USER_NOT_FOUND.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
}
