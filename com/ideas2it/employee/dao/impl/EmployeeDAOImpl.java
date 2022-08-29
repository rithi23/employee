/**
 * <p> 
 * Package for dao class 
 * Copyright 2022 - ideas2it.
 * </p>
 */
package com.ideas2it.employee.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.ideas2it.employee.dao.EmployeeDAO;
import com.ideas2it.employee.model.Employee; 
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.util.ConnectionUtil;
import com.ideas2it.employee.common.Constants;

/**
 * <p>
 * This EmployeeDAO class which is used to store trainer and trainee details   
 * and it acts as the database for our project.
 * </p>
 *
 * @author RithiKanth
 * @since 23/01/2022
 */


public class EmployeeDAOImpl implements EmployeeDAO {
    
    /**
     * <p>
     * This method is used for insert Trainers in the List
     * </p>
     * @param trainer - trainer is of trainer Employee
     *
     * @return boolean - whether the trainer is inserted or not
     */
    public boolean insertTrainer(Trainer trainer) throws SQLException {
         Connection connection = null;
         PreparedStatement preparedStatement = null;
         String query;
         int status;
         try {
             connection = ConnectionUtil.getInstance();
             query = "INSERT INTO trainer(employee_Id, name, date_Of_Birth, age, "
                                 + "phone_Number, email_Id, address, blood_Group, position, " 
                                 + "experience, project, manager)"  
                                 + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";

             preparedStatement = connection.prepareStatement(query);

             preparedStatement.setString(1 , trainer.getId());
             preparedStatement.setString(2, trainer.getName());
             preparedStatement.setDate(3, java.sql.Date.valueOf(trainer.getDateOfBirth()));
             preparedStatement.setInt(4, trainer.getAge());
             preparedStatement.setLong(5, trainer.getPhoneNumber());
             preparedStatement.setString(6, trainer.getEmailId());
             preparedStatement.setString(7, trainer.getAddress());
             preparedStatement.setString(8, trainer.getBloodGroup());
             preparedStatement.setString(9, trainer.getPosition());
             preparedStatement.setFloat(10, trainer.getExperience());
             preparedStatement.setString(11, trainer.getProject());
             preparedStatement.setString(12, trainer.getManager());
             preparedStatement.executeUpdate();
             status = preparedStatement.executeUpdate();
         } catch (SQLException sqlException) {
             throw new SQLException("Cant able to insert");
         } finally {
             preparedStatement.close();
             connection.close(); 
         }
         return (status > 0) ? true : false;
    }

    /**
     * <p>
     * This method is used for insert Trainees in the List
     * </p>
     *
     * @param trainee - trainee is of trainee employee
     *
     * @return boolean - whether the trainee is inserted or not
     */
     public boolean insertTrainee(Trainee trainee) throws SQLException {
         Connection connection = null;
         PreparedStatement preparedStatement = null;
         String query;
         int status;
         try {
             connection = ConnectionUtil.getInstance();
             query = "INSERT INTO trainee(employee_Id, name, date_Of_Birth, age," +
                                 "phone_Number, email_Id, address, blood_Group, "    +
                                 "position, experience, training_Period) "         +
                                 "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
             preparedStatement = connection.prepareStatement(query);
             preparedStatement.setString(1 , trainee.getId());
             preparedStatement.setString(2, trainee.getName());
             preparedStatement.setDate(3, java.sql.Date.valueOf(trainee.getDateOfBirth()));
             preparedStatement.setInt(4, trainee.getAge());
             preparedStatement.setLong(5, trainee.getPhoneNumber());
             preparedStatement.setString(6, trainee.getEmailId());
             preparedStatement.setString(7, trainee.getAddress());
             preparedStatement.setString(8, trainee.getBloodGroup());
             preparedStatement.setString(9, trainee.getPosition());
             preparedStatement.setFloat(10, trainee.getExperience());
             preparedStatement.setString(11, trainee.getTrainingPeriod());
             status = preparedStatement.executeUpdate();
         } catch (SQLException sqlException) {
             throw new SQLException("Cant able to insert");
         } finally {
             preparedStatement.close();
             connection.close(); 
         }
         return (status > 0) ? true : false;
     }

