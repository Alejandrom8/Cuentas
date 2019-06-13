package Banco.Controladores;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import Banco.MDD.*;
import Banco.Cuentas.*;

public class Regist extends Banco.config{
	
	//Datos de todo el programa
	public Debito[] cuentasDebito;
	public Credito[] cuentasCredito;
	public Nomina[] cuentasNomina;
	public Ejecutivo[] ejecutivos;
	//extensiones para el procesamiento de informacion
	public MDD con;
	public Tools tool;
	public Explorer exp;
	public Activity activity;
	//usuario
	public Ejecutivo ejecutivoActual;
	
	public Regist() {
		
		this.con = new MDD();//implementa los metodos para insertar, leer, borrar y actualizar datos de los archivos de texto
		this.tool = new Tools();//implementa herramientas para hacer operaciones mas faciles
		this.exp = new Explorer();//implementa todos los metodos para hacer busquedas
		
		//Se carga la informacion de los archivos de texto
		this.cuentasDebito = con.cargarCuentasDebito();
		this.cuentasCredito = con.cargarCuentasCredito();
		this.cuentasNomina = con.cargarCuentasNomina();
		
		this.activity = new Activity();
		
		this.ejecutivos = con.CargarEjecutivos();

		this.ejecutivoActual = null;//El usuario que este usando el programa
	}
	
	public void setEjecutivoActual(Ejecutivo user) {
		this.ejecutivoActual = user;
	}
	
	public boolean login(String rfc, String pass) {
		ServiceResult comprobacion = exp.SearchInEjecutivos(this.ejecutivos, rfc);
		String mensaje = "";
		boolean state = false;
		if(comprobacion.success) {
			int index = Integer.parseInt(comprobacion.data);
			String passCompare = this.ejecutivos[index].pass;
			if(pass.equals(passCompare)) {
				setEjecutivoActual(this.ejecutivos[index]);
				mensaje = "\n Bienvenid@ " + this.ejecutivoActual.nombre + " al banco Fernandino!";
				mensaje += "\n Tipo de ejecutivo: " + this.ejecutivoActual.tipo.toUpperCase();
				state = true;
			} else {
				mensaje = "\nContraseña incorrecta";
			}
		} else {
			mensaje = "\nUsuario incorrecto";
		}
		System.out.println(mensaje);
		return state;
	}
	
	public void controlCuentas() {
		Scanner input = new Scanner(System.in);
		String nombre, estado, correo, telefono, empresa, rfce;
		float saldo;
		int num = 0;
		
		String tipo = this.ejecutivoActual.getTipo();
		
		switch(tipo) {
			case "debito":
				num = 1;
				break;
			case "credito":
				num = 2;
				break;
			case "nomina":
				num = 3;
				break;
		}
		
		System.out.println("\n\n####################################\n"
				+	   "#				   #\n" 
				+  	   "#	NUEVA CUENTA DE " + tipo + "	   #\n"
				+	   "#				   #\n"
				+	   "####################################\n\n"
		);
		
		switch(num) {
			case 1:
				//crear cuenta de debito
				System.out.print("\nIntroduce el nombre del usuario: ");
				nombre = input.nextLine();
				System.out.print("Introduce el estado: ");
				estado = input.nextLine();
				System.out.print("Introduce el correo: ");
				correo = input.nextLine();
				System.out.print("Introduce el telefono: ");
				telefono = input.nextLine();
				System.out.print("Introduce el saldo inicial de la cuenta: ");
				saldo = Float.parseFloat(input.nextLine());
				
				nuevaCuentaDebito(nombre, estado, correo, telefono, saldo);
				break;
			case 2:
				//crear cuenta de credito
				System.out.print("\nIntroduce el nombre del usuario: ");
				nombre = input.nextLine();
				System.out.print("Introduce el estado: ");
				estado = input.nextLine();
				System.out.print("Introduce el correo: ");
				correo = input.nextLine();
				System.out.print("Introduce el telefono: ");
				telefono = input.nextLine();
				
				try {
					nuevaCuentaCredito(nombre, estado, correo, telefono);
				}catch(ParseException e) {
					System.out.println("Hubo un error al crear la cuenta: " + e);
				}
				break;
			case 3:
				//crear cuenta de nomina
				System.out.print("\nIntroduce el nombre del usuario: ");
				nombre = input.nextLine();
				System.out.print("Introduce el estado: ");
				estado = input.nextLine();
				System.out.print("Introduce el correo: ");
				correo = input.nextLine();
				System.out.print("Introduce el telefono: ");
				telefono = input.nextLine();
				System.out.print("Introduce el nombre de la empresa: ");
				empresa = input.nextLine();
				System.out.print("Introduce el RFC de la empresa (en mayusculas): ");
				rfce = input.nextLine();
				System.out.print("Introduce el saldo inicial de la cuenta: ");
				saldo = Float.parseFloat(input.nextLine());
				try {
					nuevaCuentaNomina(nombre, estado, correo, telefono, empresa, rfce, saldo);
				}catch(ParseException e) {
					System.out.println("Hubo un error al crear la cuenta: " + e);
				}
				break;
			default:
				System.out.println("Opcion incorrecta");
				break;
		}
	}
	
