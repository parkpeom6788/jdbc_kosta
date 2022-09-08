package test.step3;

import java.util.ArrayList;
import model.EmployeeDAO;
import model.EmployeeVO;
public class TestFindEmployeeListByHighestSalaryAndJob {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		String job = "총무";
		ArrayList<EmployeeVO> list = dao.TestFindEmployeeListByHighestSalaryAndJob(job);
		for(EmployeeVO vo : list) {
			System.out.println(vo);
		}
	}
}
