package ec.reto.wmpv.ws.service.model;

/**
 * @version 1.0
 * @author william.patino [Date: 19 ago. 2022]
 */
public enum EnumResponse {
	
	OK("OK"),
	
	ERROR("ERROR");
	
	private EnumResponse (String code) {
		this.code=code;
	}
	
	private String code;
	
	public String code() {
		return code;
	}
	
}