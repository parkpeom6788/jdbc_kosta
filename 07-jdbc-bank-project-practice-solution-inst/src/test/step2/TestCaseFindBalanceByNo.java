package test.step2;

import java.sql.SQLException;

import model.AccountDAO;
import model.exception.AccountNotFoundException;
import model.exception.NotMatchedPasswordException;

public class TestCaseFindBalanceByNo {
	public static void main(String[] args) {
		try {
			AccountDAO dao = new AccountDAO();
			
			//0.잔액조회 정상흐름 실행 		
			System.out.println("잔액조회:"+ dao.findBalanceByAccountNo("1", "1234"));
			
			//1. AccountNotFoundException test : 존재하지 않는 계좌번호 입력 
			//System.out.println("잔액조회:"+ dao.findBalanceByAccountNo("49", "1234"));
			
			//2.NotMatchedPasswordException test: 잘못된 비번입력  
			//System.out.println("잔액조회:"+ dao.findBalanceByAccountNo("1", "7777"));
			
		
		}catch (AccountNotFoundException e) {
				System.out.println(e.getMessage());	// 11 계좌번호에 해당하는 계좌가 존재하지 않습니다 		
		}catch (NotMatchedPasswordException e) {			
			System.out.println(e.getMessage());	// 계좌의 패스워드가 일치하지 않습니다 		
		}catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
