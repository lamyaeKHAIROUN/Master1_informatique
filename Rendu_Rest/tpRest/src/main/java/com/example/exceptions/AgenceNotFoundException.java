package com.example.exceptions;

public class AgenceNotFoundException extends AgenceException {
	
	public AgenceNotFoundException() {}
	public AgenceNotFoundException(String msgAfficher) {
		super(msgAfficher);
	}


}