    /**
     * <p>
     * This method is used to get the specific trainer.
     * </p>
     *
     * @param id - id of the employee
     *
     * @return trainer employee
     */
    public Trainer getTrainer(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query;
        ResultSet trainerDetail;
        try {
            connection = ConnectionUtil.getInstance();
            query = "select * from trainer where employee_Id = ? AND is_Active = 1";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            trainerDetail = preparedStatement.executeQuery(); 
            trainerDetail.next();
            Trainer trainer = new Trainer(trainerDetail.getString(Constants.employeeId),
                                       trainerDetail.getString(Constants.name),
                                       trainerDetail.getString(Constants.bloodGroup),
                                       trainerDetail.getLong(Constants.phoneNumber),
                                       trainerDetail.getString(Constants.address) ,  
                                       trainerDetail.getString(Constants.position),
                                       trainerDetail.getFloat(Constants.experience),
                                       trainerDetail.getString(Constants.project),
                                       trainerDetail.getString(Constants.manager),
                                       trainerDetail.getString(Constants.emailId),
                                       trainerDetail.getDate(Constants.dateOfBirth).toLocalDate(),
                                       trainerDetail.getInt(Constants.age));
                                       getTrainees(trainerDetail.getString(Constants.employeeId),trainer);
            return trainer;
        } catch (SQLException sqlException) {
             throw new SQLException("Cant able to get trainer");
        } finally {
             preparedStatement.close();
             connection.close(); 
        }
        
    }

    /**
     * <p>
     * This method is used to get the specific trainee.
     * </p>
     *
     * @param id - id of the employee
     *
     * @return trainee employee
     */
    public Trainee getTrainee(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query;
        ResultSet traineeDetail;
        try {
            connection = ConnectionUtil.getInstance();
            query = "select * from trainee where employee_Id = ? AND is_Active = 1";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            traineeDetail = preparedStatement.executeQuery(); 
            traineeDetail.next(); 
            Trainee trainee = new Trainee (traineeDetail.getString(Constants.employeeId),
                                       traineeDetail.getString(Constants.name),
                                       traineeDetail.getString(Constants.bloodGroup),
                                       traineeDetail.getLong(Constants.phoneNumber),
                                       traineeDetail.getString(Constants.address),  
                                       traineeDetail.getString(Constants.position),
                                       traineeDetail.getFloat(Constants.experience) ,
                                       traineeDetail.getString(Constants.trainingPeriod),
                                       traineeDetail.getString(Constants.emailId),
                                       traineeDetail.getDate(Constants.dateOfBirth).toLocalDate(),
                                       traineeDetail.getInt(Constants.age));
                                       trainee.setTrainerName(traineeDetail.getString("trainer_Id"));
            return trainee; 
        } catch (SQLException sqlException) {
            throw new SQLException("Cant able to get trainee");
        } finally {
            preparedStatement.close();
            connection.close(); 
        }             
    }

    /**
     * @inheritDocs
     */
    public boolean updateAddressForTrainer(String trainerId, String updateValue) throws SQLException  {
        Connection connection = null;
        String query;
        PreparedStatement preparedStatement = null; 
        int status;
        try {
            connection = ConnectionUtil.getInstance();
            query = "update trainer set address = ? where employee_Id = ? AND is_Active = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,updateValue);
            preparedStatement.setString(2,trainerId);
            preparedStatement.setBoolean(3,true);
            status = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new SQLException("Cant able to update");
        } finally {
            preparedStatement.close();
            connection.close();    
        }        
        return (status > 0) ? true : false;
    }

    /**
     * @inheritDocs
     */
    public boolean updatePositionForTrainer(String trainerId, String updateValue) throws SQLException {
        Connection connection = null;
        String query;
        PreparedStatement preparedStatement = null; 
        int status;
        try {
            connection = ConnectionUtil.getInstance();
            query = "update trainer set position = ? where employee_Id = ? AND is_Active = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,updateValue);
            preparedStatement.setString(2,trainerId);
            preparedStatement.setBoolean(3,true);
            status = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new SQLException("Cant able to update");
        } finally {
            preparedStatement.close();
            connection.close();    
        }        
        return (status > 0) ? true : false;
    }
    
    /**
     * @inheritDocs
     */
    public boolean updateExperienceForTrainer(String trainerId, float updatedInput) throws SQLException {
        Connection connection = null;
        String query;
        PreparedStatement preparedStatement = null; 
        int status;
        try {
            connection = ConnectionUtil.getInstance();
            query = "update trainer set experience = ? where employee_Id = ? AND is_Active = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1,updatedInput);
            preparedStatement.setString(2,trainerId);
            preparedStatement.setBoolean(3,true);
            status = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new SQLException("Cant able to update");
        } finally {
            preparedStatement.close();
            connection.close();    
        }        
        return (status > 0) ? true : false;
    }
    
