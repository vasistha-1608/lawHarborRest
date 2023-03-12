package com.virtusa.lawharbor.service;


import java.util.List;

import com.virtusa.lawharbor.model.LawyerModel;

public interface LawyerInterface {
	
	public LawyerModel saveLawyer(LawyerModel lawyerModel);
	
	public List<LawyerModel> getLawyers();
	
	public LawyerModel findLawyerByEmail(String email);
	
	public void updateLawyerActiveByEmail(String email, boolean active);
	
	public boolean existsLawyerByEmail(String eamil);

}
