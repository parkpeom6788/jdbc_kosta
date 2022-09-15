package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.DbConfig;
import model.exception.AccountNotFoundException;
import model.exception.CreateAccountException;
import model.exception.InsufficientBalanceException;
import model.exception.NoMoneyException;
import model.exception.NotMatchedPasswordException;

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
	 * 계좌 개설하는 메서드 <br>
	 * 예외흐름 : 초기 납입액이 1000원 미만일 경우 CreateAccountException을 발생시키고 전파한다 <br>
	 * 
	 * @param accountVO
	 * @throws SQLException
	 * @throws CreateAccountException
	 */
	public void createAccount(AccountVO accountVO) throws CreateAccountException, SQLException {
		if (accountVO.getBalance() < 1000)
			throw new CreateAccountException("계좌 개설시 초기 납입금은 1000원 이상이어야 합니다");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "INSERT INTO account(account_no,name,password,account_type,balance) VALUES(account_seq.nextval,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountVO.getName());
			pstmt.setString(2, accountVO.getPassword());
			pstmt.setString(3, accountVO.getAccountType());
			pstmt.setLong(4, accountVO.getBalance());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
	/**
	 * 계좌의 잔액을 조회하는 메서드 <br>
	 * 계좌번호에 해당하는 계좌가 없으면 AccountNotFoundException을 발생시키고 전파한다 <br>
	 * 계좌번호에 해당하는 계좌가 존재하되 비밀번호가 일치하지 않으면 NotMatchedPasswordException을 발생시키고 전파한다
	 * <br>
	 * 계좌번호에 해당하는 계좌가 존재하고 비밀번호가 일치하면 잔액(balance)를 반환한다 <br>
	 * 
	 * @param accountNo
	 * @param password
	 * @return balance
	 * @throws SQLException
	 * @throws AccountNotFoundException
	 * @throws NotMatchedPasswordException
	 */
	public long findBalanceByAccountNo(String accountNo, String password)
			throws SQLException, AccountNotFoundException, NotMatchedPasswordException {
		long balance = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "SELECT password,balance FROM account WHERE account_no=?";
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
				throw new AccountNotFoundException(accountNo + " 계좌번호에 해당하는 계좌가 존재하지 않습니다");
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return balance;
	}

	/**
	 * 계좌에 입금하는 메서드 <br>
	 * 입금액이 0원 이하이면 NoMoneyException 을 발생시키고 전파 <br>
	 * 계좌번호가 존재하지 않으면 AccountNotFoundException 을 발생시키고 전파 <br>
	 * 패스워드가 일치하지 않으면 NotMatchedPasswordException 을 발생시키고 전파 <br>
	 * 위 검증과정을 다 통과하면 입금처리한다 <br>
	 * 
	 * @param accountNo
	 * @param password
	 * @param money
	 * @throws SQLException
	 * @throws NoMoneyException
	 * @throws AccountNotFoundException
	 * @throws NotMatchedPasswordException
	 */
	public void deposit(String accountNo, String password, long money)
			throws SQLException, NoMoneyException, AccountNotFoundException, NotMatchedPasswordException {
		if (money <= 0)
			throw new NoMoneyException("입금액은 0원을 초과해야 합니다");
		// 계좌번호 확인과 비밀번호 일치여부 확인은 위에서 만든 메서드를 활용한다
		long balance = findBalanceByAccountNo(accountNo, password);

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "UPDATE account SET balance=? WHERE account_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, balance + money);
			pstmt.setString(2, accountNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
	/**
	 * 계좌 출금 메서드 <br>
	 * 출금액이 0원 이하이면 NoMoneyException 발생, 전파 <br>
	 * 계좌번호에 해당하는 계좌가 없으면 AccountNotFoundException 발생, 전파 <br>
	 * 계좌번호에 대한 비밀번호가 다를 경우 NotMatchedPasswordException 발생,전파 <br>
	 * 잔액이 부족할 경우 InsufficientBalanceException 발생, 전파 <br>
	 * 위의 검증 절차를 확인한 후 출금처리
	 * @param accountNo
	 * @param password
	 * @param money
	 * @throws SQLException
	 * @throws NoMoneyException
	 * @throws AccountNotFoundException
	 * @throws NotMatchedPasswordException
	 * @throws InsufficientBalanceException
	 */
	public void withdraw(String accountNo, String password, int money) throws SQLException, NoMoneyException,
			AccountNotFoundException, NotMatchedPasswordException, InsufficientBalanceException {
		if (money <= 0)
			throw new NoMoneyException("출금액은 0원을 초과해야 합니다");
		long balance = findBalanceByAccountNo(accountNo, password);
		// 출금액과 잔액을 비교해서 출금액이 잔액보다 크면 예외를 발생
		if (balance < money)
			throw new InsufficientBalanceException("잔액부족: 출금액이 잔액보다 큽니다");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "UPDATE account SET balance=? WHERE account_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, balance - money);
			pstmt.setString(2, accountNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	/**
	 * 계좌번호에 해당하는 계좌가 존재하는 지 유무를 반환하는 메서드 <br>
	 * 매개변수로 전달된 계좌번호에 해당하는 계좌가 존재하면 true, 존재하지 않으면 false를 반환한다
	 * 
	 * @param accountNo
	 * @return result
	 * @throws SQLException
	 */
	public boolean existsAccountNo(String accountNo) throws SQLException {
		boolean result = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "SELECT COUNT(*) FROM account WHERE account_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountNo);
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getInt(1) == 1) {// count가 1이면 존재한다는 의미
				result = true;
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return result;
	}

	/**
	 * 계좌이체 메서드 <br>
	 * 이체액이 0원 이하인지 확인 <br>
	 * 송금자 계좌번호 유무와 비밀번호 일치 여부를 확인 <br>
	 * 송금자 잔액부족 여부를 확인 <br>
	 * 수금자 계좌번호 유무를 확인 <br>
	 * 
	 * 수동커밋모드 <br>
	 * 출금 <br>
	 * 입금 <br>
	 * commit <br>
	 * 예외발생시 rollback <br>
	 * 
	 * @param senderAccountNo
	 * @param password
	 * @param money
	 * @param receiverAccountNo
	 * @throws NoMoneyException
	 * @throws AccountNotFoundException
	 * @throws NotMatchedPasswordException
	 * @throws InsufficientBalanceException
	 * @throws SQLException
	 */
	public void transfer(String senderAccountNo, String password, long money, String receiverAccountNo)
			throws NoMoneyException, AccountNotFoundException, NotMatchedPasswordException,
			InsufficientBalanceException, SQLException {
		if (money <= 0)
			throw new NoMoneyException("이체액은 0원을 초과해야 합니다");
		long balance = findBalanceByAccountNo(senderAccountNo, password);
		if (balance < money)
			throw new InsufficientBalanceException("잔액 부족으로 이체할 수 없습니다");
		if (existsAccountNo(receiverAccountNo) == false)
			throw new AccountNotFoundException("이체받을 계좌가 존재하지 않습니다");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);// 수동 커밋모드 전환
			String withdrawSql = "UPDATE account SET balance=balance-? WHERE account_no=?";
			pstmt = con.prepareStatement(withdrawSql);
			pstmt.setLong(1, money);
			pstmt.setString(2, senderAccountNo);
			pstmt.executeUpdate();
			pstmt.close();
			String depositSql = "UPDATE account SET balance=balance+? WHERE account_no=?";
			pstmt = con.prepareStatement(depositSql);
			pstmt.setLong(1, money);
			pstmt.setString(2, receiverAccountNo);
			pstmt.executeUpdate();
			con.commit();// 출금과 입금이 정상적으로 수행되었을 때 실제 db에 반영
		} catch (Exception e) {
			con.rollback();// 작업 중 문제가 발생되면 진행되었던 작업을 취소하고 원상태로 되돌린다
			throw e;// Exception을 호출한 곳으로 전파한다
		} finally {
			closeAll(pstmt, con);
		}
	}

	/**
	 * 최고 잔액을 가진 계좌 리스트를 조회하는 메서드
	 * 
	 * @return list
	 * @throws SQLException
	 */
	public ArrayList<AccountVO> findAccountByHighestBalance() throws SQLException {
		ArrayList<AccountVO> list = new ArrayList<AccountVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "SELECT account_no,name,password,account_type,balance FROM account WHERE balance=(SELECT MAX(balance) FROM account)";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AccountVO vo = new AccountVO(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getLong(5));
				list.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	public ArrayList<HashMap<String, String>> findAccountCountAndAvgBalanceListByAccountType() throws SQLException {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=getConnection();
			String sql="SELECT account_type,COUNT(*),AVG(balance) FROM account GROUP BY account_type";		
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				HashMap<String,String> map=new HashMap<String,String>();
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
