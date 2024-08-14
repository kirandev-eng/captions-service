package com.bmx.comm.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SqlResultSetMapping(name = "findAllEodXtpCommByAccountsAndReportingClass",
classes = @ConstructorResult(targetClass = XTPChargesBean.class, 
	columns = { @ColumnResult(name = "accountId"),
            		@ColumnResult(name = "reportingSymbol"),
            			@ColumnResult(name = "originalAmount"),
            				@ColumnResult(name = "exchangeId"),
            					@ColumnResult(name = "currency")}))

@NamedNativeQuery(name = "XTPCharges.findAllEodXtpCommByAccountsAndReportingClass", 
query = "select [Account Id] as accountId, Reporting_Symbol as reportingSymbol, sum([Original Amount]*-1) as originalAmount, exchange_id as exchangeId, Currency as currency from Archive_XTP_Charges with (nolock) where business_date = :businessDate and Reporting_Class = :reportingClass and [Account Id] in (:accounts) group by [Account Id], Reporting_Symbol, exchange_id, Currency",
	resultSetMapping = "findAllEodXtpCommByAccountsAndReportingClass")

@Entity
@Table(name = "Archive_XTP_Charges")
@IdClass(XTPChargesId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XTPCharges {
	
	@Id
	private String accountId;
	
	@Id
	private String reportingSymbol;
	
	@Column(name = "original Amount")
	private BigDecimal originalAmount;
	
	@Column(name = "Reporting_Class")
	private String reportingClass;
	
	@Column(name = "exchange_id")
	private String exchangeId;
	
	@Column(name = "Currency")
	private String currency;
	
	

}