	public void Busquedas() {
		
		String tipo = this.ejecutivoActual.getTipo();
		String[] SearchOptions = {
				"buscar por numero de cliente",
				"buscar por tipo de cuenta",
				"buscar por nombre del cliente",
				"buscar por numero de sucursal",
				"buscar por RFC del ejecutivo",
				"buscar por estado"
		};
		
		boolean vacio = true;
		
		switch(tipo) {
			case "debito":
				if(cuentasDebito != null && cuentasDebito.length > 0) {
					vacio = false;
				}
				break;
			case "credito":
				if(cuentasCredito != null && cuentasCredito.length > 0) {
					vacio = false;
				}
				SearchOptions = tool.popString(SearchOptions, "buscar por número de tarjeta");
				break;
			case "nomina":
				if(cuentasNomina != null && cuentasNomina.length > 0) {
					vacio = false;
				}
				SearchOptions = tool.popString(SearchOptions, "buscar por RFC de la empresa");
				break;
		}
		
		
		if(!vacio) { //solo se ejecuta si el array que esta consultando el ejecutivo no esta vacio
			
			SearchOptions = tool.popString(SearchOptions, "regresar");
			
			Scanner input = new Scanner(System.in);
			String SearchText = tool.makeMenu(SearchOptions, "busqueda");
			int res;
			
			do {
				
				System.out.print(SearchText);
				res = Integer.parseInt(input.nextLine());
				
				switch(res) {
					case 1:
						//numero de cliente
				
						System.out.print("\nIngresa el numero del cliente: ");
						int num = Integer.parseInt(input.nextLine());
						
						if(tipo.equals("debito")) {
							//busqueda de debito por numero de cliente
							Debito user = exp.searchByNumOfClientDebito(this.cuentasDebito, num);
							if(user != null) {
								System.out.println(user.toTableString(true));
							}else {
								System.out.println("\nUsuario no encontrado");
							}
						} else if(tipo.equals("credito")) {
							//busqueda de credito por numero de cliente
							Credito user = exp.searchByNumOfClientCredito(this.cuentasCredito, num);
							if(user != null) {
								System.out.println(user.toTableString(true));
							}else {
								System.out.println("\nUsuario no encontrado");
							}
						} else {
							//busqueda de nomina por numero de cliente
							Nomina user = exp.searchByNumOfClientNomina(this.cuentasNomina, num);
							if(user != null) {
								System.out.println(user.toTableString(true));
							}else {
								System.out.println("\nUsuario no encontrado");
							}
						}
						break;
					case 2:
						//tipo de cuenta
						
						System.out.println("\n############### TODAS LAS CUENTAS DE " + tipo.toUpperCase() + " EXISTENTES ###############");
						
						if(tipo.equals("debito")) {
							//busqueda de debito por tipo de cuenta
							String tablaTotal = cuentasDebito[0].toTableString(true);
							for(int index = 1; index < cuentasDebito.length; index++) {
								tablaTotal += cuentasDebito[index].toTableString(false);
							}
							System.out.println(tablaTotal);
						} else if(tipo.equals("credito")) {
							//busqueda de credito por tipo de cuenta
							String tablaTotal = cuentasCredito[0].toTableString(true);
							for(int index = 1; index < cuentasCredito.length; index++) {
								tablaTotal += cuentasCredito[index].toTableString(false);
							}
							System.out.println(tablaTotal);
						} else {
							//busqueda de nomina por tipo de cuenta
							String tablaTotal = cuentasNomina[0].toTableString(true);
							for(int index = 1; index < cuentasNomina.length; index++) {
								tablaTotal += cuentasNomina[index].toTableString(false);
							}
							System.out.println(tablaTotal);
						}
						
						break;
						
					case 3:
						//nombre del cliente
						
						System.out.print("\nIngresa el nombre del cliente: ");
						String nombre = input.nextLine();
						
						if(tipo.equals("debito")) {
							//busqueda de debito por nombre del cliente
							Debito[] personas = exp.searchByNameDebito(this.cuentasDebito, nombre);
							if(personas != null && personas.length > 0) {
								String tablaTotal = personas[0].toTableString(true);
								for(int index = 1; index < personas.length; index++) {
									tablaTotal += personas[index].toTableString(false);
								}
								System.out.println(tablaTotal);
							} else {
								System.out.println("\nNo hay resultados que coincidan con la busqueda");
							}
						} else if(tipo.equals("credito")) {
							//busqueda de credito por nombre del cliente
							Credito[] personas = exp.searchByNameCredito(this.cuentasCredito, nombre);
							if(personas != null && personas.length > 0) {
								String tablaTotal = personas[0].toTableString(true);
								for(int index = 1; index < personas.length; index++) {
									tablaTotal += personas[index].toTableString(false);
								}
								System.out.println(tablaTotal);
							} else {
								System.out.println("\nNo hay resultados que coincidan con la busqueda");
							}
						} else {
							//busqueda de nomina por nombre del cliente
							Nomina[] personas = exp.searchByNameNomina(this.cuentasNomina, nombre);
							if(personas != null && personas.length > 0) {
								String tablaTotal = personas[0].toTableString(true);
								for(int index = 1; index < personas.length; index++) {
									tablaTotal += personas[index].toTableString(false);
								}
								System.out.println(tablaTotal);
							} else {
								System.out.println("\nNo hay resultados que coincidan con la busqueda");
							}
						}
						
						break;
						
					case 4:
						//numero de sucursal
						
						System.out.print("\nIngresa el numero de sucursal: ");
						int sucursal = Integer.parseInt(input.nextLine());
						
						if(tipo.equals("debito")) {
							//busqueda de debito por numero de sucursal
							Debito[] personas = exp.searchBySucursalDebito(this.cuentasDebito, sucursal);
							if(personas != null && personas.length > 0) {
								String tablaTotal = personas[0].toTableString(true);
								for(int index = 1; index < personas.length; index++) {
									tablaTotal += personas[index].toTableString(false);
								}
								System.out.println(tablaTotal);
							} else {
								System.out.println("\nNo hay cuentas de debito registradas en esta sucursal");
							}
						} else if(tipo.equals("credito")) {
							//busqueda de credito por numero de sucursal
							Credito[] personas = exp.searchBySucursalCredito(this.cuentasCredito, sucursal);
							if(personas != null && personas.length > 0) {
								String tablaTotal = personas[0].toTableString(true);
								for(int index = 1; index < personas.length; index++) {
									tablaTotal += personas[index].toTableString(false);
								}
								System.out.println(tablaTotal);
							} else {
								System.out.println("\nNo hay cuentas de credito registradas en esta sucursal");
							}
						} else {
							//busqueda de nomina por numero de sucursal
							Nomina[] personas = exp.searchBySucursalNomina(this.cuentasNomina, sucursal);
							if(personas != null && personas.length > 0) {
								String tablaTotal = personas[0].toTableString(true);
								for(int index = 1; index < personas.length; index++) {
									tablaTotal += personas[index].toTableString(false);
								}
								System.out.println(tablaTotal);
							} else {
								System.out.println("\nNo hay cuentas de Nomina registradas en esta sucursal");
							}
						}
						
						break;
						
					case 5:
						//RFC del ejecutivo
						
						System.out.print("\nIngresa el RFC del ejecutivo: ");
						String rfc = input.nextLine();
						
						if(tipo.equals("debito")) {
							//busqueda de debito por RFC del ejecutivo
							Debito[] personas = exp.searchByRFCEjecDebito(this.cuentasDebito, rfc);
							if(personas != null && personas.length > 0) {
								String tablaTotal = personas[0].toTableString(true);
								for(int index = 1; index < personas.length; index++) {
									tablaTotal += personas[index].toTableString(false);
								}
								System.out.println(tablaTotal);
							} else {
								System.out.println("\nNo hay cuentas registradas bajo este RFC");
							}
						} else if(tipo.equals("credito")) {
							//busqueda de credito por RFC del ejecutivo
							Credito[] personas = exp.searchByRFCEjecCredito(this.cuentasCredito, rfc);
							if(personas != null && personas.length > 0) {
								String tablaTotal = personas[0].toTableString(true);
								for(int index = 1; index < personas.length; index++) {
									tablaTotal += personas[index].toTableString(false);
								}
								System.out.println(tablaTotal);
							} else {
								System.out.println("\nNo hay cuentas registradas bajo este RFC");
							}
						} else {
							//busqueda de nomina por RFC del ejecutivo
							Nomina[] personas = exp.searchByRFCEjecNomina(this.cuentasNomina, rfc);
							if(personas != null && personas.length > 0) {
								String tablaTotal = personas[0].toTableString(true);
								for(int index = 1; index < personas.length; index++) {
									tablaTotal += personas[index].toTableString(false);
								}
								System.out.println(tablaTotal);
							} else {
								System.out.println("\nNo hay cuentas registradas bajo este RFC");
							}
						}
						
						break;
						
					case 6:
						//estado
						
						System.out.print("\nIngresa el estado: ");
						String estado = input.nextLine();
						
						if(tipo.equals("debito")) {
							//busqueda de debito por estado
							Debito[] personas = exp.searchByEstadoDebito(this.cuentasDebito, estado);
							if(personas != null && personas.length > 0) {
								String tablaTotal = personas[0].toTableString(true);
								for(int index = 1; index < personas.length; index++) {
									tablaTotal += personas[index].toTableString(false);
								}
								System.out.println(tablaTotal);
							} else {
								System.out.println("\nNo hay cuentas registradas con este estado");
							}
						} else if(tipo.equals("credito")) {
							//busqueda de credito por estado
							Credito[] personas = exp.searchByEstadoCredito(this.cuentasCredito, estado);
							if(personas != null && personas.length > 0) {
								String tablaTotal = personas[0].toTableString(true);
								for(int index = 1; index < personas.length; index++) {
									tablaTotal += personas[index].toTableString(false);
								}
								System.out.println(tablaTotal);
							} else {
								System.out.println("\nNo hay cuentas registradas con este estado");
							}
						} else {
							//busqueda de nomina por estado
							Nomina[] personas = exp.searchByEstadoNomina(this.cuentasNomina, estado);
							if(personas != null && personas.length > 0) {
								String tablaTotal = personas[0].toTableString(true);
								for(int index = 1; index < personas.length; index++) {
									tablaTotal += personas[index].toTableString(false);
								}
								System.out.println(tablaTotal);
							} else {
								System.out.println("\nNo hay cuentas registradas con este estado");
							}
						}
						
						break;
					case 7:
						//busquedas adicionales
						if(tipo.equals("credito")) {
							//busqueda por numero de la tarjeta
							System.out.print("\nIngresa el numero de la tarjeta: ");
							String tarjeta = input.nextLine();
							
							Credito user = exp.searchByNoTarjetaCredito(this.cuentasCredito, tarjeta);
							if(user != null) {
								System.out.println(user.toTableString(true));
							}else {
								System.out.println("\nCuenta no encontrada");
							}
						}else if( tipo.equals("nomina")) {
							//busqueda por RFC de la empresa
							System.out.print("\nIngresa el RFC de la empresa: ");
							String rfce = input.nextLine();
							Nomina user = exp.searchByRFCENomina(this.cuentasNomina, rfce);
							if(user != null) {
								System.out.println(user.toTableString(true));
							}else {
								System.out.println("\nCuenta no encontrada");
							}
						}
						break;
					case 8:
						//regresar al menu principal
						break;
					default:
						System.out.println("Opcion incorrecta");
						break;
				}
				
			} while (res != SearchOptions.length);
			
		} else {
			System.out.println("\nNo hay clientes actualmente\n");
		}
	}
	
