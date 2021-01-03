package tr.com.minicrm.productgroup.data.couchbase.exception;

public abstract class BaseRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BaseRuntimeException(String message) {
    super(message);
  }

  public abstract Integer getCode();

}
