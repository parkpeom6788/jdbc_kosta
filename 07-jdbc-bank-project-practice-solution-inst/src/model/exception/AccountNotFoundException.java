package model.exception;
/**
 * 계좌번호에 따른 계좌가 존재하지 않을 때 발생하는 Exception 
 * @author KOSTA
 *
 */
public class AccountNotFoundException extends Exception {

	/**
	 * 객체 메모리를 외부로 전송할 수 있는 상태로 만드는 것이 직렬화이고 <br>
	 * 직렬화시 어떤 클래스로부터 전송되었는 지를 알리는 역할을 serialVersionUID 가 <br>
	 * 한다. ( 붕어빵틀의 유일한 아이디임 ) , 명시하지 않으면 자동 생성되지만 <br>
	 * 컴파일러별로 uid가 다를 수도 있고 , 특히 클래스의 멤버 변경시 자동 생성되는 아이디는<br>
	 * 변경되므로 직접 생성해서 명시하는 것을 권장한다 
	 */
	private static final long serialVersionUID = -5926283501034963550L;
    public AccountNotFoundException(String message) {
    	super(message);
	}
}