	public void mostrarCuentas() {
		switch(this.ejecutivoActual.getTipo()) {
			case "debito":
				String all = "";
				if(cuentasDebito.length > 0) {	
					 all += cuentasDebito[0].toTableString(true);
					for(int i = 1; i < this.cuentasDebito.length; i++) {
						all += cuentasDebito[i].toTableString(false);
					}
				} else {
					all += "\nNo hay registros\n";
				}
				System.out.println(all);
				break;
			case "credito":
				String all2 = "";
				if(cuentasCredito.length > 0) {	
					 all2 += cuentasCredito[0].toTableString(true);
					for(int i = 1; i < this.cuentasCredito.length; i++) {
						all2 += cuentasCredito[i].toTableString(false);
					}
				} else {
					all2 += "\nNo hay registros\n";
				}
				System.out.println(all2);
				break;
			case "nomina":
				String all3 = "";
				if(cuentasNomina.length > 0) {	
					all3 += cuentasNomina[0].toTableString(true);
					for(int i = 1; i < this.cuentasNomina.length; i++) {
						all3 += cuentasNomina[i].toTableString(false);
					}
				} else {
					all3 += "\nNo hay registros\n";
				}
				System.out.println(all3);
				break;
			default:
				System.out.println("\nNo hay registros para este tipo de cuenta\n");
				break;
		}
	}
	
