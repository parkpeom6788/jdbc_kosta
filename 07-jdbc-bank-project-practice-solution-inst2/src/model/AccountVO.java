package model;

public class AccountVO {
	private long accountNo;
	private String name;
	private String password;
	private String accountType;
	private long balance;
	
	
	
	public AccountVO(long accountNo, String name, String password, String accountType, long balance) {
		super();
		this.accountNo = accountNo;
		this.name = name;
		this.password = password;
		this.accountType = accountType;
		this.balance = balance;
	}
	public AccountVO(String name, String password, String accountType, long balance) {
		this.name = name;
		this.password = password;
		this.accountType = accountType;
		this.balance = balance;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "AccountVO [accountNo=" + accountNo + ", name=" + name + ", password=" + password + ", accountType="
				+ accountType + ", balance=" + balance + "]";
	}
}
