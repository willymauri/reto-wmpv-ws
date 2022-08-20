package ec.reto.wmpv.ws.noc.service.exception;

public class WsServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WsServiceException() {
        super();
    }

    public WsServiceException(String message) {
        super(message);
    }

    public WsServiceException(Throwable cause) {
        super(cause);
    }

    public WsServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
