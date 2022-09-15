package model.exception;
/**
 * 출금, 이체할 때 잔액 부족시 발생하는 Exception
 * @author KOSTA
 *
 */
public class InsufficientBalanceException extends Exception {
	private static final long serialVersionUID = -7126266304655772353L;
	public InsufficientBalanceException(String message) {
		super(message);
	}
}
