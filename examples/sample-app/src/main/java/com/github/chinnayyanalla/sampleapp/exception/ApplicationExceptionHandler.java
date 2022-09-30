package com.github.chinnayyanalla.sampleapp.exception;


import com.github.chinnayyanalla.sampleapp.model.Fault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ CustomException.class })
	public final ResponseEntity<Fault> handleException(CustomException customException, WebRequest webRequest) {
		Fault fault = new Fault();
		fault.setDetails(customException.getFaultDetails());
		return new ResponseEntity<>(fault, customException.getHttpStatus());
	}

}
