package test.step2;
import java.util.ArrayList;
import model.EmployeeDAO;

public class TestFindJobKindList {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		try {
			ArrayList<String> list = dao.findJobKindList();
			for (String vo : list) {
				System.out.println(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
