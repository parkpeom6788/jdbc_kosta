package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.security.auth.login.AccountNotFoundException;

import common.DbConfig;
import exception.InsufficientBalanceException;
import exception.NoMoneyException;
import exception.NotMatchedPasswordException;

public class AccountDAO {
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
		if (rs != null)
			rs.close();
		closeAll(pstmt, con);
	}
	/**
	 * 계좌 개설하는 메서드<br>
	 * 예외흐름 : 초기 납입액이 1000원 미만일 경우 Create AccountException을 발생시키고 전파한다 <br>
	 * 
	 * @param accountVO
	 * @param vo1
	 * @throws CreateAccountException
	 * @throws SQLException
	 */
	public void createAccount(AccountVO accountVO) throws CreateAccountException, SQLException {
		if (accountVO.getBalance() < 1000)
			throw new CreateAccountException("계좌 개설시 초기 납입금이 1000원 이상이어야 합니다");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "insert into account(account_no,name,password,account_type,balance) values(account_seq.nextval,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountVO.getName());
			pstmt.setString(2, accountVO.getPassword());
			pstmt.setString(3, accountVO.getAccountType());
			pstmt.setLong(4, accountVO.getBalance());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, con);
		}
	}
	/**
	 * 
	 * 계좌의 잔액을 조회하는 메서드<br>
	 * 계좌번호에 해당하는 계좌가 없으면 AccountNotFoundException 을 발생시키고 전파한다<br>
	 * 계좌번호에 해당하는 계좌가 존재하되 비밀번호가 일치하지 않으면 NotMatchedPasswordException을 발생시키고 전파한다
	 * <br>
	 * 계좌번호에 해당하는 계좌가 존재하고 비밀번호가 일치하면 잔액(balance)을 반환한다<br>
	 * @throws SQLException 
	 * @throws NotMatchedPasswordException 
	 * @throws AccountNotFoundException 
	 */
	public long findBalanceByAccountNo(String accountNo, String password) throws SQLException, NotMatchedPasswordException, AccountNotFoundException {
		long balance = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select password,balance from account where account_no=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(password)) {
					balance = rs.getInt(2);
				} else {
					throw new NotMatchedPasswordException("계좌의 패스워드가 일치하지 않습니다");
				}
			} else {
				throw new AccountNotFoundException(accountNo + "계좌번호에 해당하는 계좌가 존재하지 않습니다");
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return balance;
	}
	/**
	 * 계좌에 입금하는 메서드 <br>
	 * 입금액이 0원 이하이면 NoMoneyException 을 발생시키고 전파<br>
	 * 계좌번호가 존재하지 않으면 AccountNotFoundException을 발생시키고 전파<br>
	 * @throws SQLException 
	 * @throws NoMoneyException 
	 * @throws NotMatchedPasswordException 
	 * @throws AccountNotFoundException 
	 */
	public void deposit(String accountNo, String password, long money) throws SQLException, NoMoneyException, AccountNotFoundException, NotMatchedPasswordException {
		if (money <= 0)
			throw new NoMoneyException("입금액은 0원을 초과해야 합니다");
		long balance = findBalanceByAccountNo(accountNo, password);
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "update account set balance = ? where account_no =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, balance + money);
			pstmt.setString(2, accountNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
	public void withdraw(String accountNo, String password, int money) throws InsufficientBalanceException, NoMoneyException, AccountNotFoundException, SQLException, NotMatchedPasswordException {
		if (money < 0)
			throw new NoMoneyException("출금액은 0원을 초과해야 한다");
		long balance = findBalanceByAccountNo(accountNo, password);

		// 가지고있는 잔액보다 빼려는 돈이 더 크면
		if (balance < money) 
			throw new InsufficientBalanceException("잔액부족: 출금액이 잔액보다 큽니다");
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = getConnection();
				String sql = "update account set balance=? where account_no";
				pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, balance - money);
				pstmt.setString(2, accountNo);
				pstmt.executeUpdate();
			} finally {
				closeAll(pstmt, con);
			}
		}
		/**
		 * 계좌번호에 해당하는 계좌가 존재하는지 유무를 반환하는 메서드 <br>
		 * 매개변수로 전달된 계좌번호에 해당하는 계좌가 존재하면 true 존재하지 않으면 false
		 */
	public boolean existsAccountNo(String accountNo) throws SQLException {
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select count(*) from account where account_no";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return result;
	}

	public void transfer(String senderAccountNo, String password, long money, String receiverAccountNo) throws InsufficientBalanceException, NoMoneyException, AccountNotFoundException, SQLException, NotMatchedPasswordException {
		if (money <= 0)
			throw new NoMoneyException("이체액은 0원을 초과해야 합니다");
		long balance = findBalanceByAccountNo(senderAccountNo, password); // 현재 잔액

		if (balance < money)
			throw new InsufficientBalanceException("잔액 부족으로 이체할 수 없습니다");
	
		if (existsAccountNo(receiverAccountNo) == false)
			throw new AccountNotFoundException("이체받을 계좌가 존재하지 않습니다");
	
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			String withdrawSal = "update account set balance = balance - ? where account_no=?";
			pstmt = con.prepareStatement(withdrawSal);
			pstmt.setLong(1, money);
			pstmt.setString(2, senderAccountNo);
			pstmt.executeUpdate();
			pstmt.close();
			String depositSql = "update account set balance = balance + ? where account_no=?";
			pstmt = con.prepareStatement(depositSql);
			pstmt.setLong(1, money);
			pstmt.setString(2, receiverAccountNo);
			pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		} finally {
			closeAll(pstmt, con);
		}
	}
	public ArrayList<AccountVO> findAccountByHighestBalance() throws SQLException {
		ArrayList<AccountVO> list = new ArrayList<AccountVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select account_no,name,password,account_type,balance from account where balance = (select max(balance) from account)";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AccountVO vo = new AccountVO(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getLong(5));
				list.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	public ArrayList<HashMap<String,String>> findAccountCountAndAvgBalanceListByAccountType() throws SQLException {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		try {
			con = getConnection();
			String sql = "select account_type,count(*),avg(balance) from account group by account_type";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("ACCOUNT_TYPE", rs.getString(1));
				map.put("ACCOUNT_COUNT", rs.getString(2));
				map.put("AVG_BALANCE", rs.getString(3));		
				list.add(map);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
}
