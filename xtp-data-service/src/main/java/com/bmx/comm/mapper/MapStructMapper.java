package com.bmx.comm.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bmx.comm.entity.XTPChargesBean;
import com.bmx.protobuf.card.CardTrade.FinEODDataResponse.FinEODData;


@Mapper(componentModel = "spring")
public interface MapStructMapper {
	
	@Mapping(source = "accountId", target = "account")
	@Mapping(source = "reportingSymbol", target = "metal")
	@Mapping(source = "originalAmount", target = "pnl")
	@Mapping(source = "exchangeId", target = "exchange")
	FinEODData xtpChargesTopDayToFinPosData(XTPChargesBean xTPChargesTopDay);
	List<FinEODData>  xtpChargesTopDayToFinPosDataList(List<XTPChargesBean> xTPChargesTopDayList);

}
