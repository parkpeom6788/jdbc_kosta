package test.step2;

import java.util.ArrayList;

import model.EmployeeDAO;

// job의 종류를 list로 
public class TestFindJobKindList {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		try {
			ArrayList<String> list = dao.findJobKindList();
			for(String vo : list) {
				System.out.println(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
