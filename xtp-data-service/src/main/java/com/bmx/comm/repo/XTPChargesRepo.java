package com.bmx.comm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bmx.comm.entity.XTPCharges;
import com.bmx.comm.entity.XTPChargesBean;

@Repository
public interface XTPChargesRepo extends JpaRepository<XTPCharges, String>{
	
	@Query(nativeQuery = true)
	List<XTPChargesBean> findAllEodXtpCommByAccountsAndReportingClass(String businessDate, String reportingClass, List<String> accounts);

}
