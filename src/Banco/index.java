package Banco;

import java.text.ParseException;
import java.util.Scanner;

import Banco.Controladores.*;

public class index {
	
	public final static Regist control = new Regist();
	
	public static void main (String [] args) {
		//Banco
		config cf = new config();
		Scanner input = new Scanner(System.in);
		System.out.println("Bienvenido al Banco " + cf.nombre_banco);
		
		String[] mainOptions = {"cuentas", "ejecutivos", "salir"};
		String[] countsOptions = {"crear cuenta de débito", "crear cuenta de crédito"};
		String menuText = makeMenu(mainOptions);
		String countsText = makeMenu(countsOptions);
		
		String nombre, estado, correo, telefono;
		float saldo;
		int res;
		
		do {
			
			System.out.print(menuText);
			res = Integer.parseInt(input.nextLine());
			
			switch(res) {
				case 1:
					System.out.print(countsText);
					int num = Integer.parseInt(input.nextLine());
					switch(num) {
						case 1:
							System.out.print("Introduce el nombre del usuario: ");
							nombre = input.nextLine();
							System.out.print("Introduce el estado: ");
							estado = input.nextLine();
							System.out.print("Introduce el correo: ");
							correo = input.nextLine();
							System.out.print("Introduce el telefono: ");
							telefono = input.nextLine();
							System.out.print("Introduce el saldo inicial de la cuenta: ");
							saldo = Float.parseFloat(input.nextLine());
							
							control.nuevaCuentaDebito(nombre, estado, correo, telefono, saldo);
							break;
						case 2:
							System.out.print("Introduce el nombre del usuario: ");
							nombre = input.nextLine();
							System.out.print("Introduce el estado: ");
							estado = input.nextLine();
							System.out.print("Introduce el correo: ");
							correo = input.nextLine();
							System.out.print("Introduce el telefono: ");
							telefono = input.nextLine();
							
							try {
								control.nuevaCuentaCredito(nombre, estado, correo, telefono);
							}catch(ParseException e) {
								System.out.println("Hubo un error al crear la cuenta: " + e);
							}
						case 3:
							
							break;
						default:
							System.out.println("Opcion incorrecta");
							break;
					}
					break;
				case 2:
					
					break;
				case 3:
					System.out.print("Fin del programa");
					break;
				default:
					System.out.println("Opcion incorrecta");
					break;
			}
			
		}while(res != 3);
		
		input.close();
		
	}
	
	public static String makeMenu(String[] op) {
		String menuText = "\n opciones \n";
		for(int i = 1; i <= op.length; i++) {
			menuText += i + ". " + op[i-1] + "\n";
		}
		menuText += "\n respuesta: ";
		return menuText;
	}
	
	public static boolean Login() {
		Scanner input = new Scanner(System.in);
		System.out.print("Menú \n 1. iniciar sesion \n 2.registrar ejecutivo \n\n respuesta: ");
		int res = Integer.parseInt(input.nextLine());
		switch(res) {
		case 1:
			System.out.print("Ingresa tu RFC: ");
			String rfc = input.nextLine();
			System.out.println("Ingresa tu contraseña: ");
			String pass = input.nextLine();
			control.SearchExecutive(rfc, pass);
			break;
		case 2:
			break;
		default:
			break;
		}
		input.close();
		return false;
	}
}
