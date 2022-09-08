package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.DbConfig;

/*
 * DAO : Data Access Object 데이터 베이스 연동 로직 정의
 */
public class ItemDAO {
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PASS);
	}

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		// close는 역순으로 한다
		if (rs != null)
			rs.close();
		closeAll(pstmt, con);
	}

	// 최저가 상품정보
	public ArrayList<ItemVO> findItemByMinPrice() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO vo = null;
		ArrayList<ItemVO> itemlist = new ArrayList<>();
		try {
			con = getConnection();
			// sql 문자열이 길어서 여러 문자열을 합해야 할 경우 가변 속성을 가지고 있는
			// 성능향상을 위해 StringBuilder 를 권장한다 - 서버 자원을 절약하는 것에 권장함
			// 회사에서 문자열을 다룰때 많이 씀
			StringBuilder sql = new StringBuilder("select id,name,maker,price "); // 끝에 한칸 뛰어줘야함->문자열이기때문에
			sql.append("from item ");
			sql.append("where price = (select min(price) from item)");
//			String query = "select id,name,maker,price from item where price = (select min(price) from item)";
			pstmt = con.prepareStatement(sql.toString()); // String 으로 변환후 넣음
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// add시킬때 바로 new 할수있다.
				itemlist.add(new ItemVO(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getLong(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll(rs, pstmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return itemlist;
	}
	// 리스트에서 map을 만듬 
	public ArrayList<HashMap<String, String>> findMakerAndSumPriceListGroupByMaker(int price) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		StringBuilder sb = new StringBuilder();
		try {
			con = getConnection();
			sb.append("select maker , sum(price) as SUM_PRICE  , count(*) as item_count ");
			sb.append(" from item group by maker ");
			sb.append("having sum(price) >= ? ");
			sb.append("order by SUM_PRICE desc");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, price);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String,String> map = new HashMap<>();
				
				// map 에 key값은 컬럼의 대소문자를 구분한다.
				map.put("MAKER", rs.getString(1));
				map.put("SUM_PRICE", rs.getString(2));
				map.put("ITEM_COUNT", rs.getString(3));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll(rs, pstmt, con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
