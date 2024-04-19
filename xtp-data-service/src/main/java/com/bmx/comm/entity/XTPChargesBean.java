package com.bmx.comm.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTPChargesBean {
	
	private String accountId;

	private String reportingSymbol;

	private BigDecimal originalAmount;
	
	private String exchangeId;
	
	private String currency;

}
