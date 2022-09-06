package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.PetDAO;
import model.PetVO;

public class TestFindPetByName {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			PetDAO dao = new PetDAO();
			System.out.println("**이름으로 펫 검색**");
			String name = scanner.nextLine();
			System.out.println(name +" 이름으로 회원 검색 시작 ");
			PetVO vo = dao.findMemberByName(name);
			if(vo == null) {
				System.out.println("회원이 없습니다.");
			} else {
				System.out.printf("%s 의 주인은 %s  성별은 %s", name,vo.getOwner(),vo.getSex());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
