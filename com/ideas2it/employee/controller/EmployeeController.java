/**
 * %W% %E% RithiKanth B C
 *
 * Copyright (c) 2000 - 2020 ideas2it, Inc. All Rights Reserved.
 *
 * This software(iassistant)is the confidential and 
 * proprietary information of ideas2it,
 * Inc. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with ideas2it.
 *
 * IDEAS2IT MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. IDEAS2IT SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package com.ideas2it.employee.controller;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

import com.ideas2it.employee.exception.InvalidChoiceException;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Trainee;
import com.ideas2it.employee.model.Trainer;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.impl.EmployeeServiceImpl; 
import com.ideas2it.employee.util.DateUtil;
import com.ideas2it.employee.util.StringUtil;



/**
 * <p>
 * This EmployeeController class is  responsible for getting
 * user inputs and displays results for users 
 * </p>
 *
 * @author  Rithikanth
 * @since   01-07-2022
 */
public class EmployeeController {
      private EmployeeService employeeService = new EmployeeServiceImpl();
      private Logger logger = LoggerFactory.getLogger(EmployeeController.class);
      private Scanner input = new Scanner(System.in);

    /*
     * <p>
     * This is the main class and it is the starting method of this program
     * </p>
     *
     * @param args {@link String} - argument of main class
     */
    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
        employeeController.init();
    }

    /**
     * <p>
     * This init method contains adding,displaying,
     * updating,deleting employees functions.
     * </p>
     *
     * @return void
     */
    public void init() {
          boolean flag = true;
          boolean status;
          int choice;
          do { 
              try {     
                  logger.info("Please Enter 1 to add Details");
                  logger.info("Please Enter 2 to display Details");
                  logger.info("Please Enter 3 to update Details");
                  logger.info("Please Enter 4 to Delete Details");
                  logger.info("Please Enter 5 to Associate Trainer and Trainee");
                  logger.info("Please Enter 6 to exit");
                  choice = input.nextInt();
                  boolean checkChoice;
                  switch (choice) {
                  case 1 :
                      addEmployee(); 
                      break;
                  case 2 :
                      int displayChoice = getEmployeChoice();                  
                      String displayType = getId();                        
                      displayEmployeeById(displayChoice,displayType);           
                      break;
                  case 3 :
                      try {
                          int updateChoice  = getEmployeChoice();               
                          String updateType = getId(); 
                          int menuChoice = viewUpdationMenu();                         
                          System.out.println("Please Enter the new value");
                          String updateValue = input.next();
                          status = employeeService.updateEmployeeById(updateChoice, 
                                       updateType, menuChoice, updateValue) ; 
                          if (status) {
                              logger.info(" Updated Successfully");
                          } else {
                              logger.info("OOPS Cant Able To Delete");
                          }
                      } catch (SQLException e) {
                           e.getMessage();
                      }
                      break; 
                  case 4 : 
                      try {
                          int deleteChoice = getEmployeChoice();                                  
                          String deleteEmployeeId = getId();                               
                          status = employeeService.deleteEmployeeById(deleteChoice,deleteEmployeeId);    
                          if (status) {
                              logger.info("Deleted Successfully");
                          } else {
                              logger.info("OOPS Cant Able To Delete");
                          }
                      } catch(SQLException sqlException) {
                          sqlException.getMessage();
                      }
                      break; 
                  case 5 :
                      assignTrainer();
                      break;
                  case 6 :
                      flag = !flag;
                      break;
                  default :
                      logger.info("Please Enter the Valid input");
                  }
              } 
              catch(InputMismatchException e) {
                  logger.info("Please Enter the valid input");
                  input.nextLine();
                  init();
              }       
        } while (flag);   
    }

    /**
     * <p>
     * This method is for adding the employee details 
     * ie. getting id, name, bloodgroup, phonenumber,
     * address, position, emailid, experience from the user
     * </p>
     *
     * @return boolean 
     */
     public void addEmployee() {

        int addChoice = getEmployeChoice();
        boolean addStatus;
        int age = 0;

        logger.info("Enter the Employee id :");
        String id = getvalidinput("^(i22)[0-9]{0,9}$");

        logger.info("Please Enter your Name :");
        String name = getvalidinput("[A-Za-z]+\\s[A-Z]{1,5}$");
                  
        logger.info("Please Enter your Blood group ");
        String bloodGroup = input.next();

        Long phoneNumber = null;
        try {
            phoneNumber = checkUniquePhoneNumber();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        }
        
        logger.info("Thats great please mention your address");
	String address = input.next();

        logger.info("Please specify your designation");	
	String position = getvalidinput("[A-Za-z0-9]{1,}(.){1,}") ;   

        logger.info("Please specify your emailID");	
	String emailId = getvalidinput("[a-z](.)?[A-Za-z]?(@)?[a-z](.)(com)$");
                                                   
        logger.info("Please Mention your years of experience");
	float experience = Float.parseFloat(getvalidinput("^[0-9(.)]{2}"));
        
        LocalDate dateOfBirth;
        while (true) {
            try {
                logger.info("Please enter your Date of Birth in this format dd-mm-yyyy");
                dateOfBirth = DateUtil.convertToDate(input.nextLine());
                break;
            } catch (DateTimeParseException dateTimeParseException) {
                logger.error(dateTimeParseException.getMessage());     
            }
        }
        switch(addChoice) {
        case 1 :
            logger.info("Please specify your Project Name");	
	    String project = input.next();

            logger.info("Please specify your Manager");	
	    String manager = input.next();
     
            Trainer trainer = new Trainer( id, name, bloodGroup, phoneNumber, 
	                                   address,position, experience, project,
                                           manager, emailId, dateOfBirth, age);
           try {    
               employeeService.createTrainer(trainer) ;
           } catch(SQLException e) {
               e.getMessage();
           }
           break;
	case 2 :
             logger.info("Please specify your Training Period");	
	     String trainingPeriod = input.next();
            
	     Trainee trainee = new Trainee( id, name, bloodGroup, phoneNumber, 
	                                    address, position, experience, 
                                            trainingPeriod, emailId, dateOfBirth, age);
             try {
                 employeeService.createTrainee(trainee);
             } catch (SQLException e) {
                 e.getMessage();
             } 
        }
    }

    /**
     * <p> 
     * This method is to display the Employee 
     * </p>
     *
     * @param choiceType - it is a choice i.e trainer or trainee choice
     * @param id - id of the employee
     */
    public void displayEmployeeById(int choiceType, String id) {
        switch(choiceType) {
        case 1 :
            try {
                Trainer trainer = employeeService.getTrainer(id);
                if (null == trainer) {
                    logger.error("There are no more trainer in this id");
                    break;
                } else {
                    logger.info(trainer.toString());                       
                }
             } catch(SQLException e) {
                e.getMessage();
            }
            break;
       
        case 2 :
            try {
                Trainee trainee = employeeService.getTrainee(id);
                if (null == trainee) {
                    logger.error("There are no more trainee in this id");
                    break;
                } else {
                    logger.info(trainee.toString());
               }
            } catch (SQLException e) {
              e.getMessage();
            }
        }    
    } 
      
    /*
     * <p> 
     * This method is used to get the user choice
     * i.e Trainer or Trainee
     * </p>
     *
     * @return choice - choice is either Trainer or Trainee
     */
    public int getEmployeChoice() {
        int choice = 0;
        try {
            logger.info("PLease Enter trainer or trainee");
            logger.info("Enter 1 For Trainer ");
     	    logger.info("Enter 2 For Trainee ");
            choice = input.nextInt();
            if (choice > 0 && choice < 3) {
                return choice;      
            }
            throw new InvalidChoiceException("Please enter the correct choice");            
        } catch (NumberFormatException | InputMismatchException
                | InvalidChoiceException exception) {
            input.nextLine();
            logger.error(exception.getMessage());
            exception.printStackTrace();
            getEmployeChoice();
        }        
        return choice;
    }
    
    /**
     * <p> 
     * This method is used to get the id
     * </p>
     *
     * @return id - id is employeeid
     */ 
    public String getId() { 
        logger.info("Please Enter the id");
        return getvalidinput("^(i22)[0-9]{0,9}$");
    }
    
    /**
     * <p>
     * This method cotains values to be updated
     * i.e address or position or experience
     * </p>
     *
     * @return values - values are new values to be updated 
     * i.e address, position, experience.
     */
    public int viewUpdationMenu() {
        int choice = 0;
        try {
            logger.info("UPDATION MENU");
            logger.info("Enter 1 for address");
            logger.info("Enter 2 for position");
            logger.info("Enter 3 for experience");
            choice = input.nextInt();
            if (choice > 0 && choice < 4) {
                return choice;      
            }
            throw new InvalidChoiceException("Please enter the correct choice");            
        } catch (NumberFormatException | InputMismatchException | InvalidChoiceException exception) {
            input.nextLine();
            logger.error(exception.getMessage());
            exception.printStackTrace();
            getEmployeChoice();
        }        
        return choice;
    }

    /**
     * <p>
     * This method checks whether the input is valid until
     * user gives the valid input
     * </p>
     *
     * @param pattern- {@link String }String pattern is of constant type
     * and the input should match the pattern
     */
    public String getvalidinput(String pattern) { 
        boolean isValid = false; 
        String userInput;
        do {
            userInput = input.nextLine();
            isValid =  StringUtil.checkValidInput(userInput, pattern);  
            if (!isValid) {
                logger.info("Please enter the valid input");
            }
        } while (!isValid);
        return userInput;
    }

    /**
     * <p>
     * This method is used to check the number is unique
     * </p>
     */
    public Long checkUniquePhoneNumber() throws SQLException {
        long phoneNumber;
        boolean result;
        do {
            logger.info(" please enter your PhoneNumber");  
            phoneNumber = Long.parseLong(getvalidinput("^[6-9][0-9]{9}"));
            result = employeeService.checkUniquePhoneNumber(phoneNumber);
            if (result) {
                logger.error("Phone number already exists please enter another number");
                checkUniquePhoneNumber();   
            }                              
        } while (result);
        return phoneNumber;
    }

    /**
     * <p> 
     * This method shows the list of Trainers
     * </p>
     */
    public void showTrainers() {
        try {
            for (Trainer trainer : employeeService.showTrainers()) {
                logger.info(trainer.getName() + trainer.getId());
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        }
    }

    /**
     * <p> 
     * This method shows the list of Trainers
     * </p>
     */
    public void showTrainees() {
        try {
            for (Trainee trainee : employeeService.showTrainees()) {
                logger.info(trainee.getName() + trainee.getId());
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        }
    }

    /** 
     * <p>
     * This method shows the trainees list size that were appointed for trainer
     * if the trainer id is invalid it asks for valid id until the user enters the
     * valid id.
     * </p>
     */
    public void showListOfTraineesAppointed() throws SQLException {
        logger.info("Enter the trainer id to assign for trainee");
        Trainer trainer = employeeService.getTrainer(getvalidinput("^(i22)[0-9]{0,9}$"));
        if (null == trainer) {
            logger.info("Please Enter the Valid trainer id");
            showListOfTraineesAppointed();
        } else {
            try {
                String traineeListSize = String.valueOf(trainer.getTrainees().size());
                logger.info(traineeListSize);
            } catch(NullPointerException e) {
                logger.info("There are no trainees So please assign the trainee");
                addTrainee(trainer);   
            }
        }   
    }

    /**
     * <p>
     * This method is for assigning trainer for Trainees
     * </p>
     */
    public void addTrainerforTrainees() throws SQLException {
        logger.info("Please Enter the Trainer id to bee associated");
        Trainer trainer = employeeService.getTrainer(getvalidinput("^(i22)[0-9]{0,9}$"));
        if(null == trainer) {
            logger.info("Please enter the valid id");
            addTrainerforTrainees();
        } else if (trainer.getTrainees().size() >= 4) {
            logger.info("Please choose another trainer");
            addTrainerforTrainees();
        } else {
            addTrainee(trainer);
        }
    }

    /**
     * <p>
     *  This method shows the list of trainees and adds trainer for trainees
     * </p>
     */
    public void addTrainee(Trainer trainer) throws SQLException {
        showTrainees();
        System.out.println("Please enter the trainee id");
        Trainee trainee = employeeService.getTrainee(getvalidinput("^(i22)[0-9]{0,9}$"));
        if (null == trainee) {
            logger.info("Please Enter the valid id");
            addTrainee(trainer);
        } else  {
            boolean status = employeeService.addTrainerForTrainees(trainer,trainee) ;
            if (status == true) {
                logger.info("Trainer assigned");
            } else {
                    logger.info("Cant able to assign");
            }   
        }   
    }

    /**
     * <p> 
     * This method is for assigning trainers
     * </p>
     */
    public void assignTrainer() {
        try {
            showTrainers(); 
            showListOfTraineesAppointed(); 
            addTrainerforTrainees(); 
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }                   

//its a sample
