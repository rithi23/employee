/**
 * <p>
 * This is the package for exception 
 */
package com.ideas2it.employee.exception;

/**
 * <p>
 * This class handles the custom exceptions
 * </p>
 * @author Rithikanth
 * @since 23/01/2022
 */

import java.lang.Exception;

public class InvalidChoiceException extends Exception {
    public InvalidChoiceException(String message) {
        super(message);
    }
}
    