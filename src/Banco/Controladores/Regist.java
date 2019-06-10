package Banco.Controladores;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Banco.MDD.*;
import Banco.Cuentas.*;

public class Regist extends Banco.config{
	
	private Debito cuentasDebito[];
	private Credito cuentasCredito[];
	private Nomina cuentasNomina[];
	private Ejecutivo[] ejecutivos;
	private MDD con;
	private int lendeb = 0, lencre = 0, lennom = 0;
	public String ejecutivoActual;
	
	public Regist() {
		this.con = new MDD();
		this.cuentasDebito = new Debito[500];
		this.cuentasCredito = new Credito[500];
		this.cuentasNomina = new Nomina[500];
//		int no_lineas_ejecutivos = con.contarLineas("ejecutivos.txt");
//		this.ejecutivos = new Ejecutivo[no_lineas_ejecutivos];
		this.ejecutivos = con.CargarEjecutivos();
		this.ejecutivoActual = null;
	}
	
	public void setEjecutivoActual(String rfc) {
		this.ejecutivoActual = rfc;
	}
	
	public ServiceResult SearchExecutive(String rfc) {
		boolean existe = false;
		int index = 0;
		
		for(int i = 0; i < ejecutivos.length; i++) {
			if(ejecutivos[i].RFC.equals(rfc)) {
				existe = true;
				index = i;
				break;
			}
		}
		
		ServiceResult res = new ServiceResult();
		res.success = existe;
		res.data[0] = Integer.toString(index);
		return res;
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
	
	public String generateId(int digits) {
		String id = "";
		for(int i = 0; i < digits; i++) {
			int dig = (int) Math.round(Math.random()*10);
			id += dig;
		}
		return id;
	}
	
	public boolean login(String rfc, String pass) {
		ServiceResult comprobacion = this.SearchExecutive(rfc);
		String mensaje = "";
		if(comprobacion.success) {
			int index = Integer.parseInt(comprobacion.data[0]);
			String passCompare = this.ejecutivos[index].pass;
			if(pass.equals(passCompare)) {
				setEjecutivoActual(rfc);
				mensaje = "Bienvenido!";
				return true;
			} else {
				mensaje = "Contraseña incorrecta";
			}
		} else {
			mensaje = "Usuario incorrecto";
		}
		System.out.println(mensaje);
		return false;
	}
	
	public boolean nuevoEjecutivo(String tipo, String nombre, String rfc, String dir, String tel, double sueldo, String pass) {
		ServiceResult existe = this.SearchExecutive(rfc);
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
			
			ejecutivos[ejecutivos.length] = ej;

			return true;
		}else {
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
				nombre,
				Integer.toString(lendeb + 1),
				currentDate,
				sucursal,
				estado,
				correo,
				tel,
				nocta, 
				saldo
			);
			
			if(this.con.insertarCuentaDebito(deb)) {
				this.cuentasDebito[lendeb] = deb;
				System.out.println("Se creo la cuenta de débito para el usuario " + cuentasDebito[lendeb].nombre_cliente);
				lendeb++;
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
		
		String nocta = Integer.toString(this.lencre + 1);
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
		
		Credito cr = new Credito(
			nombre,
			nocta,
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
		
		if(this.con.insertarCuentaCredito(cr)) {
			this.cuentasCredito[lencre] = cr;
			System.out.println("Se creo la cuenta de crédito para el usuario " + cuentasCredito[lencre].nombre_cliente);
			lencre++;
			return true;
		}else {
			System.out.println("No se logro insertar el registro en el archivo cuentas.txt");
			return false;
		}
		
	}
	
	public boolean nuevaCuentaNomina(String nombre, String estado, String correo, String tel, float saldo) {
		if(saldo >= 100) {
			String nocta = generateId(10);
			String currentDate = this.getDate();
			int sucursal = (int) Math.round(Math.random() * 5 + 1);
			
			Debito deb = new Debito(
				nombre,
				Integer.toString(lendeb + 1),
				currentDate,
				sucursal,
				estado,
				correo,
				tel,
				nocta, 
				saldo
			);
			
			if(this.con.insertarCuentaDebito(deb)) {
				this.cuentasDebito[lendeb] = deb;
				System.out.println("Se creo la cuenta de débito para el usuario " + cuentasDebito[lendeb].nombre_cliente);
				lendeb++;
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
