package org.target.casestudy.exception;
/**
 * This class represents the data related  unchecked exceptions.
 * 
 * @author Asad Islam
 * Last Updated: Feb 7, 2016
 */
public class DataAccessException extends RuntimeException {
	
	/**
	 * Holds the value of serial version number.
	 */
	private static final long serialVersionUID = 1L;
	

	
	/**
	 * Instantiates a new data access exception.
	 *
	 * @param code the code
	 */
	public DataAccessException(String code) {
		super(code);
	}
	
	/**
	 * Instantiates a new data access exception.
	 *
	 */
	public DataAccessException() {
		super();
	}
	
	/**
	 * Instantiates a new data access exception.
	 *
	 * @param exception - Throwable Object
	 */
	public DataAccessException(Throwable exception) {
		super(exception);
	}
	
	 /**
	 * Make Object non cloneable
	 * @return Object
	 * @throws CloneNotSupportedException
	 */
	@Override
	public final Object clone() throws java.lang.CloneNotSupportedException {
		throw new java.lang.CloneNotSupportedException();
	}
}
