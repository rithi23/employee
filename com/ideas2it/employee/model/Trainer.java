/**
 * <p> 
 * This is the package for model class 
 * Copyright 2022 - ideas2it.
 * </p>
 */
package com.ideas2it.employee.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.ideas2it.employee.model.Employee;

/**
 * <p>
 * Trainer class has trainer specific details as well as 
 * setters and getters for trainers
 * </p>
 * @author Rithikanth
 * @since 23/01/2022
 */
public class Trainer extends Employee {
    private String project;
    private String manager;
    private List trainees = new ArrayList<>();

    public Trainer(String id, String name,
	     String bloodGroup, long phoneNumber, 
	     String address, String position, 
	     float experience, String project,
             String manager,
             String emailId, LocalDate dateOfBirth, int age)  {

         super( id, name, bloodGroup, phoneNumber, address,
              position, experience, emailId, dateOfBirth, age);
         this.project = project;
         this.manager = manager;
    }

    /**
     * <p>
     * This method sets the project for the trainer
     * </p>
     * @param project project of the trainer
     */
    public void setProject(String project) {
        this.project = project;
    }
    
    /**
     *<p>
     * This method gets the project of the trainer
     * </p>
     */
    public String getProject() {
        return project;
    }
    
    /**
     * <p>
     * This method sets the manager for the trainer
     * </p>
     * @param manager this is manager for the trainer
     */
    public void setManager(String manager) { 
        this.manager = manager;
    }
  
    /**
     * <p>
     * This method gets the manager for the trainer
     * </p>
     */
    public String getManager() {
        return manager;
    }

    /**
     * <p>
     * This method sets the traineee for the trainers
     * </p>
     *
     * @param trainee - trainee is of trainee employee
     */
    public void setTrainees(String traineeId) {
        this.trainees.add(traineeId);
        
    }

    /**
     * <p>
     * This method is used to get the trainee's list
     * </p>
     */
    public List<Trainee> getTrainees() {
        return trainees;
    }

    public String toString() {
        return ( "id : " + getId() + "\n" +
                 "name : " + getName() + "\n" +
                 "bloodGroup : " + getBloodGroup() + "\n" +
                 "phoneNumber : " + getPhoneNumber() + "\n" +
                 "address : " + getAddress() + "\n" +
                 "position : " + getPosition() + "\n" +
                 "experience : " + getExperience() + "\n" +
                 "emailId : " + getEmailId() + "\n" +
                 "age : " + getAge() + "\n" +
                 "project : " + getProject() + "\n" +
                 "manager : " + getManager() + "\n" +
                 "Trainees : " + getTrainees());
                 
    }
}