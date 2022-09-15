package test.step3;

import java.sql.SQLException;

import model.AccountDAO;
import model.exception.AccountNotFoundException;
import model.exception.NoMoneyException;
import model.exception.NotMatchedPasswordException;

public class TestCaseDeposit {
	public static void main(String[] args) {

		try {
			AccountDAO dao=new AccountDAO();
			//정상흐름테스트 : 동일 계좌에 입금 전과 후의 잔액 조회로 입금 여부를 확인  	
			//대안흐름테스트 : 존재하는 않는 계좌번호 , 비밀번호 일치여부 , 입금액 적절한지 여부 
					
			String accountNo="1";//계좌번호 테스트
			String password="1234";//패스워드 테스트
			int money=50;//입금액 테스트 
			System.out.println("입금전 잔액:"+dao.findBalanceByAccountNo(accountNo, password));
			dao.deposit(accountNo,password,money);
			System.out.println("입금완료");
			System.out.println("입금후 잔액:"+dao.findBalanceByAccountNo(accountNo, password));
		}catch(NoMoneyException e) {
			System.out.println(e.getMessage());
		}catch(AccountNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(NotMatchedPasswordException e) {
			System.out.println(e.getMessage());		
		}catch(SQLException e) {
			e.printStackTrace();
		} 		
	}
}