	public void mostrarEjecutivos() {
		String all = "";
		if(this.ejecutivos.length > 0 ) {
			all += ejecutivos[0].toTableString(true);
			for(int i = 1; i < ejecutivos.length; i++) {
				all += ejecutivos[i].toTableString(false);
			}
		}else {
			all += "\nNo hay ejecutivos registrados\n";
		}
		System.out.println(all);
	}
	
	public boolean nuevoEjecutivo(String tipo, String nombre, String rfc, String dir, String tel, double sueldo, String pass) {
		ServiceResult existe = exp.SearchInEjecutivos(this.ejecutivos, rfc);
		if(!existe.success) {
			
			int no_ejecutivo = ejecutivos.length + 1;
			
			Ejecutivo ej = new Ejecutivo(
					no_ejecutivo,
					tipo,
					nombre.trim(),
					rfc.toUpperCase().trim(),
					dir,
					tel,
					sueldo,
					pass.trim()
			);
			
			boolean insert = con.insertarEjecutivo(ej);
			if(insert){
				this.ejecutivos = tool.popEjecutivo(ejecutivos,ej);
				System.out.println("\nSe creo un nuevo empleado con la siguinte información: \n");
				System.out.println(this.ejecutivos[ejecutivos.length-1].toTableString(true));
			}
			return true;
		} else {
			System.out.println("\nYa existe un ejecutivo con este RFC ("+ rfc +")");
			return false;
		}
	}
	
