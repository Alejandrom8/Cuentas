package Banco.Cuentas;

/**
 * Clase que permite modelar a los empleados
 */

public class Ejecutivo {
 public int no_empleado;
 public String tipo, nombre, RFC, dir, tel, pass;
 public double sueldo;
 
 /**
   * Constructor que recibe parámetros
   * @param nombre El nombre del ejecutivo 
   * @param tipo Tipo de ejecutivo 
   * @param rfc RFC del ejecutivo
   * @param dir La dirección del Ejecutivo
   * @param tel El teléfono del ejecutivo
   * @param no_e Número de ejecutivo
   * @param pass La contraseña del ejecutivo
   * @param sueldo El sueldo del ejecutivo
   */
 
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
 
  /**
   * Método para establecer el tipo de cuenta que maneja el ejecutivo
   * @param tipo El tipo de cuenta que maneja el ejecutivo
   */
  public void setTipo(String tipo){
   this.tipo = tipo;
  }
  
  /**
   * Método para obtener el tipo de cuenta que maneja el ejecutivo
   * @return String El tipo de cuenta que maneja el ejecutivo
   */
  public String getTipo(){
    return tipo;
  }
  
  /**
   * Método para establecer RFC del ejecutivo
   * @param rfce RFC del ejecutivo
   */
  public void setRFCE(String rfce){
    RFC = rfce;
  }
  
  /**
   * Método para obtener RFC del ejecutivo
   * @return String RFC del ejecutivo
   */
  public String getRFC(){
    return RFC;
  }
  
    /**
   * Método para establecer el nombre del ejecutivo
   * @param nombre El nombre del ejecutivo
   */
  public void setNombre(String nombre){
    this.nombre = nombre;
  }
  
  /**
   * Método para obtener el nombre del ejecutivo
   * @return String El nombre del ejecutivo
   */
  public String getNombre(){
    return nombre;
  }
  
    /**
   * Método para establecer la dirección del ejecutivo
   * @param dir La dirección del ejecutivo
   */
  public void setDir(String dir){
    this.dir = dir;
  }
  
  /**
   * Método para obtener la dirección del ejecutivo
   * @return String La dirección del ejecutivo
   */
  public String getDir(){
    return dir;
  }
  
 /**
   * Método para establecer el sueldo del ejecutivo
   * @param sueldo El sueldo del ejecutivo
   */
  public void setSueldo(double sueldo){
    this.sueldo = sueldo;
  }
  
 /**
   * Método para obtener el sueldo del ejecutivo
   * @return double El sueldo del ejecutivo
   */
  public double getSueldo(){
    return sueldo;
  }
  
  /**
   * Método para establecer el teléfono del ejecutivo 
   * @param tel Teléfono del ejecutivo
   */
  public void setTel(String tel){
    this.tel = tel;
  }
  
  /**
   * Método para obtener el teléfono del ejecutivo
   * @return String Teléfono del ejecutivo
   */
  public String getTel(){
    return tel;
  }
  
 /**
   * Método para establecer la contraseña del ejecutivo 
   * @param pass La contraseña del ejecutivo
   */
  public void setPass(String pass){
    this.pass = pass;
  }
  
  /**
   * Método para obtener la contraseña del ejecutivo
   * @return String La contraseña del ejecutivo
   */
  public String getPass(){
    return pass;
  }
  
  /**
   * Método para establecer el número del ejecutivo 
   * @param no_e El número del ejecutivo
   */
  public void setNo_empleado(int no_e){
    no_empleado = no_e;
  }
  
  /**
   * Método para obtener el número del ejecutivo
   * @return int El número del ejecutivo
   */
  public int getNo_empleado(){
    return no_empleado;
  }
  
  public String toTableString(boolean cabecera) {
		String table = "";
		
		if(cabecera) {
			table += "\n | #empleado | nombre | tipo | rfc | direccion de casa | telefono | sueldo | \n\n";
		}
		
		table += " | " 
				+ this.getNo_empleado() + " | " 
				+ this.getNombre() +  " | " 
				+ this.getTipo() + " | " 
				+ this.getRFC() + " | " 
				+ this.getDir() + " | " 
				+ this.getTel() + " | " 
				+ this.getSueldo() + " | " 
				+ "\n"
		;
		
		return table;
  }
 
}