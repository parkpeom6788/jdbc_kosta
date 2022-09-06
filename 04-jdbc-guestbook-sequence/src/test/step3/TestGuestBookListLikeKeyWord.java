package test.step3;

import java.util.ArrayList;
import java.util.Scanner;

import model.GuestBookDAO;
import model.GuestBookVO;

public class TestGuestBookListLikeKeyWord {
	public static void main(String[] args) {
		GuestBookDAO dao = new GuestBookDAO();
	try {	
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색어를 입력하세요:");
		String keyword = scanner.nextLine();
		ArrayList<GuestBookVO> list = dao.findGuestBookListLikeKeyword(keyword);
		
		for(GuestBookVO vo : list) 
			System.out.println(vo);
		scanner.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
