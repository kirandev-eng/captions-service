package com.bmx.comm.entity;

import java.io.Serializable;

import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTPChargesId implements Serializable{

	private static final long serialVersionUID = 6764322954840886003L;
	
	@Column(name = "Account Id")
	private String accountId;
	
	@Column(name = "Reporting_Symbol")
	private String reportingSymbol;

}
