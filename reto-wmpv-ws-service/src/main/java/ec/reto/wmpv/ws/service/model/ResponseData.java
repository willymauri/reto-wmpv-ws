package ec.reto.wmpv.ws.service.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author william.patino [Date: 06-Apr-2022]
 * @version 1.0
 */
public class ResponseData<T> extends ResponseModel {
	
	/**
	 * Serializado 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private T data;
	
	public ResponseData(String code) {
		super(code);
	}
}
