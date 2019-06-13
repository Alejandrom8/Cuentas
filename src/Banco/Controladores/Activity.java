package Banco.Controladores;
import Banco.Cuentas.*;
import Banco.Controladores.Explorer;
/*
 * Clase que implementa las operaciones de retiro, depósito y pago de tarjeta
 */

public class Activity{
	
	public Explorer exp;
	
  public Activity() {

	  this.exp = new Explorer();
  }
  
	public boolean cambiarDatoDebito(Debito[] cuentasDebito, int no_cliente, Debito obj) {
		boolean ready = false;
		for(int i = 0; i < cuentasDebito.length; i++) {
			if(cuentasDebito[i].getNo_cliente() == no_cliente) {
				cuentasDebito[i] = obj;
				ready = true;
				break;
			}
		}
		return ready;
	}
	
	public boolean cambiarDatoCredito(Credito[] cuentasCredito, int no_cliente, Credito obj) {
		boolean ready = false;
		for(int i = 0; i < cuentasCredito.length; i++) {
			if(cuentasCredito[i].getNo_cliente() == no_cliente) {
				cuentasCredito[i] = obj;
				ready = true;
				break;
			}
		}
		return ready;
	}
	
	public boolean cambiarDatoNomina(Nomina [] cuentasNomina, int no_cliente, Nomina obj) {
		boolean ready = false;
		for(int i = 0; i < cuentasNomina.length; i++) {
			if(cuentasNomina[i].getNo_cliente() == no_cliente) {
				cuentasNomina[i] = obj;
				ready = true;
				break;
			}
		}
		return ready;
	}
  
  public boolean retiroDebito(String tipo, double monto, int cliente, Debito [] where) {
		  Debito user = exp.searchByNumOfClientDebito(where, cliente);
		  if(user != null) {
			  if(user.getSaldo() >= monto) {
				  user.setSaldo(user.getSaldo() - monto);
				  boolean resultado = cambiarDatoDebito(where, cliente, user);
				  if(resultado) {
					  System.out.println("Retiro " + monto + " . Queda un saldo total de " + user.getSaldo());
					  return true;
				  }else {
					  System.out.println("No se logro hacer el retiro");
				  }
			  }else {
				System.out.println("saldo insuficiente (saldo: " + user.getSaldo() + ")");  
			  }
		  }else {
			  System.out.println("Usuario no encontrado");
		  }
	  return false;
  }

	public boolean retiroCredito(String tipo, double monto, int cliente, Credito [] where){
		Credito userCredito = exp.searchByNumOfClientCredito(where, cliente);
		  if(userCredito != null) {
			  if(userCredito.getImporte_credito() >= monto) {
				  userCredito.setImporte_credito(userCredito.getImporte_credito() - monto);
				  boolean resultado = cambiarDatoCredito(where, cliente, userCredito);
				  if(resultado) {
					  System.out.println("Retiro " + monto + " . Queda un saldo total de " + userCredito.getImporte_credito());
					  return true;
				  }else {
					  System.out.println("No se logro hacer el retiro");
				  }
			  }else {
				System.out.println("saldo insuficiente (saldo: " + userCredito.getImporte_credito() + ")");  
			  }
		  }else {
			  System.out.println("Usuario no encontrado");
		  }
		  return false;
	}
	
	public boolean retiroNomina(String tipo, double monto, int cliente, Nomina [] where){
		Nomina userNomina = exp.searchByNumOfClientNomina(where, cliente);
		  if(userNomina != null) {
			  if(userNomina.getSaldo() >= monto) {
				  userNomina.setSaldo(userNomina.getSaldo() - monto);
				  boolean resultado = cambiarDatoNomina(where, cliente, userNomina);
				  if(resultado) {
					  System.out.println("Retiro " + monto + " . Queda un saldo total de " + userNomina.getSaldo());
					  return true;
				  }else {
					  System.out.println("No se logro hacer el retiro");
				  }
			  }else {
				System.out.println("saldo insuficiente (saldo: " + userNomina.getSaldo() + ")");  
			  }
		  }else {
			  System.out.println("Usuario no encontrado");
		  }
		  
		 return false;
	}
}