package Banco.MDD;

import java.io.*;

import javax.swing.JOptionPane;

import Banco.Controladores.ServiceResult;
import Banco.Cuentas.*;

public class MDD {
	
	private final String dir =  System.getProperty("user.dir"); 
	
	public MDD() {
		this.verificarArchivo("cuentas");
		this.verificarArchivo("ejecutivos");
	}

	public boolean nuevoAlmacenDeDatos(String nombre) {
		
		File f;
		
		ServiceResult res = new ServiceResult();
		
		if(nombre.length() <= 30) {
			try {
				
				f = new File(nombre);
				if(!f.exists()) {
					//si el archivo no existe
					f.createNewFile();
					res.messages = "El archivo se creo exitosamente";
					res.success = true;
				} else {
					res.messages = "El archivo que intenta crear ya exite";
				}
				
				
			}catch(Exception e){
				res.messages = "ha sucedido un error al crear el archivo: " + e;
			}
		}else {
			res.messages = "El nombre del archivo es muy largo";
		}
		
		System.out.println(res.messages);
		return res.success;
	}
	
	public boolean existeArchivo(String ruta) {
		File f = new File(ruta);
		if(f.exists()) {
			return true;
		}
		return false;
	}
	
	public void verificarArchivo(String ruta) {
		if(!this.existeArchivo(ruta + ".txt")) {
			if(this.nuevoAlmacenDeDatos(ruta + ".txt")) {
				System.out.println("El archivo " + ruta + " no existia, asi que se creo");
			}else {
				System.out.println("El archivo " + ruta + " no existia, se intento crear pero hubo un error al crearlo");
			}
		}
	}
	
	public boolean insertarCuentaDebito(Debito cuenta){
		
		if(this.existeArchivo("cuentas.txt")) {
			try {
				FileWriter fstream = new FileWriter("cuentas.txt", true);
	            BufferedWriter out = new BufferedWriter(fstream);
				
				out.write(
						"\n debito,"
						+ cuenta.nombre_cliente + ","
						+ cuenta.no_cliente + ","
						+ cuenta.fecha_apertura + ","
						+ cuenta.no_sucursal + ","
						+ cuenta.estado + ","
						+ cuenta.correo + ","
						+ cuenta.telefono + ","
						+ cuenta.nocta + ","
						+ cuenta.saldo + ","
						+ cuenta.fecha_corte
				);
				out.newLine();
				out.close();
				System.out.println("Cuenta de débito creada");
				return true;
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "ha sucedido un error al crear cuenta de debito: " + e);
			}
		}
		
		return false;
	}
	
	public boolean insertarCuentaCredito(Credito cuenta) {
		if(this.existeArchivo("cuentas.txt")) {
			try {
				FileWriter fstream = new FileWriter("cuentas.txt", true);
	            BufferedWriter out = new BufferedWriter(fstream);
				
				out.write(
						"\n credito,"
						+ cuenta.nombre_cliente + ","
						+ cuenta.no_cliente + ","
						+ cuenta.fecha_apertura + ","
						+ cuenta.no_sucursal + ","
						+ cuenta.estado + ","
						+ cuenta.correo + ","
						+ cuenta.telefono + ","
						+ cuenta.no_tarjeta + ","
						+ cuenta.fecha_pago + ","
						+ cuenta.fecha_vencimiento + ","
						+ cuenta.importe_credito + ","
						+ cuenta.MCU
				);
				out.newLine();
				out.close();
				System.out.println("Cuenta de crédito creada");
				return true;
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "ha sucedido un error al crear la cuenta de crédito: " + e);
			}
		}
		
		return false;
	}
	
	public boolean insertarCuentaNomina(Nomina cuenta) {
		if(this.existeArchivo("cuentas.txt")) {
			try {
				FileWriter fstream = new FileWriter("cuentas.txt", true);
	            BufferedWriter out = new BufferedWriter(fstream);
				
				out.write(
						"\n credito,"
						+ cuenta.nombre_cliente + ","
						+ cuenta.no_cliente + ","
						+ cuenta.fecha_apertura + ","
						+ cuenta.no_sucursal + ","
						+ cuenta.estado + ","
						+ cuenta.correo + ","
						+ cuenta.telefono + ","
						+ cuenta.nocta + ","
						+ cuenta.RFCE + ","
						+ cuenta.nombre_empresa + ","
						+ cuenta.fecha_deposito + ","
						+ cuenta.saldo
				);
				out.newLine();
				out.close();
				System.out.println("Cuenta de nómina creada");
				return true;
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "ha sucedido un error al crear la cuenta de nómina: " + e);
			}
		}
		
		return false;
	}
	
}
