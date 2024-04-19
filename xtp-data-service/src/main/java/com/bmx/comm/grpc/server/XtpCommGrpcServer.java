package com.bmx.comm.grpc.server;

import java.util.ArrayList;
import java.util.List;

import org.lognet.springboot.grpc.GRpcService;

import com.bmx.comm.entity.XTPChargesBean;
import com.bmx.comm.mapper.MapStructMapper;
import com.bmx.comm.repo.XTPChargesRepo;
import com.bmx.comm.repo.XTPChargesTopDayRepo;
import com.bmx.comm.utils.AppConstants;
import com.bmx.comm.utils.CustomLogger;
import com.bmx.protobuf.card.CardTrade.FinEODDataRequest;
import com.bmx.protobuf.card.CardTrade.FinEODDataResponse;
import com.bmx.protobuf.card.CardTrade.FinEODDataResponse.FinEODData;
import com.bmx.protobuf.card.CardTradeServiceGrpc.CardTradeServiceImplBase;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@GRpcService
public class XtpCommGrpcServer extends CardTradeServiceImplBase{
	
	private final XTPChargesTopDayRepo xtpCommRepo;
	
	private final XTPChargesRepo eodRepo;
	
	private final MapStructMapper mapper;
	
	private final CustomLogger logger;
	
	@Override
	public void getFinanceCommissionData(FinEODDataRequest request, StreamObserver<FinEODDataResponse> responseObserver) {
		try {
			List<FinEODData> finEodDataList = new ArrayList<>();
			List<XTPChargesBean> xtpCommList = null;
			logger.debug("XtpCommGrpcServer getFinanceCommissionData request: "+request.getAccountsList()+"::"+request.getExchange()+"::"+request.getPosType()+"::"+request.getTradeDate(), "-", "-");
			if (request.getPosType().equals(AppConstants.EOD)) {
				xtpCommList = eodRepo.findAllEodXtpCommByAccountsAndReportingClass(request.getTradeDate(), AppConstants.REPORTING_CLASS_CO, request.getAccountsList());
			} else {
				xtpCommList = xtpCommRepo.findAllXtpCommByAccountsAndReportingClass(AppConstants.REPORTING_CLASS_CO, request.getAccountsList());
			}

			logger.debug("XtpCommGrpcServer getFinanceCommissionData response: "+xtpCommList, "-", "-");
			if (!xtpCommList.isEmpty()) {
				finEodDataList = mapper.xtpChargesTopDayToFinPosDataList(xtpCommList);
			}
			FinEODDataResponse finEODDataResponse = FinEODDataResponse.newBuilder().addAllPosResponse(finEodDataList).build();
			responseObserver.onNext(finEODDataResponse);
			responseObserver.onCompleted();
		} catch (Exception e) {
			logger.error("XtpCommGrpcServer getFinanceCommissionData : ", e, "-", "-");
		}
	}

}
