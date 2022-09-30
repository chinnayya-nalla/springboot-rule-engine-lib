package com.github.chinnayyanalla.sampleapp.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fault implements Serializable {

	private static final long serialVersionUID = 1905122041950251207L;

	private String traceId;
	private List<FaultDetail> details;
}
