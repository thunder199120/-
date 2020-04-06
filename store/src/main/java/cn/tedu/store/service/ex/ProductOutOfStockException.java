package cn.tedu.store.service.ex;

public class ProductOutOfStockException extends ServiceException {

	/**
	 * 商品库存不足异常类
	 */
	private static final long serialVersionUID = 1L;

	public ProductOutOfStockException() {
		super();
	}

	public ProductOutOfStockException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductOutOfStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductOutOfStockException(String message) {
		super(message);
	}

	public ProductOutOfStockException(Throwable cause) {
		super(cause);
	}
	
}
