package tr.com.minicrm.productgroup.data.clickhouse;

import java.sql.SQLIntegrityConstraintViolationException;

class ExceptionUtils {

  private ExceptionUtils() {

  }

  public static boolean isSqlIntegrityConstraintViolationException(Throwable root) {
    do {
      if (isSqlIntegrityException(root)) {
        return true;
      }
      root = root.getCause();
    } while (root != null);
    return false;
  }

  private static boolean isSqlIntegrityException(Throwable throwable) {
    return throwable instanceof SQLIntegrityConstraintViolationException;
  }

}
