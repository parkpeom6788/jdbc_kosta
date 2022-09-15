package test.step1;

import java.util.Scanner;

import model.EmployeeDAO;
import model.EmployeeDAO2;
import model.EmployeeVO;
/**
 * 사원번호 empno로 사원 정보를 검색 , 사원정보는 부서정보까지 가지고 있는 정보를 조회 
 * => 사원과 부서 테이블을 Join 해서
 * 조회한다
 * @author fight2
 */
public class TestCaseFindEmployeeByNo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색할 사원번호를 입력하세요");
		int empNo = scanner.nextInt();
		EmployeeDAO2 dao = new EmployeeDAO2();
		try {
			// 사원정보 조회시 JOIN SQL을 이용해 그 사원의 부서정보까지 모두 조회되도록 한다
			EmployeeVO empVO = dao.findEmployeeByNo3(empNo);
			if(empVO == null)	 {
				System.out.println(empNo + " 사원번호에 해당하는 사원이 존재하지 않습니다.");
			} else {
				System.out.println(empNo+" 사원번호 사원에 대한 검색결과");
				System.out.println(empVO.getEmpNo());
				System.out.println(empVO.getEname());
				System.out.println(empVO.getJob());
				System.out.println(empVO.getSal());
				System.out.println(empVO.getDepartmentVO().getDeptNo());
				System.out.println(empVO.getDepartmentVO().getDname());
				System.out.println(empVO.getDepartmentVO().getTel());
				System.out.println(empVO.getDepartmentVO().getLoc());
				}
		} catch (Exception e) {
			e.printStackTrace();  // 나중에 비우면 디버깅 할때 어렵다 -> 이상현상 발생 할대 잡을수있으므로
		}
		scanner.close();
	}
}
