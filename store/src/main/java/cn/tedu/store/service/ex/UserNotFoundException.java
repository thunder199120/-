package cn.tedu.store.service.ex;
/**
 * 用户数据不存在或用户数据标记已删除
 * @author JAVA
 *
 */
public class UserNotFoundException extends ServiceException{
	private static final long serialVersionUID = 1L;
	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);

	}


	
}
