package Banco.Cuentas;

//import java.text.ParseException;

public class Cuenta {
    public String ejecutivo, nombre_cliente, estado, correo, telefono;
    public String fecha_apertura;//calendar?
    public int no_sucursal,no_cliente;
    
    public Cuenta(
    		String ejecutivo,
            String nc,//name of the client
            int no_c,//number of client
            String fa,//append date of the count
            int no_s,//number of branch office
            String e,//state
            String c,//email
            String t //phone
    ){
    	this.ejecutivo = ejecutivo;
        this.nombre_cliente = nc;
        this.no_cliente = no_c;
        this.fecha_apertura = fa;
        this.no_sucursal = no_s;
        this.estado = e;
        this.correo = c;
        this.telefono = t;
    }
    
}
