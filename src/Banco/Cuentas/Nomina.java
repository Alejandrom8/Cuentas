package Banco.Cuentas;

public class Nomina extends Cuenta{
	
	public String nocta, RFCE, nombre_empresa, fecha_deposito;
	public float saldo;
	
	public Nomina(
			String nc,//name of the client
            String no_c,//number of client
            String fa,//append date of the count
            int no_s,//number of branch office
            String e,//state
            String c,//email
            String t, //phone
            String nocta,
            String rfce,
            String nombre,
            String fd,
            float saldo
    ){
		super(nc, no_c, fa, no_s, e, c, t);
		this.nocta = nocta;
		this.RFCE = rfce;
		this.nombre_empresa = nombre;
		this.fecha_deposito = fd;
		this.saldo = saldo;
	}
}