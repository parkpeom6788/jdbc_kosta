package model;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeVO2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색할 사원번호를 입력하세요");
		int empNo = scanner.nextInt();
		EmployeeDAO2 dao = new EmployeeDAO2();
		try {
			EmployeeVO empVO = dao.findEmployeeByNo3(empNo);
			
			if(empVO == null) {
				System.out.println(empNo + " 사원번호에 해당하는 사원이 존재하지 않습니다.");
			} else {
				System.out.println(empNo + " 사원번호 사원에 대한 검색 결과");
				System.out.println(empVO.getEmpNo());
				System.out.println(empVO.getEname());
				System.out.println(empVO.getJob());
				System.out.println(empVO.getSal());
				System.out.println(empVO.getDepartmentVO().getDeptNo());
				System.out.println(empVO.getDepartmentVO().getDname());
				System.out.println(empVO.getDepartmentVO().getTel());
				System.out.println(empVO.getDepartmentVO().getLoc());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
