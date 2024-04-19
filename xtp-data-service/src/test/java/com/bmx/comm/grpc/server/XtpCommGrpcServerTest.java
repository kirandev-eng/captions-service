package com.bmx.comm.grpc.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.bmx.comm.entity.XTPChargesBean;
import com.bmx.comm.mapper.MapStructMapper;
import com.bmx.comm.repo.XTPChargesTopDayRepo;
import com.bmx.comm.utils.AppConstants;
import com.bmx.comm.utils.CustomLogger;
import com.bmx.protobuf.card.CardTrade.FinEODDataRequest;
import com.bmx.protobuf.card.CardTrade.FinEODDataResponse;
import com.bmx.protobuf.card.CardTrade.FinEODDataResponse.FinEODData;

import io.grpc.stub.StreamObserver;

class XtpCommGrpcServerTest {

    @Mock
    private XTPChargesTopDayRepo xtpCommRepo;

    @Spy
    private MapStructMapper mapper = Mappers.getMapper(MapStructMapper.class);

    @Mock
    private CustomLogger logger;

    @InjectMocks
    private XtpCommGrpcServer grpcServer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFinanceCommissionData() {

		FinEODDataRequest request = FinEODDataRequest.newBuilder().setExchange("LME").addAccounts("LME10044")
				.addAccounts("LME10099").build();

        StreamObserver<FinEODDataResponse> responseObserver = mock(StreamObserver.class);

        List<XTPChargesBean> xtpCommList = new ArrayList<>();
        xtpCommList.add(new XTPChargesBean("LME10044", "AHD", BigDecimal.valueOf(100.0), "LME", "USD"));
        xtpCommList.add(new XTPChargesBean("LME10099", "ZSD", BigDecimal.valueOf(200.0), "LME", "USD"));

		when(xtpCommRepo.findAllXtpCommByAccountsAndReportingClass(AppConstants.REPORTING_CLASS_CO,
				request.getAccountsList())).thenReturn(xtpCommList);

        grpcServer.getFinanceCommissionData(request, responseObserver);

        ArgumentCaptor<FinEODDataResponse> responseCaptor = ArgumentCaptor.forClass(FinEODDataResponse.class);
        verify(responseObserver).onNext(responseCaptor.capture());
        verify(responseObserver).onCompleted();

        FinEODDataResponse response = responseCaptor.getValue();
        assertEquals(2, response.getPosResponseCount());

        FinEODData finEodResp = response.getPosResponse(0);
        assertEquals("LME10044", finEodResp.getAccount());
        assertEquals("AHD", finEodResp.getMetal());
        assertEquals(100.0, finEodResp.getPnl(), 0.0);

        FinEODData actualResponse2 = response.getPosResponse(1);
        assertEquals("LME10099", actualResponse2.getAccount());
        assertEquals("ZSD", actualResponse2.getMetal());
        assertEquals(200.0, actualResponse2.getPnl(), 0.0);
    }

}


