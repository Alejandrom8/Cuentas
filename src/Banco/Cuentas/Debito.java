package Banco.Cuentas;

/**
 * Clase que permite modelar una Cuenta de Débito a partir de una Cuenta
 */

public class Debito extends Cuenta{
 
 public String nocta; 
 public double saldo;
 public String fecha_corte;
 
 /**
   * Constructor que recibe parámetros
   * @param ej El nombre del ejecutivo 
   * @param nc El nombre del cliente 
   * @param t El teléfono del cliente 
   * @param c El correo del cliente 
   * @param e El estado del trámite 
   * @param no_c El número de cliente 
   * @param fa Fecha de apertura
   * @param no_s El número de sucursal
   * @param nocta El número de cuenta
   * @param saldo El saldo de la cuenta
   */
 
 	public Debito(
		 	String ej,
		 	String nc,
            int no_c,
            String fa,
            int no_s,
            String e,
            String c,
            String t, 
            String nocta,
            double saldo
    ){
	  super(ej, nc, no_c, fa, no_s, e, c, t);
	  this.nocta = nocta;
	  this.saldo = saldo;
	  this.fecha_corte = "none";
 	}
 
  public void setNocta(String nocta){
  /**
   * Método para establecer el número de cuenta
   * @param nocta El número de cuenta
   */
    this.nocta = nocta;
  }
  
  public String getNocta(){
  /**
   * Método para obtener el número de cuenta
   * @return String El número de cuenta
   */
    return nocta;
  }
 
  public void setSaldo(double saldo){
	   /**
	   * Método para establecer el saldo de cuenta
	   * @param saldo El saldo de cuenta
	   */
    this.saldo = saldo;
  }
  
  public double getSaldo(){
	  /**
	   * Método para obtener el saldo de cuenta
	   * @return double El saldo de cuenta
	   */
    return saldo;
  }
  
  public void setFecha_corte(String fc){
	   /**
	   * Método para establecer la fecha de corte
	   * @param fc La fecha de corte
	   */
    fecha_corte = fc;
  }
  
  public String getFecha_corte(){
  /**
   * Método para obtener la fecha de corte
   * @return String La fecha de corte
   */
    return fecha_corte;
  }
  
  public String toTableString(boolean cabecera) {
		String table = "";
		
		if(cabecera) {
			table += "\n | #cliente | fecha apertura | nombre | #cuenta | estado | email | telefono | sucursal | saldo | \n\n";
		}
		
		table += " | " 
				+ super.getNo_cliente() + " | " 
				+ super.getFecha_apertura() +  " | " 
				+ super.getNombre_cliente() + " | " 
				+ this.getNocta() + " | " 
				+ super.getEstado() + " | " 
				+ super.getCorreo() + " | " 
				+ super.getTelefono() + " | " 
				+ super.getNo_sucursal() + " | " 
				+ this.getSaldo() + " | "
				+ "\n"
		;
		
		return table;
  }
  
  public String toStringOptions() {
		String options = "\n" 
						+ "# Numero de cliente: "
						+ this.getNo_cliente() + "\n"
						+ "# Apertura: "
						+ this.getFecha_apertura() + "\n"
						+ "# Nombre: "
						+ super.getNombre_cliente() + "\n"
						+ "# Numero de cuenta: "
						+ this.getNocta() + "\n"
						+ "# Estado: "
						+ super.getEstado() + "\n"
						+ "# Correo: "
						+ super.getCorreo() + "\n"
						+ "# Telefono: "
						+ super.getTelefono() + "\n"
						+ "# Sucursal: "
						+ super.getNo_sucursal() + "\n"
						+ "# Saldo: "
						+ this.getSaldo() + "\n"
						+ "\n"
	;
	return options;
  }

}
