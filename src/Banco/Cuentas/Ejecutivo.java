package Banco.Cuentas;

public class Ejecutivo {
	public int no_empleado;
	public String tipo, nombre, RFC, dir, tel, pass;
	public double sueldo;
	public Ejecutivo(
			int no_e,
			String tipo,
			String nombre,
			String rfc,
			String dir,
			String tel,
			double sueldo,
			String pass
	) {
		this.no_empleado = no_e;
		this.tipo = tipo;
		this.nombre = nombre;
		this.RFC = rfc;
		this.dir = dir;
		this.tel = tel;
		this.sueldo = sueldo;
		this.pass = pass;
	}
}
