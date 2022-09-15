package test.step6;

import java.sql.SQLException;
import java.util.ArrayList;

import model.AccountDAO;
import model.AccountVO;

public class TestCaseFindAccountByHighestBalance {
	public static void main(String[] args) {
		try {
			AccountDAO dao = new AccountDAO();
			ArrayList<AccountVO> list = dao.findAccountByHighestBalance();
            /*
			for (int i = 0; i < list.size(); i++)
				System.out.println(
						list.get(i).getAccountNo() + " " + list.get(i).getName() + " " + list.get(i).getBalance());
			*/	
			// 위의 for 문을 아래처럼 표현할 수 있다 
			for(AccountVO vo:list) {// list에 있는 요소를 순차적으로 vo 에 할당한다 
				System.out.println("최고잔액계좌정보:"+vo);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
