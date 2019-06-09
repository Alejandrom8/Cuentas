package Banco.Cuentas;

//import java.text.ParseException;

public class Cuenta {
    public String nombre_cliente, no_cliente, estado, correo, telefono;
    public String fecha_apertura;//calendar?
    public int no_sucursal;
    
    public Cuenta(
            String nc,//name of the client
            String no_c,//number of client
            String fa,//append date of the count
            int no_s,//number of branch office
            String e,//state
            String c,//email
            String t //phone
    ){
        this.nombre_cliente = nc;
        this.no_cliente = no_c;
        this.fecha_apertura = fa;
        this.no_sucursal = no_s;
        this.estado = e;
        this.correo = c;
        this.telefono = t;
    }
    
}
