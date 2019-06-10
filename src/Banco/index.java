package Banco;

import java.text.ParseException;
import java.util.Scanner;

import Banco.Controladores.*;

public class index {
	
	public final static Regist control = new Regist();
	
	public static String makeMenu(String[] op) {
		String menuText = "\n opciones \n";
		for(int i = 1; i <= op.length; i++) {
			menuText += i + ". " + op[i-1] + "\n";
		}
		menuText += "\n respuesta: ";
		return menuText;
	}
	
	public static boolean init() {
		
		Scanner input = new Scanner(System.in);
		String[] menu = {"iniciar sesion", "registrar ejecutivo"};
		String[] menuRegistro = {"Registrar ejecutivo de Débito", "Registrar ejecutivo de Crédito", "Registrar ejecutivo de Nómina"};
		String menuText = makeMenu(menu);
		System.out.print(menuText);
		
		int res = Integer.parseInt(input.nextLine());
		
		switch(res) {
			case 1:
				//Login
				boolean confirm = false;
				int trys = 0;
				
				do {
					
					System.out.print("Ingresa tu RFC: ");
					String rfc = input.nextLine();
					System.out.println("Ingresa tu contraseña: ");
					String pass = input.nextLine();
					confirm = control.login(rfc, pass);
					if(!confirm) {
						trys++;
					}else {
						return true;
					}
					
				}while(!confirm && trys <= 2);
				
				break;
			case 2:
				//Registro
				System.out.println(menuRegistro);
				int opcionRegistro = Integer.parseInt(input.nextLine());
				String tipoEjecutivo;
				
				switch(opcionRegistro) {
					case 1:
						tipoEjecutivo = "débito";
						break;
					case 2:
						tipoEjecutivo = "crédito";
						break;
					case 3:
						tipoEjecutivo = "nómina";
						break;
					default:
						tipoEjecutivo = null;
						break;
				}
				
				System.out.print("Ingresa el nombre del ejecutivo: ");
				String nombre = input.nextLine();
				System.out.print("Ingresa el RFC del ejecutivo: ");
				String rfc = input.nextLine();
				System.out.print("Ingresa la dirección del ejecutivo: ");
				String dir = input.nextLine();
				System.out.print("Ingresa el teléfono del ejecutivo: ");
				String tel = input.nextLine();
				System.out.print("Ingresa el sueldo mensual del ejecutivo: ");
				double sueldo = Double.parseDouble(input.nextLine());
				String pass, passRepeat;
				
				do{
					//Se genera un bucle para que la contraseña ingresada sea correcta
					System.out.print("Ingresa una contraseña: ");
					pass = input.nextLine();
					System.out.print("Repite la contraseña: ");
					passRepeat = input.nextLine();
					
					if(!pass.equals(passRepeat)) { System.out.println("Las contraseñas no coinciden, vuelve a escribirlas"); }
					
				}while(!pass.equals(passRepeat));
				
				//Se crea un nuevo ejecutivo
				control.nuevoEjecutivo(tipoEjecutivo, nombre, rfc, dir, tel, sueldo, pass);
				break;
			default:
				System.out.println("Opcion incorrecta");
				break;
		}
		
		input.close();
		return false;
		
	}
	
	public static void main (String [] args) {
		//Banco
		
		//inicio de sesion
		boolean inicio = init();
		
		if(inicio) {
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
		}else {
			System.out.println("Gracias por usar FernandSoft! \nFin del programa");
		}
	}
}
