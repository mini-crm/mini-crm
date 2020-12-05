package tr.com.minicrm.productgroup.data.jooq;

import java.sql.SQLIntegrityConstraintViolationException;

class ExceptionUtils {
	private ExceptionUtils() {

	}

	public static boolean isSQLIntegrityConstraintViolationException(Throwable root) {
		do {
			if (isSQLIntegrityException(root)) {
				return true;
			}
			root = root.getCause();
		} while (root != null);
		return false;
	}

	private static boolean isSQLIntegrityException(Throwable throwable) {
		return throwable instanceof SQLIntegrityConstraintViolationException;
	}

}
