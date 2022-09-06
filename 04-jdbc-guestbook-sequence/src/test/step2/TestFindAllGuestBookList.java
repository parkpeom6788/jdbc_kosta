package test.step2;

import java.util.ArrayList;

import model.GuestBookDAO;
import model.GuestBookVO;

public class TestFindAllGuestBookList {
	public static void main(String[] args) {
		GuestBookDAO dao = new GuestBookDAO();
		// 방명록 글번호 ( guestbookNo ) 내림차순으로 정렬되어
		// 방명록 정보가 출력된다 
		ArrayList<GuestBookVO> list = dao.findAllGuestBookListOrderByNoDesc();
		for(GuestBookVO vo : list) 
			System.out.println(vo);
	}
}