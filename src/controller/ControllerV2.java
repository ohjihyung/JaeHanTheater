package controller;

import util.ScanUtil;
import util.View2;

public class ControllerV2 {
	public static void main(String[] args) {
		new ControllerV2().start();
	}

	private void start() {
		int view = View2.HOME;
		
		while(true) {
			
			 switch(view) {
			 	case View2.HOME: view = home(); break; 
			 	case View2.USER_LOGIN: 
			 	case View2.NONEUSER:
				 
			 }
			
			
			
			
			
		}
		
	   
	}

	private int home() {
		System.out.println("=============+++ 재한 극장 +++===================");
		System.out.println(" 1.로그인  2. 회원가입 3. 비회원으로 이용");
		System.out.println("=================================================");
		System.out.print("입력>>");
		switch(ScanUtil.nextInt()) {
		case 1: return View2.USER_LOGIN;
		case 2: return View2.USER_SIGNUP;
		case 3: return View2.NONEUSER;
		default : return View2.HOME;
		}
		
	
	}
}