    /**
     * @inheritDocs
     */
    public boolean updateAddressForTrainee(String traineeId,String updateValue) throws SQLException {
        Connection connection = null;
        String query;
        PreparedStatement preparedStatement = null; 
        int status;
        try {
            connection = ConnectionUtil.getInstance();
            query = "update trainee set address = ? where employee_Id = ? AND is_Active = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, updateValue);
            preparedStatement.setString(2, traineeId);
            preparedStatement.setBoolean(3, true);
            status = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new SQLException("Cant able to update");       
        } finally {
            preparedStatement.close();
            connection.close();    
        }        
        return (status > 0) ? true : false;
    }
    
    /**
     * {@inheritDocs}      
     */
    public boolean updatePositionForTrainee(String traineeId, String updateValue) throws SQLException {
        Connection connection = null;
        String query;
        PreparedStatement preparedStatement = null; 
        int status;
        try {
            connection = ConnectionUtil.getInstance();
            query = "update trainee set position = ? where employee_Id = ? AND is_Active = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,updateValue);
            preparedStatement.setString(2,traineeId);
            preparedStatement.setBoolean(3,true);
            status = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new SQLException("Cant able to update");
        } finally {
            preparedStatement.close();
            connection.close();    
        }        
        return (status > 0) ? true : false;
    }
    
    /**
     * @inheritDocs
     */
    public boolean updateExperienceForTrainee(String traineeId, float updatedInput) throws SQLException {
        Connection connection = null;
        String query;
        PreparedStatement preparedStatement = null; 
        int status;
        try {
            connection = ConnectionUtil.getInstance();
            query = "update trainee set experience = ? where employee_Id = ? AND is_Active = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1,updatedInput);
            preparedStatement.setString(2,traineeId);
            preparedStatement.setBoolean(3,true);
            status = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new SQLException("Cant able to update");
        } finally {
            preparedStatement.close();
            connection.close();    
        }        
        return (status > 0) ? true : false;
    }

    /**
     * <p>
     * This method is used to delete the specific trainer.
     * </p>
     *
     * @param employeeId - id of the employee
     */
    public boolean deleteTrainer(String employeeId) throws SQLException {
        Connection connection = null;
        String query;
        PreparedStatement preparedStatement = null; 
        int status;
        try {
            connection = ConnectionUtil.getInstance();
            query = "update trainer set is_Active =? where employee_Id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setString(2, employeeId);
            status = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new SQLException("Cant able to delete");
        } finally {
            preparedStatement.close();
            connection.close();    
        }  
        return (status > 0) ? true : false;
    } 
  
    /**
     * <p>
     * This method is used to delete the specific trainee.
     * </p>
     *
     * @param employeeId - id of the employee
     */
    public boolean deleteTrainee(String employeeId) throws SQLException {
        Connection connection = null;
        String query;
        PreparedStatement preparedStatement = null; 
        int status;
        try {
            connection = ConnectionUtil.getInstance();
            query = "update trainee set is_Active =? where employee_Id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setString(2, employeeId);
            status = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new SQLException("Cant able to delete");
        } finally {
            preparedStatement.close();
            connection.close();    
        }  
        return (status > 0) ? true : false;
    }    

    /**
     * @inheritDocs
     */
    public boolean checkUniquePhoneNumber(long phoneNumber) throws SQLException {
        Connection connection = ConnectionUtil.getInstance();
        String query = "select exists(select * from trainer,trainee where" 
                        + "trainer.phone_Number = ? or trainee.phone_Number = ?) as isExists;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, phoneNumber);
        preparedStatement.setLong(2, phoneNumber);
        ResultSet employee = preparedStatement.executeQuery(); 
        employee.next(); 
        return(1 == employee.getInt("isExists")) ? true : false;       
    }

