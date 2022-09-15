package test.step1;

import java.sql.SQLException;

import model.AccountDAO;
import model.AccountVO;
import model.CreateAccountException;

/**
 * 계좌 생성 단위 테스트
 * @author fight2
 */
public class TestCaseCreateAccount {
	public static void main(String[] args) {
		AccountDAO dao = new AccountDAO();
		AccountVO vo1 = new AccountVO("아이유","1234","여행",1000);
		try {
			dao.createAccount(vo1);
			System.out.println("계좌 개설이 완료되었습니다!");
		} catch (CreateAccountException | SQLException e) {
			e.printStackTrace();
		}
	}
}
