package Banco.Controladores;

import Banco.Cuentas.*;

public class Explorer {
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
}
