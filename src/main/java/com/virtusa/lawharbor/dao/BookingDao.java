package com.virtusa.lawharbor.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.lawharbor.model.BookingModel;
import com.virtusa.lawharbor.model.LawyerModel;
import com.virtusa.lawharbor.model.User;

public interface BookingDao extends JpaRepository<BookingModel, Integer> {
	
	public List<BookingModel> findAllByLawyerDetail(LawyerModel lawyerDetail);
	public List<BookingModel> findAllByClientDetail(User clientdetail);
}