    /**
     * @inheritDocs
     */
    public List<Trainer> showTrainers() throws SQLException {
        Connection connection = null;
        String query;
        PreparedStatement preparedStatement = null;
        ResultSet trainerDetail;
        List<Trainer> trainers = new ArrayList<Trainer>();
        try {
            query = "select * from trainer";
            connection = ConnectionUtil.getInstance();
            preparedStatement = connection.prepareStatement(query); 
            trainerDetail = preparedStatement.executeQuery();
            while(trainerDetail.next()) {
                Trainer trainer = new Trainer(trainerDetail.getString(Constants.employeeId),
                                           trainerDetail.getString(Constants.name),
                                           trainerDetail.getString(Constants.bloodGroup),
                                           trainerDetail.getLong(Constants.phoneNumber),
                                           trainerDetail.getString(Constants.address),  
                                           trainerDetail.getString(Constants.position),
                                           trainerDetail.getFloat(Constants.experience),
                                           trainerDetail.getString(Constants.project),
                                           trainerDetail.getString(Constants.manager),
                                           trainerDetail.getString(Constants.emailId),
                                           trainerDetail.getDate(Constants.dateOfBirth).toLocalDate(),
                                           trainerDetail.getInt(Constants.age)); 
                                       trainers.add(trainer);
            }  
        } catch (SQLException sqlException) {
            throw new SQLException("Cant able to show trainer");
        } finally {
            preparedStatement.close();
            connection.close();    
        }    
        return trainers;      
    }
       
    /**
     * @inheritDocs
     */
    public List<Trainee> showTrainees() throws SQLException {
        Connection connection = null;
        String query;
        PreparedStatement preparedStatement = null;
        ResultSet traineeDetail;
        List<Trainee> trainees = new ArrayList<Trainee>();
        try {
            query = "SELECT * from trainee WHERE trainer_Id IS NULL";
            connection = ConnectionUtil.getInstance();
            preparedStatement = connection.prepareStatement(query); 
            traineeDetail = preparedStatement.executeQuery();
            while (traineeDetail.next()) {
                Trainee trainee = new Trainee (traineeDetail.getString(Constants.employeeId),
                                          traineeDetail.getString(Constants.name),
                                          traineeDetail.getString(Constants.bloodGroup),
                                          traineeDetail.getLong(Constants.phoneNumber),
                                          traineeDetail.getString(Constants.address),  
                                          traineeDetail.getString(Constants.position),
                                          traineeDetail.getFloat(Constants.experience),
                                          traineeDetail.getString(Constants.trainingPeriod),
                                          traineeDetail.getString(Constants.emailId),
                                          traineeDetail.getDate(Constants.dateOfBirth).toLocalDate(),
                                          traineeDetail.getInt(Constants.age));
                                          trainee.setTrainerName(traineeDetail.getString("trainer_Id"));
                                          trainees.add(trainee);
           }
        } catch (SQLException sqlException) {
            throw new SQLException("Cant able to show trainer");
        } finally {
            preparedStatement.close();
            connection.close();    
        }    
        return trainees;      
    }

    /**
     * @inheritDocs
     */
    public boolean addTrainerForTrainees(Trainer trainer,Trainee trainee) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query;
        int trainerStatus;
        int traineeStatus;
        try {
            connection = ConnectionUtil.getInstance();
            query = "update trainer set trainee_Id = ? where employee_Id = ? AND is_Active = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,trainee.getId());
            preparedStatement.setString(2,trainer.getId());
            preparedStatement.setBoolean(3, true);
            trainerStatus = preparedStatement.executeUpdate();
            query = "update trainee set trainerId = ? where employeeId = ? AND is_Active = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,trainer.getId());
            preparedStatement.setString(2,trainee.getId());
            preparedStatement.setBoolean(3,true);
            traineeStatus = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new SQLException("Cant able to assign trainer");
        } finally {
            preparedStatement.close();
            connection.close();    
        }   
        return (trainerStatus > 0 && traineeStatus > 0) ? true : false;                    
    }

    /**
     * @inheritDocs
     */        
    public void getTrainees(String trainerId, Trainer trainer) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query;
        ResultSet traineeDetail;
        try {
            connection = ConnectionUtil.getInstance();
            query = "select * from trainee where trainer_id = ? AND is_Active = 1";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,trainerId);
            traineeDetail = preparedStatement.executeQuery();  
            while (traineeDetail.next()) {
                trainer.setTrainees(traineeDetail.getString(Constants.employeeId));
            }
        } catch (SQLException sqlException) {
            throw new SQLException("Cant able to get trainees");
        } finally {
            preparedStatement.close();
            connection.close();    
        }  
    }
}