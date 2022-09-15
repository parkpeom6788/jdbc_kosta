package exception;
/**
 * 패스워드가 일치하지 않을 때 발생하는 Exception 
 * @author KOSTA
 *
 */
public class NotMatchedPasswordException extends Exception{
	private static final long serialVersionUID = 8323820559335018759L;
	public NotMatchedPasswordException(String message) {
		super(message);
	}
}
