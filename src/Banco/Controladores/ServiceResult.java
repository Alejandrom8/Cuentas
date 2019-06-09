package Banco.Controladores;

public class ServiceResult {
	public boolean success;
	public String errors, messages, onSuccessEvent, onErrorEvent;
	public String[][]  data;
	
	public ServiceResult() {
		this.success = false;
	}
}
