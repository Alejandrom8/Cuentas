package Banco.Controladores;
import Banco.Cuentas.*;

public class Tools {
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

