package test.step4;
import java.util.ArrayList;
import java.util.HashMap;
import model.ItemDAO;
public class TestFindMakerAndSumPriceListGroupByMaker {
	public static void main(String[] args) {
		ItemDAO dao = new ItemDAO();
		try {
			int price = 2000;
			ArrayList<HashMap<String,String>> list= dao.findMakerAndSumPriceListGroupByMaker(price);
			for(int i=0; i<list.size(); i++) {
				HashMap<String,String> map = list.get(i);
				System.out.println("제조사 : " + map.get("MAKER") + " 총액 : " + map.get("SUM_PRICE") + " 상품수 : " + map.get("ITEM_COUNT"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