	public boolean nuevaCuentaDebito(String nombre, String estado, String correo, String tel, float saldo) {
		if(saldo >= this.saldo_minimo_debito) {
			String nocta = tool.generateId(10);
			String currentDate = tool.getDate();
			int sucursal = (int) Math.round(Math.random() * 5 + 1);
			
			Debito deb = new Debito(
				this.ejecutivoActual.RFC,
				nombre.trim(),
				cuentasDebito.length,
				currentDate,
				sucursal,
				estado.toLowerCase().trim(),
				correo,
				tel,
				nocta.trim(), 
				saldo
			);
			
			boolean insert = con.insertarCuentaDebito(deb);
			if(insert){
				this.cuentasDebito = tool.popDebito(this.cuentasDebito,deb);
				int index = cuentasDebito.length - 1;
				System.out.println("Se creo la cuenta de débito para el usuario " + cuentasDebito[index].nombre_cliente + " con los siguientes datos: ");
				System.out.println(cuentasDebito[index].toStringOptions());
				return true;
			}else {
				System.out.println("No se logro insertar el registro en el archivo cuentas.txt");
				return false;
			}
			
		}else {
			System.out.println("El saldo minimo para abrir una cuenta de débito es de $" + this.saldo_minimo_debito);
			return false;
		}
		
	}
	
