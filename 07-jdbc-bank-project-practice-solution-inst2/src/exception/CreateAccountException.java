package exception;
/**
 * 계좌 개설시 초기 납입금이 1000원 미만일 때 발생하는 Exception 
 * @author KOSTA
 *
 */
public class CreateAccountException extends Exception {
	private static final long serialVersionUID = 3062393879675001628L;
	public CreateAccountException(String message) {
		super(message);
	}
}
