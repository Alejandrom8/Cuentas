package Banco.Cuentas;

public class Debito extends Cuenta{
	
	public String nocta; //numero de cuenta
	public double saldo;
	public String fecha_corte;//fecha de corte
	
	public Debito(
			String ej,
			String nc,//name of the client
            int no_c,//number of client
            String fa,//append date of the count
            int no_s,//number of branch office
            String e,//state
            String c,//email
            String t, //phone
            String nocta,
            double saldo
    ){
		super(ej, nc, no_c, fa, no_s, e, c, t);
		this.nocta = nocta;
		this.saldo = saldo;
		this.fecha_corte = "none";
	}
}
