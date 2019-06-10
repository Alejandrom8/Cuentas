package Banco.Cuentas;

public class Credito extends Cuenta{
	public String no_tarjeta, fecha_pago, fecha_vencimiento;
	public double importe_credito, MCU;
	
	public Credito(
			String ej,
            String nc,//name of the client
            int no_c,//number of client
            String fa,//append date of the count
            int no_s,//number of branch office
            String e,//state
            String c,//email
            String t, //phone
            String no_t,
            String fp,
            String fv,
            double ic,
            double mcu
    ){
		super(ej, nc, no_c, fa, no_s, e, c, t);
		this.no_tarjeta = no_t;
		this.fecha_pago = fp;
		this.fecha_vencimiento = fv;
		this.importe_credito = ic;
		this.MCU = mcu;
	}
	
}
