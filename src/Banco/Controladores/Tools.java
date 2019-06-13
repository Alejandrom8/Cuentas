package Banco.Controladores;

import java.util.Calendar;
import Banco.Cuentas.*;

public class Tools {
	
	public String makeMenu(String[] op, String title) {
		String menuText = "\n	menu ~ " + title + "\n\n";
		for(int i = 1; i <= op.length; i++) {
			menuText += "	" + i + ". " + op[i-1] + "\n";
		}
		menuText += "\n respuesta: ";
		return menuText;
	}
	
	public String getDate() {
		int year, month, day;
		String format;
		Calendar date = Calendar.getInstance();
		year = date.get(Calendar.YEAR);
		month = date.get(Calendar.MONTH) + 1;
		day = date.get(Calendar.DAY_OF_MONTH);
		format = day + "/" + month + "/" + year;
		return format;
	}
	
	public String generateId(int digits) {
		String id = "";
		for(int i = 0; i < digits; i++) {
			int dig = (int) Math.round(Math.random()*10);
			id += dig;
		}
		return id;
	}
	
	public String[] popString(String[] oldarray, String item) {
		int len = oldarray.length;
		String[] newArray = new String[len+1];
		System.arraycopy(oldarray, 0, newArray, 0, len);
		newArray[len] = item;
		return newArray;
	}
	
	public Ejecutivo[] popEjecutivo(Ejecutivo[] oldarray, Ejecutivo item) {
		int len = oldarray.length;
		Ejecutivo[] newArray = new Ejecutivo[len+1];
		System.arraycopy(oldarray, 0, newArray, 0, len);
		newArray[len] = item;
		return newArray;
	}
	
	public Debito[] popDebito(Debito[] oldarray, Debito item) {
		int len = oldarray.length;
		Debito[] newArray = new Debito[len+1];
		System.arraycopy(oldarray, 0, newArray, 0, len);
		newArray[len] = item;
		return newArray;
	}
	
	public Credito[] popCredito(Credito[] oldarray, Credito item) {
		int len = oldarray.length;
		Credito[] newArray = new Credito[len+1];
		System.arraycopy(oldarray, 0, newArray, 0, len);
		newArray[len] = item;
		return newArray;
	}
	
	public Nomina[] popNomina(Nomina[] oldarray, Nomina item) {
		int len = oldarray.length;
		Nomina[] newArray = new Nomina[len+1];
		System.arraycopy(oldarray, 0, newArray, 0, len);
		newArray[len] = item;
		return newArray;
	}
	
}

