package com.mackenzie.br.socialmedia.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mackenzie.br.socialmedia.enums.InstructionLevelEnum;
import com.mackenzie.br.socialmedia.enums.ProfileTypeEnum;

//import com.mackenzie.br.socialmedia.enums.InstructionLevelEnum;
//import com.mackenzie.br.socialmedia.enums.ProfileTypeEnum;

@Document(collection = "ProfessionalCollection")
public class ProfessionalDomain {

	@Id
	private String professionalID;
	
	@NotNull
	private String name;
	
	@NotNull
	private String userLogin;

	//@JsonIgnore
	@NotNull
	private String password;

	private File profileImage;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull
	@DateTimeFormat
	private Date birthDate;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull
	@DateTimeFormat
	private Date careerDate;
	
	//@JsonProperty
	@NotNull
	private JobRoleDomain jobRole;

	private InstructionLevelEnum instructionLevel;

	private ProfileTypeEnum profileType;

	public ProfessionalDomain() {
		
	}

	public InstructionLevelEnum getInstructionLevel() {
		return instructionLevel;
	}

	public void setInstructionLevel(InstructionLevelEnum instructionLevel) {
		this.instructionLevel = instructionLevel;
	}

	public ProfileTypeEnum getProfileType() {
		return profileType;
	}

	public void setProfileType(ProfileTypeEnum profileType) {
		this.profileType = profileType;
	}

	public JobRoleDomain getJobRole() {
		return jobRole;
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
	
}