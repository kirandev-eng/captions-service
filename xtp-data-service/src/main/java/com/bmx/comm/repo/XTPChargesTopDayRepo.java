package com.bmx.comm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bmx.comm.entity.XTPChargesTopDay;
import com.bmx.comm.entity.XTPChargesBean;

@Repository
public interface XTPChargesTopDayRepo extends JpaRepository<XTPChargesTopDay, String>{
	
	@Query(nativeQuery = true)
	List<XTPChargesBean> findAllXtpCommByAccountsAndReportingClass(String reportingClass, List<String> accounts);

}
