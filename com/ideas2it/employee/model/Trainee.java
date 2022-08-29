/**
 * <p> 
 * Package for model class 
 * Copyright 2022 - ideas2it.
 * </p>
 */
package com.ideas2it.employee.model;

import java.time.LocalDate; 

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employee.model.Employee;

/** 
 * <p>
 * This class contains trainee specific information as well as getters and setters for trainee
 * </p>
 *
 * @author  Rithikanth
 * @since 23/01/2001
 */
public class Trainee extends Employee {
    private String trainingPeriod;
    private String trainerName;

    public Trainee(String id, String name, 
	     String bloodGroup, long phoneNumber, 
	     String address, String position, 
	     float experience, String trainingPeriod, String emailId,
             LocalDate dateOfBirth, int age) {

        super(id, name, bloodGroup, phoneNumber,
               address, position, experience,
               emailId, dateOfBirth, age);
        this.trainingPeriod = trainingPeriod;
    }
    
    /**
     * <p>
     * This Method sets the training period for the trainee
     * </p>
     *
     * @param trainingPeriod trainingPeriod is training period for the trainee
     * @author  Rithikanth
     */
    public void setTrainingPeriod(String trainingPeriod) {
        this.trainingPeriod = trainingPeriod;
    }
    
    /**
     * <p>
     * This Method gets the training period for the trainee
     * </p>
     */
    public String getTrainingPeriod() {
        return trainingPeriod;
    }

    /**
     * <p> 
     * This method is used to set the trainer for trainee
     * </p>
     *
     * @param trainerName - is the trainer for trainee
     */
    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    /**
     * <p> 
     * This method is used to get the trainer name for the trainee
     * </p>
     */
    public String getTrainerName() {
        return trainerName;
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
                 "trainingPeriod : " + getTrainingPeriod() + "\n" +
                 "trainerId : " + getTrainerName());                 
    }
}