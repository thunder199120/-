package cn.tedu.store.controller.ex;
/**
 * 上传的文件大小超出了限制
 * @author JAVA
 *
 */
public class FileSizeException extends  FileUploadException{

	private static final long serialVersionUID = 1L;

	public FileSizeException() {
		super();
		
	}

	public FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileSizeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileSizeException(String message) {
		super(message);
	}

	public FileSizeException(Throwable cause) {
		super(cause);
	}
	

}
