package Banco.Controladores;

import Banco.Cuentas.*;
import Banco.Controladores.Tools;

public class Explorer {
	
	private final Tools tool = new Tools();
	
	public ServiceResult SearchInEjecutivos(Ejecutivo[] arr, String busqueda) {
		
		boolean existe = false;
		int index = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i].RFC.equals(busqueda)) {
				existe = true;
				index = i;
				break;
			}
		}
		
		ServiceResult res = new ServiceResult();
		res.success = existe;
		res.data = Integer.toString(index);
		return res;
	}	
	
	//Metodos de busqueda para las cuentas de DEBITO 
	
	public Debito searchByNumOfClientDebito(Debito[] cuentas, int who) {
		Debito usuario = null;
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getNo_cliente() == who) {
				usuario = cuentas[i];
				break;
			}
		}
		return usuario;
	}
	
	public Debito[] searchByNameDebito(Debito[] cuentas, String nombre) {
		Debito[] personas = new Debito[0];
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getNombre_cliente().equals(nombre)) {
				personas = tool.popDebito(personas, cuentas[i]);
			}
		}
		return personas;
	}
	
	public Debito[] searchBySucursalDebito(Debito[] cuentas, int sucursal) {
		Debito[] personas = new Debito[0];
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getNo_sucursal() == sucursal) {
				personas = tool.popDebito(personas, cuentas[i]);
			}
		}
		return personas;
	}
	
	public Debito[] searchByRFCEjecDebito(Debito[] cuentas, String rfc) {
		Debito[] personas = new Debito[0];
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].ejecutivo.equals(rfc)) {
				personas = tool.popDebito(personas, cuentas[i]);
			}
		}
		return personas;
	}
	
	public Debito[] searchByEstadoDebito(Debito[] cuentas, String estado) {
		Debito[] personas = new Debito[0];
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getEstado().equals(estado)) {
				personas = tool.popDebito(personas, cuentas[i]);
			}
		}
		return personas;
	}
	
	
	//Metodos de busqueda para las cuentas de CREDITO
	
	public Credito searchByNumOfClientCredito(Credito[] cuentas, int who) {
		Credito usuario = null;
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getNo_cliente() == who) {
				usuario = cuentas[i];
				break;
			}
		}
		return usuario;
	}
	
	public Credito searchByNoTarjetaCredito(Credito[] cuentas, String tarjeta) {
		Credito who = null;
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getNo_tarjeta().equals(tarjeta)) {
				who = cuentas[i];
				break;
			}
		}
		return who;
	}
	
	public Credito[] searchByNameCredito(Credito[] cuentas, String nombre) {
		Credito[] personas = new Credito[0];
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getNombre_cliente().equals(nombre)) {
				personas = tool.popCredito(personas, cuentas[i]);
			}
		}
		return personas;
	}
	
	public Credito[] searchBySucursalCredito(Credito[] cuentas, int sucursal) {
		Credito[] personas = new Credito[0];
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getNo_sucursal() == sucursal) {
				personas = tool.popCredito(personas, cuentas[i]);
			}
		}
		return personas;
	}
	
	public Credito[] searchByRFCEjecCredito(Credito[] cuentas, String rfc) {
		Credito[] personas = new Credito[0];
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].ejecutivo.equals(rfc)) {
				personas = tool.popCredito(personas, cuentas[i]);
			}
		}
		return personas;
	}
	
	public Credito[] searchByEstadoCredito(Credito[] cuentas, String estado) {
		Credito[] personas = new Credito[0];
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getEstado().equals(estado)) {
				personas = tool.popCredito(personas, cuentas[i]);
			}
		}
		return personas;
	}
	
	//Metodos de busqueda para las cuentas de NOMINA
	
	public Nomina searchByNumOfClientNomina(Nomina[] cuentas, int who) {
		Nomina usuario = null;
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getNo_cliente() == who) {
				usuario = cuentas[i];
				break;
			}
		}
		return usuario;
	}
	
	public Nomina[] searchByNameNomina(Nomina[] cuentas, String nombre) {
		Nomina[] personas = new Nomina[0];
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getNombre_cliente().equals(nombre)) {
				personas = tool.popNomina(personas, cuentas[i]);
			}
		}
		return personas;
	}
	
	public Nomina[] searchBySucursalNomina(Nomina[] cuentas, int sucursal) {
		Nomina[] personas = new Nomina[0];
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getNo_sucursal() == sucursal) {
				personas = tool.popNomina(personas, cuentas[i]);
			}
		}
		return personas;
	}
	
	public Nomina[] searchByRFCEjecNomina(Nomina[] cuentas, String rfc) {
		Nomina[] personas = new Nomina[0];
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].ejecutivo.equals(rfc)) {
				personas = tool.popNomina(personas, cuentas[i]);
			}
		}
		return personas;
	}
	
	public Nomina[] searchByEstadoNomina(Nomina[] cuentas, String estado) {
		Nomina[] personas = new Nomina[0];
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getEstado().equals(estado)) {
				personas = tool.popNomina(personas, cuentas[i]);
			}
		}
		return personas;
	}
	
	public Nomina searchByRFCENomina(Nomina[] cuentas, String rfce) {
		Nomina usuario = null;
		for(int i = 0; i < cuentas.length; i++) {
			if(cuentas[i].getRFCE().equals(rfce)) {
				usuario = cuentas[i];
				break;
			}
		}
		return usuario;
	}
	
}
