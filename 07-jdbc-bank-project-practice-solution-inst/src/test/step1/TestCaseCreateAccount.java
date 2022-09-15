package test.step1;

import model.AccountDAO;
import model.AccountVO;
import model.exception.CreateAccountException;

/**
 * 계좌 생성 단위 테스트 
 * @author KOSTA
 *
 */
public class TestCaseCreateAccount {
	public static void main(String[] args) {
		AccountDAO dao=new AccountDAO();
		// 계좌번호는 시퀀스에 의해 자동생성되므로 계좌주명, 비번, 계좌타입, 잔액으로 객체 생성해
		// 전달한다 
		// 1. 정상흐름 
		AccountVO vo1=new AccountVO("아이유","1234","여행",1000);
		//2. 예외흐름 : 초기 납입금을 1000원 미만으로 입력해본다 
		/*
			AccountVO vo2=new AccountVO("아이유","1234","여행",999);		
		*/
		try {
			dao.createAccount(vo1);
			System.out.println("계좌 개설이 완료되었습니다!");
		} catch (CreateAccountException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}








