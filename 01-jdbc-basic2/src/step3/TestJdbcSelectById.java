package step3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.DbConfig;

public class TestJdbcSelectById {
	public static void main(String[] args) {
		try {
			Class.forName(DbConfig.DRIVER);
			Connection con = DriverManager.getConnection(DbConfig.URL, "scott", "tiger");
			String sql = "select name,address from member2 where id =?";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "spring");

			ResultSet rs = pstmt.executeQuery();
			// 키값 으로 데이터 검색 -> 데이터 하나나옴 
			if(rs.next()) {
				String name = rs.getString("name");
				String address = rs.getString("address");
				System.out.println("name : " + name + " address : " + address);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
