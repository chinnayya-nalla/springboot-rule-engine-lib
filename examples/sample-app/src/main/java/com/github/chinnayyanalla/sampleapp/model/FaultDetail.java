package com.github.chinnayyanalla.sampleapp.model;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaultDetail implements Serializable {

	private static final long serialVersionUID = 5311536095187802382L;

	private String reason;
	private String code;
	private String description;
}
