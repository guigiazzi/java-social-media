package com.mackenzie.br.socialmedia.domain;

import java.io.File;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.mackenzie.br.socialmedia.enums.InstructionLevelEnum;
//import com.mackenzie.br.socialmedia.enums.ProfileTypeEnum;

@Document(collection = "waterFlowCollection")
public class ProfessionalDomain {

	@Id
	private String professionalID;

	private String name;

	private String userLogin;

	//@JsonIgnore
	private String password;

	private File profileImage;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthDate;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date careerDate;
	
	//@JsonProperty
	private JobRoleDomain jobRole;

//	private InstructionLevelEnum instructionLevel;

//	private ProfileTypeEnum profileType;

	public ProfessionalDomain() {
		
	}

	public String getProfessionalID() {
		return professionalID;
	}

	public void setProfessionalID(String professionalID) {
		this.professionalID = professionalID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public File getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(File profileImage) {
		this.profileImage = profileImage;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getCareerDate() {
		return careerDate;
	}

	public void setCareerDate(Date careerDate) {
		this.careerDate = careerDate;
	}

	public JobRoleDomain getJobRoleID() {
		return jobRole;
	}

	public void setJobRole(JobRoleDomain jobRole) {
		this.jobRole = jobRole;
	}

//	public InstructionLevelEnum getInstructionLevel() {
//		return instructionLevel;
//	}
//
//	public void setInstructionLevel(InstructionLevelEnum instructionLevel) {
//		this.instructionLevel = instructionLevel;
//	}
//
//	public ProfileTypeEnum getProfileType() {
//		return profileType;
//	}
//
//	public void setProfileType(ProfileTypeEnum profileType) {
//		this.profileType = profileType;
//	}
}
