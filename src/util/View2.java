package util;

public interface View2 {
	
	// 메인메뉴 
	int HOME = 1;
	
	// 비회원   
	int NONEUSER = 3;
	
	// 회원가입, 로그인   -- 김슬기  
	int USER = 2;
	int USER_SIGNUP = 21;
	int USER_LOGIN = 22;
	
	
	// 로그인한 회원정보 보기 --박민지 
	int USER_STATUS = 23;
	int USER_MODIFY = 24;
	int USER_REVIEW = 25;
	
	
	//게시판  -- 전재한 
	int BOARD = 4;
	// TODO: 게시판을 선택하는 인터페이스를 추가함, 순서변경 필요하다면 수정 필요
	int BOARD_SELECT = 44;
	/**/
	int BOARD_WRITE = 41;
	int BOARD_MODIFY = 42;
	int BOARD_DELETE = 43;
	
	
	// 연극보여주기 --박민지
	int DRAMA = 5;
	int DRAMAINFO = 51;
	int DRAMAREVIEW = 52;
	
	
	//예매 , 환불  --전재한 
	int TICKETTING = 61;
	int REFUND = 62;
	
	// 리뷰  -- 김슬기
	
	int REVIEW = 7;
	int REVIEW_WRITE = 71;
	int REVIEW_MODIFY = 72;
	int REVIEW_DELETE = 73;

	
	
	
	

}
