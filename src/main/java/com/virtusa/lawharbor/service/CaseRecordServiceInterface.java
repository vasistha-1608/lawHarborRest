package com.virtusa.lawharbor.service;

import java.util.List;

import com.virtusa.lawharbor.model.BookingModel;
import com.virtusa.lawharbor.model.CaseRecord;
import com.virtusa.lawharbor.model.LawyerModel;
import com.virtusa.lawharbor.model.User;

public interface CaseRecordServiceInterface {

	public List<CaseRecord> getAllCaseRecord();

	public List<CaseRecord> getAllCaseRecordsByIssuedBy(LawyerModel issuedBy);

	public List<CaseRecord> getAllCaseRecordByUser(User user);
	
	public CaseRecord findCaseRecordById(int id);
	
	public boolean existsCaseRecordByBookingId(BookingModel booking);

	public CaseRecord saveCaseRecord(CaseRecord caseRecord);

	public CaseRecord updateCaseRecord(CaseRecord caseRecord);

	public CaseRecord deleteCaseRecordById(int id);
	
	public void deleteCaseRecordByBookingDetails(BookingModel bookingModel);

}
