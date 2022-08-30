/**
 * <p> 
 * Package for dao class 
 * Copyright 2022 - ideas2i.
 * </p>
 */
package com.ideas2it.employee.dao;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List; // hello

import com.ideas2it.employee.model.Employee; 
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer; 

/**
 * <p>
 * This EmployeeDAO interface which is used to store trainer and trainee details in abstact  
 * and it acts as the database for our project
 *
 * @author RithiKanth
 * @since 2022
 * </p>
 */
public interface EmployeeDAO {

    /**
     * <p>
     * This abstract method is used for insert Trainers in the List
     * </p>
     * @param trainer trainer contains trainer employee details
     *
     * @return boolean - whether the trainer is inserted or not
     */   
    public boolean insertTrainer(Trainer trainer) throws SQLException;

    /**
     * <p>
     * This abstract method is used for insert Trainees in the List
     * </p>
     *
     * @param trainee trainee contains trainee employee details
     *
     * @return boolean - whether the trainee is inserted or not
     */
    public boolean insertTrainee(Trainee trainee) throws SQLException;

    
   /**
     * <p>
     * This abstract method is used to get the specific trainer.
     * </p>
     *
     * @param id
     *
     * @return trainer employee
     */
    public Trainer getTrainer(String id)throws SQLException;

    /**
     * <p>
     * This abstract method is used to get the specific trainee.
     * </p>
     * @paramid id is the employeeid
     *
     * @return trainee employee
     */
    public Trainee getTrainee(String id) throws SQLException;
    
    /**
     * <p>
     * This method is used for update Address for trainer 
     * </p>
     *
     * @param trainerId - is the id of trainer 
     * @updateValue updateValue is the new value for trainer
     */ 
    public boolean updateAddressForTrainer(String trainerId, String updateValue) throws SQLException ;

    
    /**
     * <p>
     * This method is used for update position for trainer
     * </P>
     * 
      * @param trainerId - is the id of trainer 
     * @updateValue updateValue is the new value for trainer
     */ 
    public boolean updatePositionForTrainer(String trainerId, String updateValue) throws SQLException;

    
    /**
     * <p>
     * This method is used for update experience for trainer 
     * </p>
     *
     * @param trainerId - is the id of trainer 
     * @updateValue updateValue is the new value for trainer
     */ 
    public boolean updateExperienceForTrainer(String trainerId, float updatedInput) throws SQLException;
    
    
    /**
     * <p>
     * This method is used for update Address for trainee 
     * </p>
     *
     * @param traineeId - is the id of trainee 
     * @updateValue updateValue is the new value for trainee
     */ 
    public boolean updateAddressForTrainee(String traineeId, String updateValue) throws SQLException;

    /** 
     * <p>
     * This method is used for update position for trainee 
     * </P>
     *
     * @param traineeId - is the id of trainee 
     * @updateValue updateValue is the new value for trainee
     */
    public boolean updatePositionForTrainee(String traineeId, String updateValue) throws SQLException;

    /**
     * <p>
     * This method is used for update Address for trainee 
     * </p>
     *
     * @param traineeId - is the id of trainee 
     * @updateValue updateValue is the new value for trainee
     */
    public boolean updateExperienceForTrainee(String traineeId, float updatedInput) throws SQLException;

    /**
     * <p>
     * This abstract method is used to delete the specific trainer.
     * </p>
     *
     * @paramemployeeId id is the employeeid
     */
    public boolean deleteTrainer(String employeeId) throws SQLException;

    /**
     * <p>
     * This abstract method is used to delete the specific trainee.
     * </p>
     *
     * @paramemployeeId id is the employeeid
     */
    public boolean deleteTrainee(String employeeId) throws SQLException;

    /**
     * <p>
     * This method validates the phone number is unique or not
     * </p>
     * @param phoneNumber - phoneNumber of the employee
     */  
    public boolean checkUniquePhoneNumber(long phoneNumber) throws SQLException;

    /**
     * <p> 
     * This method shows the list of trainers
     * </p>
     */
    public List<Trainer> showTrainers() throws SQLException;

    /**
     * <p>
     * This method shows the list of trainees
     * </p>
     */
    public List<Trainee> showTrainees() throws SQLException;

    /**
     * <p>
     * This method is for adding trainer to trainees
     * </p>
     * @param trainer - is the trainer employee
     * @param trainee - is the trainee employee
     */
    public boolean addTrainerForTrainees(Trainer trainer,Trainee trainee) throws SQLException;

    /**
     * <p>
     * This method gets the trainees for the trainer 
     * </p>
     *
     * @param trainer - trainer employee
     * @param trainerId  - is id of the trainer
     */
    public void getTrainees(String trainerId,Trainer trainer) throws SQLException;
}