package com.virtusa.lawharbor.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.virtusa.lawharbor.model.BookingModel;
import com.virtusa.lawharbor.model.CaseRecord;
import com.virtusa.lawharbor.model.LawyerModel;
import com.virtusa.lawharbor.model.User;

@Repository
public interface CaseRecordDao extends JpaRepository<CaseRecord, Integer> {

	public List<CaseRecord> findAllByIssuedBy(LawyerModel issuedBy);

	public List<CaseRecord> findAllByUser(User user);
	
	public boolean existsByBookingDetails(BookingModel bookingModel);
	
	@Transactional
	@Modifying
	public void deleteByBookingDetails(BookingModel bookingModel);

}
