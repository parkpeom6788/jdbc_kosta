package test.step5;

import java.sql.SQLException;

import model.AccountDAO;
import model.exception.AccountNotFoundException;
import model.exception.InsufficientBalanceException;
import model.exception.NoMoneyException;
import model.exception.NotMatchedPasswordException;

public class TestCaseTransfer {
public static void main(String[] args) {		
		try {
			AccountDAO dao=new AccountDAO();
			String senderAccountNo="1";
			String  senderPassword="1234";
			String  receiverPassword="4321";
			long money=300;
			String receiverAccountNo="2";
			System.out.println("이체전 송금자 계좌잔액:"+dao.findBalanceByAccountNo(senderAccountNo, senderPassword));
			System.out.println("이체전 수금자 계좌잔액:"+dao.findBalanceByAccountNo(receiverAccountNo, receiverPassword));
			dao.transfer(senderAccountNo,senderPassword,money,receiverAccountNo);
			System.out.println(money+"원 계좌이체완료");
			System.out.println("이체후 송금자 계좌잔액:"+dao.findBalanceByAccountNo(senderAccountNo, senderPassword));
			System.out.println("이체후 수금자 계좌잔액:"+dao.findBalanceByAccountNo(receiverAccountNo,receiverPassword));
		}catch(NoMoneyException e) {	
			System.out.println(e.getMessage()); // 이체액은 0원을 초과해야 합니다 
		}catch(AccountNotFoundException e) {
			System.out.println(e.getMessage()); //계좌번호에 해당하는 계좌가 존재하지 않습니다 or 이체받을 계좌가 존재하지 않습니다
		}catch(NotMatchedPasswordException e) {
			System.out.println(e.getMessage()); //계좌의 패스워드가 일치하지 않습니다
		}catch(InsufficientBalanceException e) {
			System.out.println(e.getMessage());//잔액 부족으로 이체할 수 없습니다
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
