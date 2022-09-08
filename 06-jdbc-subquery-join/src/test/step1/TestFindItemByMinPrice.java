package test.step1;

import java.util.ArrayList;

import model.ItemDAO;
import model.ItemVO;

// 최저가 상품의 아이템을 찾는다
public class TestFindItemByMinPrice {
	public static void main(String[] args) {
		ItemDAO dao = new ItemDAO();
		try {
			// 최저가 상품이 여러개 수 있으므로 리스트로 반환 받는다
			ArrayList<ItemVO> itemList = dao.findItemByMinPrice();
			for(ItemVO itemvo : itemList) {
				System.out.println("최저가 상품 정보" + itemvo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
