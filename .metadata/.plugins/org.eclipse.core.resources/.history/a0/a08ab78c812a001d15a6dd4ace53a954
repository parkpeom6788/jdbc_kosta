package test.step1;

import java.sql.SQLException;
import java.util.Scanner;

import model.MemberDAO;
import model.MemberVO;
/*
 * 아이디로 회원정보를 검색
 */
public class TestFindMemberById {
	public static void main(String[] args) {
		try {
			MemberDAO dao = new MemberDAO();
			System.out.println("**아이디로 회원검색**");
			Scanner scanner = new Scanner(System.in);
			System.out.print("검색할 회원아이디:");
			String id = scanner.nextLine();
			System.out.println(id+"아이디로 회원 검색 시작");
			
			MemberVO vo = dao.findMemberById2(id);
			// 존재하면 객체 반환
			if(vo!=null) {
			// java 아이디 회원명 : 아이유  주소 : 오리  
				System.out.println(id + "아이디 회원명 : " + vo.getName() + " 주소: "+  vo.getAddress());
			} else {
			// 존재하지 않을 때
			System.out.println(id+"아이디에 대한 회원이 존재하지 않습니다.");
			}
			scanner.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
