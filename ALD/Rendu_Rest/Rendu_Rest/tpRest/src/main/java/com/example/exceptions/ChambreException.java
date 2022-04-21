package com.example.exceptions;


public class ChambreException extends Exception {

	public ChambreException() {
	}

	public ChambreException(String msgAfficher) {
		super(msgAfficher);
	}
}
