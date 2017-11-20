package com.bioandeanexpeditions.resources;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import pe.com.erp.crypto.Encryptar;

public class Encriptar {

	public static void main(String[] args) throws KeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, GeneralSecurityException, IOException {
		// TODO Auto-generated method stub
		Encryptar encrip= new Encryptar();
		System.out.println("Aqui esta la contraseña desencriptada-->"+encrip.decrypt("dwemuQCFJjDvxQAJlXaQcA=="));
	}

}
