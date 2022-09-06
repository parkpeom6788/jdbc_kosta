import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExample {
	Connection con;
	Statement stmt;
	ResultSet rs;
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String id = "scott";
	String pw = "tiger";

	public JdbcExample() { // 객체 생성시 드라이버 로딩
		try {
			// 드라이버 로딩
			Class.forName("oracle.jdbc.OracleDriver"); // oracl driver
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getConnection() { // 연결 전용 메서드
		try {
			con = DriverManager.getConnection(url, pw, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getData() {
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from member2");
			while (rs.next()) {
				System.out.println(rs.getString("user_id"));
				System.out.println(rs.getString("user_nm"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		JdbcExample jdbcExample = new JdbcExample();
		jdbcExample.getConnection();
		jdbcExample.getData();
		jdbcExample.closeConnection();
	}
}
