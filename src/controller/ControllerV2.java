package controller;

import java.util.Map;

import service.BoardService;
import service.RootService;
import util.ScanUtil;
import util.View2;

public class ControllerV2 {

	public static boolean loggedInUser = false;
	public static Map<String, Object> userInfo = null;

	RootService rootService = RootService.getInstance();
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
			case View2.SIGNUP:
				view = rootService.signUp();
				break;
			case View2.LOGIN:
				view = rootService.login();
				break;
			case View2.LOGOUT:
				view = rootService.logout();
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
		if (!loggedInUser) {
			System.out.println(" 1. 연극 2. 게시판 3. 로그인 4. 회원가입");
		} else {
			System.out.println(" 1. 연극 2. 게시판 3. 마이페이지 4. 로그아웃");
		}
		System.out.println("=================================================");
		System.out.print("입력 >>> ");
		switch (ScanUtil.nextInt()) {
		case 1:
			return View2.DRAMA;
		case 2:
			return View2.BOARD;
		case 3:
			if (!loggedInUser) {
				return View2.LOGIN;
			}
			return View2.USER_STATUS;
		case 4:
			if (!loggedInUser) {
				return View2.SIGNUP;
			}
			return View2.LOGOUT;
		default:
			return View2.HOME;
		}

	}
}
