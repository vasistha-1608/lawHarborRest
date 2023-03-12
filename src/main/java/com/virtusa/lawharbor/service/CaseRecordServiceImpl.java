package com.virtusa.lawharbor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.lawharbor.dao.CaseRecordDao;
import com.virtusa.lawharbor.model.BookingModel;
import com.virtusa.lawharbor.model.CaseRecord;
import com.virtusa.lawharbor.model.LawyerModel;
import com.virtusa.lawharbor.model.User;

@Service
public class CaseRecordServiceImpl implements CaseRecordServiceInterface{

	@Autowired
	private CaseRecordDao caseRecordDao;
	
	
	
	@Override
	public List<CaseRecord> getAllCaseRecord() {

		return caseRecordDao.findAll();
	}

	@Override
	public List<CaseRecord> getAllCaseRecordsByIssuedBy(LawyerModel issuedBy) {
		
		return caseRecordDao.findAllByIssuedBy(issuedBy);
	}

	@Override
	public List<CaseRecord> getAllCaseRecordByUser(User user) {

		return caseRecordDao.findAllByUser(user);
	}

	@Override
	public CaseRecord saveCaseRecord(CaseRecord caseRecord) {

		return caseRecordDao.save(caseRecord);
	}

	@Override
	public CaseRecord updateCaseRecord( CaseRecord caseRecord) {
		if(this.caseRecordDao.existsById(caseRecord.getCaseRecordId())) {
			CaseRecord rc = this.findCaseRecordById(caseRecord.getCaseRecordId());
			rc.setDate(caseRecord.getDate());
			rc.setActionTaken(caseRecord.getActionTaken());
			rc.setEventDetails(caseRecord.getEventDetails());
			return caseRecordDao.save(rc);
			
		}
		return null;
	}

	@Override
	public CaseRecord deleteCaseRecordById(int id) {
		CaseRecord cs = this.caseRecordDao.findById(id).get();
		if(cs!=null) {
			caseRecordDao.deleteById(id);
			
		}
		return cs;
	}

	@Override
	public CaseRecord findCaseRecordById(int id) {
		
		return this.caseRecordDao.findById(id).get();
	}

	@Override
	public boolean existsCaseRecordByBookingId(BookingModel booking) {
		
		return caseRecordDao.existsByBookingDetails(booking);
	}

	@Override
	public void deleteCaseRecordByBookingDetails(BookingModel bookingModel) {
		if(caseRecordDao.existsByBookingDetails(bookingModel)) {
			caseRecordDao.deleteByBookingDetails(bookingModel);
			
		}
		
	}

}
