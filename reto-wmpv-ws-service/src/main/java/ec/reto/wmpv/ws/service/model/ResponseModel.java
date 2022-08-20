package ec.reto.wmpv.ws.service.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author william.patino [Date: 06-Apr-2022]
 * @version 1.0
 */
@Data
public class ResponseModel implements Serializable {
    
	/**
	 * Serializado 
	 */
	private static final long serialVersionUID = 1L;
	
	String code;
    String message;
    
    public ResponseModel(String code) {
    	this.code = code;
    	this.message = "";
    }
}
