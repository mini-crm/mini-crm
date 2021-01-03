package tr.com.minicrm.productgroup.data.couchbase.exception;

public enum ErrorType {
  USER_NOT_FOUND(1, "User not found"),
  USER_ALREADY_EXISTS(2, "User already exists"),
  PRODUCT_NOT_FOUND(3, "Product not found");

  private final int code;
  private final String message;

  private ErrorType(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

}
