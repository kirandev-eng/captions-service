package com.bmx.comm.mapper;

import com.bmx.comm.entity.XTPChargesBean;
import com.bmx.protobuf.card.CardTrade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-19T13:54:41+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240325-1403, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public CardTrade.FinEODDataResponse.FinEODData xtpChargesTopDayToFinPosData(XTPChargesBean xTPChargesTopDay) {
        if ( xTPChargesTopDay == null ) {
            return null;
        }

        CardTrade.FinEODDataResponse.FinEODData.Builder finEODData = CardTrade.FinEODDataResponse.FinEODData.newBuilder();

        finEODData.setAccount( xTPChargesTopDay.getAccountId() );
        finEODData.setMetal( xTPChargesTopDay.getReportingSymbol() );
        if ( xTPChargesTopDay.getOriginalAmount() != null ) {
            finEODData.setPnl( xTPChargesTopDay.getOriginalAmount().doubleValue() );
        }
        finEODData.setExchange( xTPChargesTopDay.getExchangeId() );
        finEODData.setCurrency( xTPChargesTopDay.getCurrency() );

        return finEODData.build();
    }

    @Override
    public List<CardTrade.FinEODDataResponse.FinEODData> xtpChargesTopDayToFinPosDataList(List<XTPChargesBean> xTPChargesTopDayList) {
        if ( xTPChargesTopDayList == null ) {
            return null;
        }

        List<CardTrade.FinEODDataResponse.FinEODData> list = new ArrayList<CardTrade.FinEODDataResponse.FinEODData>( xTPChargesTopDayList.size() );
        for ( XTPChargesBean xTPChargesBean : xTPChargesTopDayList ) {
            list.add( xtpChargesTopDayToFinPosData( xTPChargesBean ) );
        }

        return list;
    }
}
