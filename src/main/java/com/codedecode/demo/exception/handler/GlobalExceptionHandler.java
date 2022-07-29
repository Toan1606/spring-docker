package com.codedecode.demo.exception.handler;

import java.util.Date;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codedecode.demo.exception.AddressNotFound;
import com.codedecode.demo.exception.CityNotFound;
import com.codedecode.demo.exception.ErrorDetails;
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
	public ResponseEntity<ErrorDetails> handleIllegalArgumentException() {
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date())
				.message(ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION.getErrorMessage())
				.details(ExceptionMessage.ILLEGAL_ARGUMENT_EXCEPTION.getErrorMessage()).build();
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AddressNotFound.class)
	public ResponseEntity<ErrorDetails> handleAddressNotFound() {
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date())
				.message(ExceptionMessage.ADDRESS_NOT_FOUND.getErrorMessage())
				.details(ExceptionMessage.ADDRESS_NOT_FOUND.getErrorMessage()).build();
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CityNotFound.class)
	public ResponseEntity<ErrorDetails> handleCityNotFound() {
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date())
				.message(ExceptionMessage.CITY_NOT_FOUND.getErrorMessage())
				.details(ExceptionMessage.CITY_NOT_FOUND.getErrorMessage()).build();
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IndexException.class)
	public ResponseEntity<ErrorDetails> handleIndexException() {
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date())
				.message(ExceptionMessage.INDEX_EXCEPTION.getErrorMessage())
				.details(ExceptionMessage.INDEX_EXCEPTION.getErrorMessage()).build();
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoBearerTokenError.class)
	public ResponseEntity<ErrorDetails> handleNoBearerTokenError() {
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date())
				.message(ExceptionMessage.NO_BEARER_TOKEN_ERROR.getErrorMessage())
				.details(ExceptionMessage.NO_BEARER_TOKEN_ERROR.getErrorMessage()).build();
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundProvince.class)
	public ResponseEntity<ErrorDetails> handleNotFoundProvince() {
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date())
				.message(ExceptionMessage.PROVINCE_NOT_FOUND.getErrorMessage())
				.details(ExceptionMessage.PROVINCE_NOT_FOUND.getErrorMessage()).build();
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PostingNotFound.class)
	public ResponseEntity<ErrorDetails> handlePostingNotFound() {
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date())
				.message(ExceptionMessage.POSTING_NOT_FOUND.getErrorMessage())
				.details(ExceptionMessage.POSTING_NOT_FOUND.getErrorMessage()).build();
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ProvinceNotFound.class)
	public ResponseEntity<ErrorDetails> handleProvinceNotFound() {
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date())
				.message(ExceptionMessage.PROVINCE_NOT_FOUND.getErrorMessage())
				.details(ExceptionMessage.PROVINCE_NOT_FOUND.getErrorMessage()).build();
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(StreetNotFound.class)
	public ResponseEntity<ErrorDetails> handleStreetNotFound() {
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date())
				.message(ExceptionMessage.STREET_NOT_FOUND.getErrorMessage())
				.details(ExceptionMessage.STREET_NOT_FOUND.getErrorMessage()).build();
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnauthenticatedException.class)
	public ResponseEntity<ErrorDetails> handleUnauthenticatedException() {
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date())
				.message(ExceptionMessage.UNAUTHENTICATED_EXCEPTION.getErrorMessage())
				.details(ExceptionMessage.UNAUTHENTICATED_EXCEPTION.getErrorMessage()).build();
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleUserNotFoundException() {
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date())
				.message(ExceptionMessage.USER_NOT_FOUND.getErrorMessage())
				.details(ExceptionMessage.USER_NOT_FOUND.getErrorMessage()).build();
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ErrorDetails> handleEmptyResultDataAccessException() {
		ErrorDetails error = ErrorDetails.builder().timestamp(new Date())
				.message(ExceptionMessage.EMPTY_RESULT_DATA_ACCESS_EXCEPTION.getErrorMessage())
				.details(ExceptionMessage.EMPTY_RESULT_DATA_ACCESS_EXCEPTION.getErrorMessage()).build();
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}
	
	
}
