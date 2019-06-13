package Banco;

import java.util.Scanner;

import Banco.Controladores.*;

public class index {
	
	public final static Regist control = new Regist();
	
	public static boolean login() {
		//introduce al proceso del logeo
		Scanner input = new Scanner(System.in);
		boolean confirm = false;
		int trys = 0, intentosTotales = 2;
		
		do {
			
			System.out.print("\nIngresa tu RFC: ");
			String rfc = input.nextLine().trim();
			System.out.print("Ingresa tu contraseña: ");
			String pass = input.nextLine().trim();
			confirm = control.login(rfc, pass);
			if(!confirm) {
				trys++;
				System.out.println("Quedan " + ((intentosTotales+1) - trys) + " intentos");
			}else {
				return true;
			}
			
		}while(!confirm && trys <= intentosTotales);
		System.out.println("Se acabaron los intentos");
		return false;
	}
	
	private static boolean registrar() {
		//introduce al proceso de registrar un ejecutivo
		Scanner input = new Scanner(System.in);
		String[] menuRegistro = {"Registrar ejecutivo de Débito", "Registrar ejecutivo de Crédito", "Registrar ejecutivo de Nómina"};
		String menuRegistroText = control.tool.makeMenu(menuRegistro, "Registrar");
		System.out.print(menuRegistroText);
		int opcionRegistro = Integer.parseInt(input.nextLine());
		String tipoEjecutivo;
		
		switch(opcionRegistro) {
			case 1:
				tipoEjecutivo = "debito";
				break;
			case 2:
				tipoEjecutivo = "credito";
				break;
			case 3:
				tipoEjecutivo = "nomina";
				break;
			default:
				tipoEjecutivo = null;
				break;
		}
		
		System.out.print("Ingresa el nombre del ejecutivo: ");
		String nombre = input.nextLine();
		System.out.print("Ingresa el RFC del ejecutivo (en mayusculas): ");
		String rfc = input.nextLine();
		System.out.print("Ingresa la dirección del ejecutivo: ");
		String dir = input.nextLine();
		System.out.print("Ingresa el teléfono del ejecutivo: ");
		String tel = input.nextLine();
		System.out.print("Ingresa el sueldo mensual del ejecutivo: ");
		double sueldo = Double.parseDouble(input.nextLine());
		String pass, passRepeat;
		
		do{
			//Se genera un bucle para que la contrase�a ingresada sea correcta
			System.out.print("Ingresa una contraseña: ");
			pass = input.nextLine();
			System.out.print("Repite la contraseña: ");
			passRepeat = input.nextLine();
			
			if(!pass.equals(passRepeat)) { System.out.println("\n Las contraseñas no coinciden, vuelve a escribirlas \n"); }
			
		}while(!pass.equals(passRepeat));
		
		//Se crea un nuevo ejecutivo
		boolean created = control.nuevoEjecutivo(tipoEjecutivo, nombre, rfc, dir, tel, sueldo, pass);
		if(created) {
			System.out.print("\nQuieres iniciar sesion? [s/n] ");
			String respuesta = input.nextLine();
			if(respuesta.toLowerCase().equals("s")) {
				boolean cf = login();
				if(cf) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean init() {
		
		System.out.println("      _________________________\r\n" + 
				"     /////////////|\\\\\\\\\\\\\\\\\\\\\\\\\\\r\n" + 
				"    '.-------------------------.'\r\n" + 
				"     |                         |	Bienvenid@ al banco "+ control.nombre_banco +"\r\n" + 
				"     | [] [] [] [] [] [] [] [] |\r\n" + 
				"     |                         |\r\n" + 
				"   _.-.        _ _2_ _         |\r\n" + 
				"   >   )] [] []||_||||[] [] [] |,'`\\\r\n" + 
				"   `.,'________||___||_________|\\  <\r\n" + 
				"    ||  /  _<> _     _    (_)<>\\ ||\r\n" + 
				"    '' /<>(_),:/     \\:. <>'  <>\\||\r\n" + 
				"    __::::::::/ _ _ _ \\:::::::::::_\r\n" + 
				"   __________           ___________\r\n" + 
				"      ,.::. /           \\  _________\r\n" + 
				"      `''''/             \\ \\:'-'-'-'-\r\n" + 
				"          ||             || \\\\\r\n" + 
				"\r\n" + 
				"------------------------------------------------\r\n");
		
		Scanner input2 = new Scanner(System.in);
		String[] menu = {"iniciar sesion", "registrar ejecutivo"};
		String menuText = control.tool.makeMenu(menu, "inicio");
		
		System.out.print(menuText);
		
		int res = Integer.parseInt(input2.nextLine());
		
		switch(res) {
			case 1:
				//Login
				boolean confirm = login();
				if(confirm) {
					return true;
				}
				break;
			case 2:
				//Registro
				boolean registro = registrar();
				if(registro) {
					return true;
				}
				break;
			default:
				System.out.println("Opcion incorrecta");
				break;
		}
		
		return false;
	}
	
	public static void main (String [] args) {
		//Banco
		config cf = new config();
		Scanner input = new Scanner(System.in);
		//inicio de sesion para acceder al programa
		boolean inicio = init();
		
		if(inicio) {
			
			String[] mainOptions = {"cuentas", "ejecutivos", "cerrar sesion"};
			String[] countsOptions = {"crear cuenta","mostrar cuentas","buscar cuenta","retirar", "depositar","regresar"};
			String[] ejecuOptions = {"crear nuevo ejecutivo", "mostrar ejecutivos", "buscar ejecutivo por RFC", "regresar"};
			
			String menuText = control.tool.makeMenu(mainOptions, "banco");
			String countsText = control.tool.makeMenu(countsOptions, "cuentas");
			String ejecuText = control.tool.makeMenu(ejecuOptions, "ejecutivos");
			
			int res;
			
			do {
				
				System.out.print(menuText);
				res = Integer.parseInt(input.nextLine());
				
				switch(res) {
					case 1:
						//opciones de operaciones con cuentas
						System.out.print(countsText);
						int num = Integer.parseInt(input.nextLine());
						switch(num) {
							case 1:
								//crear cuenta nueva
								control.controlCuentas();
								break;
							case 2:
								//mostrar cuentas
								control.mostrarCuentas();
								break;
							case 3:
								//buscar cuenta
								control.Busquedas();
								break;
							case 4:
								//retirar
								System.out.println("Ingrese el numero del cliente: ");
								int respuesta = Integer.parseInt(input.nextLine());
								System.out.println("Ingrese el monto a retirar: ");
								double monto = Double.parseDouble(input.nextLine());
								switch(control.ejecutivoActual.getTipo()) {
									case "debito":
										control.activity.retiroDebito(control.ejecutivoActual.getTipo(), monto, respuesta, control.cuentasDebito);
										break;
									case "credito":
										control.activity.retiroCredito(control.ejecutivoActual.getTipo(), monto, respuesta, control.cuentasCredito);
										break;
									case "nomina":
										control.activity.retiroNomina(control.ejecutivoActual.getTipo(), monto, respuesta, control.cuentasNomina);
										break;
								}
								break;
							case 5:
								//depositar
								System.out.println("Ingrese el numero del cliente: ");
								int num1 = Integer.parseInt(input.nextLine());
								System.out.println("Ingrese el monto a depositar: ");
								double monto1 = Double.parseDouble(input.nextLine());
								System.out.println("Se deposito "+ monto1 + " a el usuario con numero de cliente: " + num1);
								break;
							case 6:
								//regresar
								break;
							default:
								System.out.println("Opcion incorrecta");
								break;
						}
						break;
					case 2:
						//opciones de operaciones con los ejecutivos
						System.out.print(ejecuText);
						int op = Integer.parseInt(input.nextLine());
						switch(op) {
							case 1:
								//registrar nuevo ejecutivo
								System.out.println("\n\n####################################\n"
										+	   "#				   #\n" 
										+  	   "#	 NUEVO EJECUTIVO 	   #\n"
										+	   "#				   #\n"
										+	   "####################################\n\n"
								);
								registrar();
								break;
							case 2:
								//mostrar cuentas
								control.mostrarEjecutivos();
								break;
							case 3:
								//buscar ejecutivos por RFC
								System.out.print("Introduce el RFC del ejecutivo: ");
								String rfc = input.nextLine();
								ServiceResult response = control.exp.SearchInEjecutivos(control.ejecutivos, rfc);
								if(response.success) {
									System.out.println(control.ejecutivos[Integer.parseInt(response.data)].toTableString(true));
								}else {
									System.out.println("\nNo hay resultados que coincidan con este RFC\n");
								}
								break;
							case 4:
								//regresar
								break;
							default:
								System.out.println("Opcion incorrecta");
								break;
						}
						break;
					case 3:
						//finalizar programa completo
						control.con.vaciarArchivo("cuentas.txt");
						control.con.nuevoAlmacenDeDatos("cuentas.txt");
						
						for(int i = 0; i < control.cuentasDebito.length; i++) {
							control.con.insertarCuentaDebito(control.cuentasDebito[i]);
						}
						
						for(int i = 0; i < control.cuentasCredito.length; i++) {
							control.con.insertarCuentaCredito(control.cuentasCredito[i]);
						}
						
						for(int i = 0; i < control.cuentasNomina.length; i++) {
							control.con.insertarCuentaNomina(control.cuentasNomina[i]);
						}
						
						System.out.print("Fin del programa");
						break;
					default:
						System.out.println("Opcion incorrecta");
						break;
				}
				
			}while(res != 3);
			
		}else {
			System.out.println("Gracias por usar FernandSoft! \nFin del programa");
		}
	}
}
