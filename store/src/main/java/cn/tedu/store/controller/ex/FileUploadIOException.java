package cn.tedu.store.controller.ex;
/**
 * 上传的文件时读写异常
 * @author JAVA
 *
 */
public class FileUploadIOException extends  FileUploadException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileUploadIOException() {
		super();
	}

	public FileUploadIOException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileUploadIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileUploadIOException(String message) {
		super(message);
	}

	public FileUploadIOException(Throwable cause) {
		super(cause);
	}
	

}
