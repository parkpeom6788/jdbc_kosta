package model;

public class GuestBookVO {
	private int guestBookNo;
	private String title;
	private String content;
	
	public GuestBookVO() {
		super();
	}
	// 시퀀스로 쓰일 BookVO 제외
	public GuestBookVO(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	public GuestBookVO(int guestBookNo, String title, String content) {
		super();
		this.guestBookNo = guestBookNo;
		this.title = title;
		this.content = content;
	}
	public int getGuestBookNo() {
		return guestBookNo;
	}
	public void setGuestBookNo(int guestBookNo) {
		this.guestBookNo = guestBookNo;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "GuestBookVO [guestBookNo=" + guestBookNo + ", title=" + title + ", content=" + content + "]";
	}
	
}
