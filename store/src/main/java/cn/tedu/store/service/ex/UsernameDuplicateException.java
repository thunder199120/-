package cn.tedu.store.service.ex;
/**
 * 用户名冲突异常,例如尝试注册已经被占用的用户名
 * @author JAVA
 *
 */
public class UsernameDuplicateException extends ServiceException{

	private static final long serialVersionUID = 1L;

	public UsernameDuplicateException() {
		super();
		
	}

	public UsernameDuplicateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public UsernameDuplicateException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UsernameDuplicateException(String message) {
		super(message);
		
	}

	public UsernameDuplicateException(Throwable cause) {
		super(cause);
		
	}

}
