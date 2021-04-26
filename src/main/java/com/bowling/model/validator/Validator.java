package com.bowling.model.validator;

/**
 * This interface help us to validate our model objects and implements
 * as many as we want.
 * @author Sistemas
 *
 * @param <T>
 */
public interface Validator<T> {

	default boolean validate(T s) {
		return true;	
	}
	default boolean validate() {
		return true;
	}

}
