package controller;

import service.BoardService;
import util.ScanUtil;
import util.View2;

public class ControllerV2 {

	BoardService boardService = BoardService.getInstance();

	public static void main(String[] args) {
		new ControllerV2().start();
	}

	private void start() {
		
		int view = View2.HOME;

		while (true) {
			switch (view) {
			case View2.HOME:
				view = home();
				break;
			case View2.USER_LOGIN:
				break;
			case View2.BOARD:
				view = boardService.showBoard();
				break;
			case View2.BOARD_SELECT:
				view = boardService.selectBoard();
				break;
			case View2.BOARD_WRITE:
				view = boardService.writeBoard();
				break;
			case View2.BOARD_MODIFY:
				view = boardService.selectBoard();
				break;
			case View2.BOARD_DELETE:
				view = boardService.selectBoard();
				break;
			}

		}

	}

	private int home() {
		System.out.println("=============+++ 재한 극장 +++===================");
		System.out.println(" 1.로그인  2. 회원가입 3. 테스트");
		System.out.println("=================================================");
		System.out.print("입력>>");
		switch (ScanUtil.nextInt()) {
		case 1:
			return View2.USER_LOGIN;
		case 2:
			return View2.USER_SIGNUP;
		case 3:
			return View2.BOARD;
		default:
			return View2.HOME;
		}

	}
}
