package Banco.MDD;

import java.io.*;

import javax.swing.JOptionPane;

import Banco.Controladores.ServiceResult;
import Banco.Cuentas.*;

public class MDD {
	
//	private final String dir =  System.getProperty("user.dir"); 
	public Ejecutivo[] ejecutivos;
	
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
				System.out.println("Cuenta de d�bito creada");
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
				System.out.println("Cuenta de cr�dito creada");
				return true;
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "ha sucedido un error al crear la cuenta de cr�dito: " + e);
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
				System.out.println("Cuenta de n�mina creada");
				return true;
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "ha sucedido un error al crear la cuenta de n�mina: " + e);
			}
		}
		
		return false;
	}
	
	public boolean insertarEjecutivo(Ejecutivo registro) {
		
		try {
			
			FileWriter fw = new FileWriter("ejecutivos.txt", true);
			BufferedWriter out = new BufferedWriter(fw);
			
			out.write(
					registro.no_empleado + ","
					+ registro.tipo + ","
					+ registro.nombre + ","
					+ registro.RFC + ","
					+ registro.dir + ","
					+ registro.tel + ","
					+ registro.sueldo + ","
					+ registro.pass 
			);
			
			out.newLine();
			out.close();
			System.out.println("Ejecutivo " + registro.nombre + " registrado exitosamente");
			return true;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ha sucedido un error al registrar al ejecutivo: " + e);
		}
		
		return false;
	}
	
	public int contarLineas (String archivo) {
		
		int no_lineas = 0;
		String linea;
		FileReader fr;
		BufferedReader br;
		
		try {
			
			fr = new FileReader (archivo);
			br = new BufferedReader (fr);
			
			linea = br.readLine();
			
			while(linea != null) {
				no_lineas++;
				linea = br.readLine();
			}
			
			br.close();
			
		}catch(IOException ioex) {
			
			System.out.println("No se pudo leer la informaci�n del archivo " + archivo);
			System.out.println(ioex.getMessage());
			System.exit(1);
			
		}
		
		return no_lineas;
	}
	
	public Ejecutivo[] CargarEjecutivos(){
		
		FileReader fw;
		BufferedReader br;
		String line;
		Ejecutivo[] ejecutivos = new Ejecutivo[contarLineas("ejecutivos.txt")];
		String separador = ",";
		int index = 0;
		
		try {
			fw = new FileReader("ejecutivos.txt");
			br = new BufferedReader(fw);
			line = br.readLine();
			
			while(line != null) {
				String[] data = line.split(separador);
				int no_e = Integer.parseInt(data[0]);
				double sueldo = Double.parseDouble(data[6]);
				Ejecutivo ne = new Ejecutivo(
						no_e,
						data[1],
						data[2],
						data[3],
						data[4],
						data[5],
						sueldo,
						data[7]
				);
				ejecutivos[index] = ne;
				index++;
				line = br.readLine();
			}
			
			br.close();
			return ejecutivos;
		}catch(FileNotFoundException e) {
			System.out.println("El archivo ejecutivos.txt no existe!");
		}catch(IOException e) {
			System.out.println("No se pudo leer la informaci�n del archivo ejecutivos.txt");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		return null;
	}
	
}
