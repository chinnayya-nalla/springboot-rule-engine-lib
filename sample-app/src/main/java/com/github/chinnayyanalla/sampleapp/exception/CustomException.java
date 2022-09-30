package com.github.chinnayyanalla.sampleapp.exception;

import com.github.chinnayyanalla.sampleapp.model.FaultDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;
    private final List<FaultDetail> faultDetails;

}
