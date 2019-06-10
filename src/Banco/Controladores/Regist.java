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
	
	private Debito[] cuentasDebito;
	private Credito[] cuentasCredito;
	private Nomina[] cuentasNomina;
	private Ejecutivo[] ejecutivos;
	private MDD con;
	private Tools tool;
	private Explorer exp;
	public Ejecutivo ejecutivoActual;
	
	public Regist() {
		this.con = new MDD();//implementa los metodos para insertar, leer, borrar y actualizar datos de los archivos de texto
		this.tool = new Tools();//implementa herramientas para hacer operaciones mas faciles
		this.exp = new Explorer();//implementa todos los metodos para hace busquedas
		
		//Se carga la informacion de los archivos de texto
		this.cuentasDebito = con.cargarCuentasDebito();
		this.cuentasCredito = con.cargarCuentasCredito();
		this.cuentasNomina = con.cargarCuentasNomina();
		this.ejecutivos = con.CargarEjecutivos();
		
		this.ejecutivoActual = null;//El usuario que este usando el programa
	}
	
	public void setEjecutivoActual(Ejecutivo user) {
		this.ejecutivoActual = user;
	}

	private String getDate() {
		int year, month, day;
		String format;
		Calendar date = Calendar.getInstance();
		year = date.get(Calendar.YEAR);
		month = date.get(Calendar.MONTH) + 1;
		day = date.get(Calendar.DAY_OF_MONTH);
		format = day + "/" + month + "/" + year;
		return format;
	}
	
	public String makeMenu(String[] op, String title) {
		String menuText = "\n	menu ~ " + title + "\n\n";
		for(int i = 1; i <= op.length; i++) {
			menuText += "	" + i + ". " + op[i-1] + "\n";
		}
		menuText += "\n respuesta: ";
		return menuText;
	}
	
	public String generateId(int digits) {
		String id = "";
		for(int i = 0; i < digits; i++) {
			int dig = (int) Math.round(Math.random()*10);
			id += dig;
		}
		return id;
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
				mensaje += "\n Tipo de ejecutivo: " + this.ejecutivoActual.tipo;
				state = true;
			} else {
				mensaje = "Contraseña incorrecta";
			}
		} else {
			mensaje = "Usuario incorrecto";
		}
		System.out.println(mensaje);
		return state;
	}
	
	public void controlCuentas() {
		Scanner input = new Scanner(System.in);
		String nombre, estado, correo, telefono, empresa, rfce;
		float saldo;
		int num = 0;
		
		switch(this.ejecutivoActual.tipo) {
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
				System.out.print("Introduce el RFC de la empresa: ");
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
		String[] SearchOptions = {
				"buscar por numero de cliente",
				"buscar por tipo de cuenta",
				"buscar por nombre del cliente",
				"buscar por numero de sucursal",
				"buscar por RFC del ejecutivo",
				"buscar por estado"
		};
		
		switch(this.ejecutivoActual.tipo) {
			case "debito":
				break;
			case "credito":
				SearchOptions = tool.popString(SearchOptions, "buscar por número de tarjeta");
				break;
			case "nomina":
				SearchOptions = tool.popString(SearchOptions, "buscar por RFC de la empresa");
				break;
		}
		
		SearchOptions = tool.popString(SearchOptions, "regresar");
		
		Scanner input = new Scanner(System.in);
		String SearchText = makeMenu(SearchOptions, "busqueda");
		int res;
		do {
			System.out.println(SearchText);
			res = Integer.parseInt(input.nextLine());
			
			if(res == 1) {
				
			}else if(res == 2) {
				
			}else if(res == 3) {
				
			}else if(res == 4) {
				
			}else if(res == 5) {
				
			}else if(res == 6) {
				
			}else if(res == 7 && this.ejecutivoActual.tipo == "credito") {
				
			}else if(res == 7 && this.ejecutivoActual.tipo == "nomina") {
				
			}else if(res == 8){
				
			}else {
				System.out.println("Opcion Incorrecta");
			}
			
		}while(res != SearchOptions.length);
		
	}
	
	public boolean nuevoEjecutivo(String tipo, String nombre, String rfc, String dir, String tel, double sueldo, String pass) {
		ServiceResult existe = exp.SearchInEjecutivos(this.ejecutivos, rfc);
		if(!existe.success) {
			
			int no_ejecutivo = ejecutivos.length + 1;
			
			Ejecutivo ej = new Ejecutivo(
					no_ejecutivo,
					tipo,
					nombre,
					rfc,
					dir,
					tel,
					sueldo,
					pass
			);
			
			boolean insert = con.insertarEjecutivo(ej);
			if(insert){
				this.ejecutivos = tool.popEjecutivo(ejecutivos,ej);
			}
			return true;
		} else {
			System.out.println("Ya existe un ejecutivo con este RFC ("+ rfc +")");
			return false;
		}
	}
	
	public boolean nuevaCuentaDebito(String nombre, String estado, String correo, String tel, float saldo) {
		if(saldo >= this.saldo_minimo_debito) {
			String nocta = generateId(10);
			String currentDate = this.getDate();
			int sucursal = (int) Math.round(Math.random() * 5 + 1);
			
			Debito deb = new Debito(
				this.ejecutivoActual.RFC,
				nombre,
				cuentasDebito.length,
				currentDate,
				sucursal,
				estado,
				correo,
				tel,
				nocta, 
				saldo
			);
			
			boolean insert = con.insertarCuentaDebito(deb);
			if(insert){
				this.cuentasDebito = tool.popDebito(this.cuentasDebito,deb);
				int index = cuentasDebito.length - 1;
				System.out.println("Se creo la cuenta de débito para el usuario " + cuentasDebito[index].nombre_cliente);
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
		
		String currentDate = this.getDate();
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
		String no_tarjeta = this.generateId(16);
		int no_cuenta =  cuentasCredito.length;
		
		Credito cr = new Credito(
			this.ejecutivoActual.nombre,
			nombre,
			no_cuenta,
			currentDate,
			sucursal,
			estado,
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
			System.out.println("Se creo la cuenta de crédito para el usuario " + cuentasCredito[index].nombre_cliente);
			return true;
		}else {
			System.out.println("No se logro insertar el registro en el archivo cuentas.txt");
			return false;
		}
	}
	
	public boolean nuevaCuentaNomina(String nombre, String estado, String correo, String tel, String rfce, String nombre_empresa, float saldo) throws ParseException {
		if(saldo >= 100) {
			String nocta = generateId(10);
			String currentDate = this.getDate();
			Date currentDateObj = new SimpleDateFormat("dd/MM/yyyy").parse(currentDate);
			int sucursal = (int) Math.round(Math.random() * 5 + 1);
			
			Calendar fecha_deposito = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			fecha_deposito.setTime(currentDateObj);
			fecha_deposito.add(Calendar.DAY_OF_MONTH, 15);
			
			String fd = dateFormat.format(fecha_deposito.getTime());
			
			Nomina nom = new Nomina(
				this.ejecutivoActual.RFC,
				nombre,
				cuentasNomina.length,
				currentDate,
				sucursal,
				estado,
				correo,
				tel,
				nocta,
				rfce,
				nombre_empresa,
				fd,
				saldo
			);
			
			boolean insert = con.insertarCuentaNomina(nom);
			if(insert){
				this.cuentasNomina = tool.popNomina(this.cuentasNomina,nom);
				int index = cuentasNomina.length - 1;
				System.out.println("Se creo la cuenta de nomina para el usuario " + cuentasNomina[index].nombre_cliente);
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
