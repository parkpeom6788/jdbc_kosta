package model;

public class EmployeeVO {
	private int empNo;
	private String ename;
	private long sal;
	private String job;
	private DepartmentVO departmentVO; // has a relationship
	public EmployeeVO() {
		super();
	}
	// 사원정보가 필요할때 
	public EmployeeVO(int empNo, String ename, long sal, String job) {
		super();
		this.empNo = empNo;
		this.ename = ename;
		this.sal = sal;
		this.job = job;
	}
	// 부서정보가 필요할때 
	public EmployeeVO(int empNo, String ename, long sal, String job, DepartmentVO departmentVO) {
		super();
		this.empNo = empNo;
		this.ename = ename;
		this.sal = sal;
		this.job = job;
		this.departmentVO= departmentVO;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public long getSal() {
		return sal;
	}
	public void setSal(long sal) {
		this.sal = sal;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public DepartmentVO getDepartmentVO() {
		return departmentVO;
	}
	public void setDepartmentVO(DepartmentVO departmentVO) {
		this.departmentVO = departmentVO;
	}
	@Override
	public String toString() {
		return "EmployeeVO [empNo=" + empNo + ", ename=" + ename + ", sal=" + sal + ", job=" + job + ", departmentVO="
				+ departmentVO + "]";
	}
}
