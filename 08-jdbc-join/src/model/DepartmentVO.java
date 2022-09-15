package model;
public class DepartmentVO {
	private int deptNo;
	private String dname;
	private String loc;
	private String tel;
	
	public DepartmentVO() {
		super();
	}
	public DepartmentVO(int deptNo, String dname, String loc, String tel) {
		super();
		this.deptNo = deptNo;
		this.dname = dname;
		this.loc = loc;
		this.tel = tel;
	}
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "DepartmentVO [deptNo=" + deptNo + ", dname=" + dname + ", loc=" + loc + ", tel=" + tel + "]";
	}
}
