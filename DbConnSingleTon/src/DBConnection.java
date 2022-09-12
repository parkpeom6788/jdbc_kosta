import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
//DB관련 jar 파일을 build 하여 import 시킨다음
//DB와 연결해야 한다
//connection  관련 객체를 담는 static 변수를 private로 담는다
	private static Connection conn;
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Class.forName("jdbc.oracle.OracleJdbc.jdbc.Driver");
				System.out.println("드라이버 로딩성공!");
				// 다리를 짓고자 하는 목적지
				String url = "jdbc:oracle://localhost:";
				String user ="scott";
				String password ="tiger";
	// 드라이버 매니저의 내장 메서드를 이용해 우리가 사용할 dbms정보를 넘겨주고있다
				conn =DriverManager.getConnection(url,user,password);
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패 : " );
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
