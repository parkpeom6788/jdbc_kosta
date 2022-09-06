package member.view;

import java.util.List;

import member.model.vo.Member;

// 프로그램의 출력 화면을 보여주는 클래스
public class MemberView {

	// 성공 알림용 뷰
	public void displaySuccess(String msg) {

		switch (msg) {
		case "insert":
			System.out.println("회원 가입 성공");
			break;
		case "updatePassword":
			System.out.println("암호 수정 성공");
			break;
		case "updateEmail":
			System.out.println("이메일 수정 성공");
			break;
		case "updatePhone":	
			System.out.println("전화번호 수정 성공");
			break;
		case "updateAddress":
			System.out.println("주소 수정 성공");
			break;
		case "delete":
			System.out.println("회원 탈퇴 성공");
			break;
		default :
			System.out.println("알수없는 성공 알림");
		}
	}
	
	// 실패 알림용 뷰
	public void displayError(String msg) {
		switch (msg) {
		case "insert":
			System.out.println("회원 가입 실패");
			break;
		case "updatePassword":
			System.out.println("암호 수정 실패");
			break;
		case "updateEmail":
			System.out.println("이메일 수정 실패");
			break;
		case "updatePhone":	
			System.out.println("전화번호 수정 실패");
			break;
		case "updateAddress":
			System.out.println("주소 수정 실패");
			break;
		case "delete":
			System.out.println("회원 탈퇴 실패");
			break;
		default :
			System.out.println("알수없는 실패 알림");
		}
	}
	
	// 회원 목록 출력용 뷰
	public void displayList(List<Member> mList) {
		if(mList.isEmpty()) {
			System.out.println("조회 된 회원이 없습니다.");
		} else {
			for(Member m : mList) {
				System.out.println(m);
			}
		}
	}
	// 아이디로 회원 조회
	public void displayMember(Member m) {
		if(m != null) {
			System.out.println(m);
		} else {
			System.out.println("조회된 회원이 없습니다.");
		}
	}
}