	public boolean nuevaCuentaCredito(String nombre, String estado, String correo, String tel) throws ParseException {
		
		String currentDate = tool.getDate();
		Date currentDateObj = new SimpleDateFormat("dd/MM/yyyy").parse(currentDate);
		
		Calendar fecha_pago = Calendar.getInstance(), fecha_vencimiento = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		
		fecha_pago.setTime(currentDateObj);
		fecha_pago.add(Calendar.MONTH, 1);
		
		fecha_vencimiento.setTime(currentDateObj);
		fecha_vencimiento.add(Calendar.YEAR, this.agnos_vencimiento);
		
		String fp = dateFormat.format(fecha_pago.getTime());
		String fv = dateFormat.format(fecha_vencimiento.getTime());
		
		int sucursal = (int) Math.round(Math.random() * 5 + 1);
		String no_tarjeta = tool.generateId(16);
		int no_cuenta =  cuentasCredito.length;
		
		Credito cr = new Credito(
			this.ejecutivoActual.RFC,
			nombre.trim(),
			no_cuenta,
			currentDate,
			sucursal,
			estado.toLowerCase().trim(),
			correo,
			tel,
			no_tarjeta,
			fp,
			fv,
			100,
			0
		);
		
		
		boolean insert = con.insertarCuentaCredito(cr);
		if(insert){
			this.cuentasCredito = tool.popCredito(this.cuentasCredito,cr);
			int index = cuentasCredito.length - 1;
			System.out.println("Se creo la cuenta de crédito para el usuario " + cuentasCredito[index].nombre_cliente + " con los siguientes datos: ");
			System.out.println(cuentasCredito[index].toStringOptions());
			return true;
		}else {
			System.out.println("No se logro insertar el registro en el archivo cuentas.txt");
			return false;
		}
	}
	
	public boolean nuevaCuentaNomina(String nombre, String estado, String correo, String tel, String rfce, String nombre_empresa, float saldo) throws ParseException {
		if(saldo >= 100) {
			String nocta = tool.generateId(10);
			String currentDate = tool.getDate();
			Date currentDateObj = new SimpleDateFormat("dd/MM/yyyy").parse(currentDate);
			int sucursal = (int) Math.round(Math.random() * 5 + 1);
			
			Calendar fecha_deposito = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			fecha_deposito.setTime(currentDateObj);
			fecha_deposito.add(Calendar.DAY_OF_MONTH, 15);
			
			String fd = dateFormat.format(fecha_deposito.getTime());
			
			Nomina nom = new Nomina(
				this.ejecutivoActual.RFC,
				nombre.trim(),
				cuentasNomina.length,
				currentDate,
				sucursal,
				estado.toLowerCase().trim(),
				correo,
				tel,
				nocta,
				rfce.toUpperCase().trim(),
				nombre_empresa.trim(),
				fd,
				saldo
			);
			
			boolean insert = con.insertarCuentaNomina(nom);
			if(insert){
				this.cuentasNomina = tool.popNomina(this.cuentasNomina,nom);
				int index = cuentasNomina.length - 1;
				System.out.println("Se creo la cuenta de nomina para el usuario " + cuentasNomina[index].nombre_cliente + " con los siguientes datos: ");
				System.out.println(cuentasNomina[index].toStringOptions());
				return true;
			}else {
				System.out.println("No se logro insertar el registro en el archivo cuentas.txt");
				return false;
			}

		}else {
			System.out.println("El saldo minimo para abrir una cuenta de débito es de $100");
			return false;
		}
		
	}	
	
}
