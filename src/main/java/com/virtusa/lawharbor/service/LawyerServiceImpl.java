package com.virtusa.lawharbor.service;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.lawharbor.dao.LawyerDao;
import com.virtusa.lawharbor.model.LawyerModel;
@Service
public class LawyerServiceImpl  implements LawyerInterface{
	
	private static final Logger log = LogManager.getLogger(LawyerServiceImpl.class.getName());

	@Autowired
	private LawyerDao dao;
	
	@Override
	public LawyerModel saveLawyer(LawyerModel lawyerModel) {
		
		try {
			if (lawyerModel == null) {
				log.log(Level.INFO, "User Deleted successfully .");
				return null;
			} else if (this.dao.findByEmail(lawyerModel.getEmail()) == lawyerModel) {
				log.log(Level.WARN, "User alrady exists :{0} ", lawyerModel);
				return null;
			} else {
				log.log(Level.INFO, "User Saved successfully .");
				return this.dao.save(lawyerModel);
			}

		} catch (Exception e) {
			log.log(Level.WARN, "Error occurred in create user : ", e);
		}
		return null;

	}

	@Override
	public List<LawyerModel> getLawyers() {
		try {
			return dao.findAll();
		}catch (Exception e) {
			log.log(Level.WARN, "Error occurred in get All User Service : ", e);
		}
		return Collections.emptyList();
	}
	
	@Override
	public LawyerModel findLawyerByEmail(String email) {
		
		return dao.findByEmail(email);

	}
	
	public LawyerModel findLawyerById(int id) {
		return dao.findById(id).get();
	}

	public void deleteLawyer(int lawyerId) {
		
		try {
			if (lawyerId == 0) {
				log.warn("No data Found in Delete service.");
				return;
			}
			if (this.dao.existsById(lawyerId)) {
				this.dao.deleteById(lawyerId);
				log.log(Level.INFO, "User Deleted successfully .");
			} else {
				log.log(Level.WARN, "User does not exists with id :{0} ", lawyerId);
			}
		} catch (Exception e) {
			log.log(Level.WARN, "Error occurred in Delete user service : ", e);
		}
		
	}

	public void updateLawyer(LawyerModel lawyer, int id) {
		try {
			if (lawyer == null) {
				log.warn("No data Found in Update service.");
				return;
			}
			if (this.dao.existsById(id)) {
				this.dao.save(lawyer);
				log.log(Level.INFO, "User data updated. :{0}", lawyer);
			} else {
				log.log(Level.WARN, "User does not exists with id :{0} ", id);
			}

		} catch (Exception e) {
			log.log(Level.WARN, "Error occurred in Update user service : ", e);
		}

		
	}

	@Override
	public void updateLawyerActiveByEmail(String email, boolean active) {
		try {
			if (email == null) {
				log.warn("No data Found in Update service.");
				return;
			}
			if (this.dao.existsByEmail(email)) {
				this.dao.updateActiveByEmail(active, email);
				log.log(Level.INFO, "lawyer status updated.");
			} else {
				log.log(Level.WARN, "User does not exists with email :{0} ",email );
			}

		} catch (Exception e) {
			log.log(Level.WARN, "Error occurred in Update user service : ", e);
		}
		
	}

	@Override
	public boolean existsLawyerByEmail(String eamil) {
		
		return dao.existsByEmail(eamil);
	}

}
