package test.step1;

import java.util.ArrayList;

import model.ItemDAO;
import model.ItemVO;

public class TestFineItemByMinPrice {
	public static void main(String[] args) {
		ItemDAO dao = new ItemDAO();
		
		ArrayList<ItemVO> itemList = dao.findItemByMinPrice();
		for(ItemVO vo : itemList) {
			System.out.println("최저가 상품 정보 " + vo);
		}
	}
}
