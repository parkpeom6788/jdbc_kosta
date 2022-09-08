package test.step3;

import java.util.ArrayList;

import model.EmployeeDAO;
import model.EmployeeVO;

public class TestFindEmployeeListByHighestSalaryAndJob {
	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		try {
			String job = "총무";
			ArrayList<EmployeeVO> list = dao.TestFindEmployeeListByHighestSalaryAndJob(job); 
			for(EmployeeVO vo : list) {
				System.out.println(vo);
				/*아래와 같은 표현임
				 * for(int i=0; i<list.size(); i++) {
				 * 	System.out.println(list.get(i));
				 * }
				 */
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
