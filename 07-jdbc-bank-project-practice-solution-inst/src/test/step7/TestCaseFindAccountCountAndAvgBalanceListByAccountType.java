package test.step7;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.AccountDAO;

public class TestCaseFindAccountCountAndAvgBalanceListByAccountType {
	public static void main(String[] args) {
		AccountDAO dao = new AccountDAO();
		ArrayList<HashMap<String, String>> list;
		try {
			list = dao.findAccountCountAndAvgBalanceListByAccountType();
			System.out.println("*****for Loop*****");
			for(int i=0;i<list.size();i++) {
				HashMap<String,String> map=list.get(i);
				System.out.println("계좌타입:"+map.get("ACCOUNT_TYPE") + " 계좌수:" + map.get("ACCOUNT_COUNT") + " 평균잔액:" + map.get("AVG_BALANCE"));
			}			
			System.out.println("*****Enhanced Loop*****");
			for (HashMap<String, String> map : list)
				System.out.println("계좌타입:"+map.get("ACCOUNT_TYPE") + " 계좌수:" + map.get("ACCOUNT_COUNT") + " 평균잔액:" + map.get("AVG_BALANCE"));
			System.out.println("*****Lambda*****");
			list.forEach(map -> System.out.println("계좌타입:"+map.get("ACCOUNT_TYPE") + " 계좌수:" + map.get("ACCOUNT_COUNT") + " 평균잔액:" + map.get("AVG_BALANCE")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
