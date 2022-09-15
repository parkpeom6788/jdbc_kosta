package exception;
/**
 *  거래 금액이 0원을 초과하지 않을 때 발생하는 Exception 
 * @author KOSTA
 *
 */
public class NoMoneyException extends Exception{
	private static final long serialVersionUID = -1947940677166752660L;
	public NoMoneyException(String message) {
		super(message);
	}
}
