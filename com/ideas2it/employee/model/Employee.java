package com.ideas2it.employee.model;
         
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employee.util.DateUtil;

/**
 * <p>
 * This class is an employee pojo and it is used to store employee details.
 * </p>
 * @author  Rithikanth
 * @since   01-07-2022
 */
public class Employee {
    private String id;
    private String name;
    private String bloodGroup;
    private long phoneNumber;
    private String address;
    private String position;
    private float experience;  //
    private String emailId;
    private LocalDate dateOfBirth;
    private int age;
   
    /**
     * <p>
     * This constructs an employee with a specified id, name, bloodgroup,
     * phonenumber, address, position, experience, emailid .
     * </p>
     * @param id the id of the employee
     * @param name the name of the employee
     * @param bloodgroup the blood group of the employee
     * @param phonenumber the phone number of the employee 
     * @param address the address of the employee
     * @param position the position of the employee
     * @param experience the experience of the employee
     * @param emailid the emailid of the employee
     * @param dateOfBirth the date Of Birth of the employee
     * @param age age of the employee
     */   
    public Employee(String id, String name, 
	            String bloodGroup, long phoneNumber, 
	            String address, String position, 
	            float experience, String emailId,
                    LocalDate dateOfBirth, int age) {
       this.id = id;
       this.name = name;
       this.bloodGroup = bloodGroup;
       this.phoneNumber = phoneNumber;
       this.address = address;
       this.position = position;
       this.experience = experience;
       this.emailId = emailId;
       this.dateOfBirth = dateOfBirth; //date format
       this.age = age;       
    } 
 
    /**
     * <p>
     * This method sets the name for the employee 
     * </p>
     * @param name the name of the employee 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p> 
     * This method is used to get the name of the employee
     * </p>
     * @return name the name of the employee
     */
    public String getName() {
        return name;
    }  

    /**
     * <p> 
     * This method sets the id for the employee
     * </p>
     * @param id the id of the employee
     * @return void 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * <p> 
     * This method is used to get the id of the employee
     * </p>
     * @return id the id of the employee
     */
    public String getId() {
        return id;
    }

    /**
     * <p>
     * This method sets the blood group for th employee 
     * </p>
     * @param bloodGroup the blood group of the employee
     * @return void 
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * <p> 
     * This method is used to get the blood group of the employee
     * </p>
     * @return bloodGroup the blood group of the employee
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * <p>
     * This method sets the phone number for th employee 
     * </p>
     * @param phonenumber the phone number of the employee
     * @return void 
     */
    public void setPhoneNumber(long phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * <p> 
     * This method is used to get the phone number of the employee
     * </p>
     * @return phoneNumber the phone number of the employee
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * <p> 
     * This method sets the address for th employee 
     * </p>
     * @param address the address of the employee
     * @return void 
     */
    public void setAddress(String address) {
        this.address = address;
    } 

    /**
     * <p> 
     * This method is used to get the address of the employee
     * </p>
     * @return address the address of the employee
     */
    public String getAddress() {
        return address;
    }

    /**
     * <p>
     * This method sets the position for th employee 
     * </p>
     * @param position the position of the employee
     * @return void 
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * <p>
     * This method is used to get the position of the employee
     * </p>
     * @return position the position of the employee
     */ 
    public String getPosition() {
        return position;
    }

    /**
     * <p> 
     * This method sets the experience for th employee 
     * </p>
     * @param experience the experience of the employee
     * @return void 
     */
    public void setExperience(float experience) {
        this.experience = experience;
    }

    /**
     * <p> 
     * This method is used to get the experience of the employee
     * </p>
     * @return experience the experience of the employee
     */                                                                        
    public float getExperience() {
        return experience;                                                      
    }
 
    /**
     * <p> 
     * This method sets the email id for the employee
     * </p>
     * @param emailId the email id of the employee
     * @return void 
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * <p> 
     * This method is used to get the email id of the employee
     * </p>
     * @return emailId the email id of the employee
     */                                                                       
    public String getEmailId() {
        return emailId;                                                      
    }  

    /**
     * <p> 
     * This method sets the date of birth for the employee
     * </p>
     * @param dateOfBirth the date of birth of the employee
     * @return void 
     */
     public void setDateOfBirth(LocalDate dateOfBirth) {
         this.dateOfBirth = dateOfBirth;
     }

    /**
     * <p>
     * This method gets the date of birth of the employee
     * </p>
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    /**
     * <p>
     * This method is used to get the age of the employee
     * </p>
     */
    public int getAge() {
         return DateUtil.calculateAge(dateOfBirth);
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
                 "age : " + getAge() );
                 
    }
}