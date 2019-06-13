package Banco.Cuentas;

//import java.text.ParseException;

/**
* Clase para definir una cuenta
* @author María Fernanda Morales Oviedo
* @version Junio 2019
*/

public class Cuenta {
	
	  public String ejecutivo, nombre_cliente, estado, correo, telefono;
	  public String fecha_apertura;
	  public int no_sucursal,no_cliente;
	  
	  /**
	 * Constructor que recibe parámetros
	 * @param ejecutivo Nombre del ejecutivo
	 * @param nc El nombre del cliente
	 * @param t El teléfono del cliente
	 * @param c El correo del cliente
	 * @param e El estado del trámite
	 * @param no_c El número de cliente 
	 * @param fa Fecha de apertura
	 * @param no_s Número de surcursal
	 */
	  
	  public Cuenta(
			  String ejecutivo,
	          String nc,
	          int no_c,
	          String fa,
	          int no_s,
	          String e,
	          String c,
	          String t 
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
	  
	  /**
	 * Método para establecer el nombre del cliente
	 * @param nc El nombre del cliente
	 */
	public void setNombre_cliente(String nc){ nombre_cliente = nc; }
	
	/**
	 * Método para obtener el nombre del cliente
	 * @return String El nombre del cliente
	 */
	public String getNombre_cliente(){ return nombre_cliente; }
	  
	/**
	 * Método para establecer el número de cliente
	 * @param no_c El nombre del cliente
	 */
	public void setNo_cliente(int no_c){ no_cliente = no_c; }
	
	/**
	 * Método para obtener el número del cliente
	 * @return int El nombre del cliente
	 */
	public int getNo_cliente(){ return no_cliente; }
	
	/**
	 * Método para establecer el correo del cliente
	 * @param c El correo del cliente
	 */
	public void setCorreo(String c){ correo = c; }
	
	/**
	 * Método para obtener el correo del cliente
	 * @return String El correo del cliente
	 */
	public String getCorreo(){ return correo; }
	
	/**
	 * Método para establecer el estado del trámite
	 * @param e El estado del cliente
	 */
	public void setEstado(String e){ estado = e; }
	
	/**
	 * Método para obtener el estado del trámite
	 * @return String El estado del cliente
	 */
	public String getEstado(){ return estado; }
	
	/**
	 * Método para establecer la fecha de apertura de la cuenta
	 * @param a Fecha de apertura
	 * @param fa La fecha de apertura de la cuenta
	 */
	public void setFecha_apertura(String fa){ fecha_apertura = fa; }
	
	/**
	 * Método para obtener la fecha de apertura de la cuenta
	 * @return String La fecha de apertura de la cuenta
	 */
	public String getFecha_apertura(){ return fecha_apertura; }
	
	/**
	 * Método para establecer el número de la surusal 
	 * @param no_s Número de la sucursal
	 */
	public void setNo_sucursal(int no_s){ no_sucursal = no_s; }
	
	/**
	 * Método para obtener el número de la surusal
	 * @return int Número de la sucursal
	 */
	public int getNo_sucursal(){ return no_sucursal; }
	
	/**
	 * Método para establecer el teléfono del cliente 
	 * @param t Teléfono del cliente
	 */
	public void setTelefono(String t){ telefono = t; }
	
	/**
	 * Método para obtener el teléfono del cliente
	 * @return int Teléfono del cliente
	 */
	public String getTelefono(){ return telefono; }  
}