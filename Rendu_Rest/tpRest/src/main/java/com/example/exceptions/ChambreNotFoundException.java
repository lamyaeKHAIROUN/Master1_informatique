package com.example.exceptions;

public class ChambreNotFoundException  extends ChambreException{

	public ChambreNotFoundException() {
		
	}


	public ChambreNotFoundException(String msgAfficher) {
		super(msgAfficher);
		
	}
}
