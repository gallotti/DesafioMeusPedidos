package com.gallotti.desafiomeuspedidos.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.AlertDialog;
import android.content.Context;

public class Util {

	/**
	 * 
	 * Metodo responsavel por exibir dialog com alerta
	 * 
	 * @param title - Texto do titulo do dialog
	 * @param msg - Mensagem que será exibida no dialog
	 * @param c - Contexto
	 */

	public static void callDialogAlert(String title,String msg,Context c){
		new AlertDialog.Builder(c)
		.setTitle(title)
		.setMessage(msg)
		.setPositiveButton("Ok",null)
		.setIcon(android.R.drawable.ic_dialog_alert)
		.show();
	}

	
	/**
	 * 
	 *  Metodo responsavel por validar email
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmailValid(String email) {
		String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
		Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
