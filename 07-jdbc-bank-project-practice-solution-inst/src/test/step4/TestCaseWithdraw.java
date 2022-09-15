package test.step4;

import java.sql.SQLException;

import model.AccountDAO;
import model.exception.AccountNotFoundException;
import model.exception.InsufficientBalanceException;
import model.exception.NoMoneyException;
import model.exception.NotMatchedPasswordException;

public class TestCaseWithdraw {
	public static void main(String[] args) {			
		try {
			AccountDAO dao=new AccountDAO();
			//정상흐름테스트 : 동일 계좌에 출금 전과 후의 잔액 조회로 출금 여부를 확인  	
			//대안흐름테스트 : 1)  존재하는 않는 계좌번호 , 2) 비밀번호 일치여부 , 3) 입금액 적절한지 
			//                   4)  잔액이 충분한지 여부를 테스트 
					
			String accountNo="1";//출금계좌번호
			String password="1234";//계좌패스워드
			int money=100;//출금액
			System.out.println("출금전 계좌잔액:"+dao.findBalanceByAccountNo(accountNo, password));
			dao.withdraw(accountNo,password,money);
			System.out.println("출금후 계좌잔액:"+dao.findBalanceByAccountNo(accountNo, password));
		}catch(NoMoneyException e) {
			System.out.println(e.getMessage());
		}catch(AccountNotFoundException e) { 
			System.out.println(e.getMessage());
		}catch(NotMatchedPasswordException e) {	
			System.out.println(e.getMessage());
		}catch(InsufficientBalanceException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
