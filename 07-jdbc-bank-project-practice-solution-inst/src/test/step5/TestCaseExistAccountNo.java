package test.step5;

import java.sql.SQLException;

import model.AccountDAO;

public class TestCaseExistAccountNo {
	public static void main(String[] args) {
		try {
			AccountDAO dao = new AccountDAO();
			String accountNo = "1";
			 accountNo="11";
			boolean result = dao.existsAccountNo(accountNo);
			if (result)
				System.out.println(accountNo + " 계좌가 존재합니다");
			else
				System.out.println(accountNo + " 계좌가 존재하지 않습니다");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
