package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.DbConfig;

public class ItemDAO {
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PASS);
	}

	public void closeAll(Connection con, PreparedStatement pstmt) throws SQLException {
		if (con != null)
			con.close();
		if (pstmt != null)
			pstmt.close();
	}

	public void closeAll(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(con, pstmt);
	}

	// 최저가 상품 정보
	public ArrayList<ItemVO> findItemByMinPrice() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemVO> list = new ArrayList<ItemVO>();
		try {
			con = getConnection();
			String query = "select id,name,maker,price from item where price = (select min(price) from item)";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ItemVO vo = new ItemVO(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getLong(4));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<HashMap<String, String>> findMakerAndSumPriceListGroupByMaker(int price) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		try {
			con = getConnection();
			sb.append("select maker, sum(price) SUM_PRICE  , count(*) item_count ");
			sb.append("from item group by maker ");
			sb.append("having sum(price) >= ? order by SUM_PRICE desc ");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, price);
			
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("maker", rs.getString(1));
				map.put("SUM_PRICE", rs.getString(2));
				map.put("item_count", rs.getString(3));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll(con, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
