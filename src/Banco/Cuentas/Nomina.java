package Banco.Cuentas;

/**
 * Clase que permite modelar una Cuenta de Nómina a partir de una Cuenta
 */

public class Nomina extends Cuenta{
 
 public String nocta, RFCE, nombre_empresa, fecha_deposito;
 public double saldo;
 
  /**
   * Constructor que recibe parámetros
   * @param ej El rfc del ejecutivo
   * @param nc El nombre del cliente 
   * @param t El teléfono del cliente 
   * @param c El correo del cliente 
   * @param e El estado del trámite 
   * @param no_c El número de cliente 
   * @param fa Fecha de apertura 
   * @param no_s El número de sucursal 
   * @param nocta El número de cuenta 
   * @param saldo El saldo de la cuenta
   * @param rfce El RFC de la empresa
   * @param nombre El nombre de la empresa
   * @param fd La fecha de dpósito por parte de la empresa
   */
 
 public Nomina(
	 	String ej,
	 	String nc,
        int no_c,
        String fa,
        int no_s,
        String e,
        String c,
        String t, 
        String nocta,
        String rfce,
        String nombre,
        String fd,
        double saldo
    ){
  super(ej, nc, no_c, fa, no_s, e, c, t);
  this.nocta = nocta;
  this.RFCE = rfce;
  this.nombre_empresa = nombre;
  this.fecha_deposito = fd;
  this.saldo = saldo;
 }
 
  /**
   * Método para establecer el número de cuenta
   * @param nocta El número de cuenta
   */
  public void setNocta(String nocta){ this.nocta = nocta; }
  
  /**
   * Método para obtener el número de cuenta
   * @return String El número de cuenta
   */
  public String getNocta(){ return nocta; }
  
  /**
   * Método para establecer RFC de la empresa
   * @param rfce RFC de la empresa
   */
  public void setRFCE(String rfce){ RFCE = rfce; }
  
  /**
   * Método para obtener RFC de la empresa
   * @return String RFC de la empresa
   */
  public String getRFCE(){ return RFCE; }
  
    /**
   * Método para establecer el nombre de la empresa
   * @param nombre El nombre de la empresa
   */
  public void setNombre_empresa(String nombre){ nombre_empresa = nombre; }
  
  /**
   * Método para obtener el nombre de la empresa
   * @return String El nombre de la empresa
   */
  public String getNombre_empresa(){ return nombre_empresa; }
  
    /**
   * Método para establecer la fecha de depósito
   * @param fd La fecha de depósito
   */
  public void setFecha_deposito(String fd){ fecha_deposito = fd; }
  
  /**
   * Método para obtener la fecha de depósito
   * @return String La fecha de depósito
   */
  public String getFecha_deposito(){ return fecha_deposito; }
  
   /**
   * Método para establecer el saldo de cuenta
   * @param saldo El saldo de cuenta
   */
  public void setSaldo(double saldo){ this.saldo = saldo; }
  
  /**
   * Método para obtener el saldo de cuenta
   * @return double El saldo de cuenta
   */
  public double getSaldo(){ return saldo; }
  
  public String toTableString(boolean cabecera) {
		String table = "";
		
		if(cabecera) {
			table += "\n | #cliente | fecha apertura | fecha deposito | nombre | #cuenta | estado | email | telefono | sucursal | RFC empresa | nombre empresa |  saldo |\n\n";
		}
		
		table += " | " 
				+ super.getNo_cliente() + " | " 
				+ super.getFecha_apertura() +  " | " 
				+ this.getFecha_deposito() + " | "
				+ super.getNombre_cliente() + " | " 
				+ this.getNocta() + " | " 
				+ super.getEstado() + " | " 
				+ super.getCorreo() + " | " 
				+ super.getTelefono() + " | " 
				+ super.getNo_sucursal() + " | "
				+ this.getRFCE() + " | "
				+ this.getNombre_empresa() + " | "
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
						+ "# Deposito: "
						+ this.getFecha_deposito() + "\n"
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
						+ "# RFC de la empresa: "
						+ this.getRFCE() + "\n"
						+ "# Nombre de la empresa: "
						+ this.getNombre_empresa() + "\n"
						+ "# Saldo: "
						+ this.getSaldo() + "\n"
						+ "\n"
		;
		return options;
	}
}
