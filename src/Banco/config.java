package Banco;

public class config {
	public String nombre_banco;
	public int agnos_vencimiento;//a�os en los que vence una tarjeta de cr�dito
	public double saldo_minimo_debito;//saldo minimo que podran ingresar al crear una cuenta
	public config() {
		this.nombre_banco = "Fernandino";
		this.agnos_vencimiento = 5;
		this.saldo_minimo_debito = 100;
	}
}
