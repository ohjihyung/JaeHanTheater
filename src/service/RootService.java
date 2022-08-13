package service;

import java.util.List;

import controller.ControllerV2;

import java.util.ArrayList;

import dao.RootDAO;
import util.ScanUtil;
import util.View2;

public class RootService {

	private static RootService instance = null;

	private RootService() {
	}

	public static RootService getInstance() {
		if (instance == null) {
			instance = new RootService();
		}
		return instance;
	}

	RootDAO dao = RootDAO.getInstance();

	public int signUp() {
		System.out.println("--- 회원가입 ---");
		System.out.print("아이디 >>> ");
		String userID = ScanUtil.nextLine();
		System.out.print("비밀번호 >>> ");
		String userPass = ScanUtil.nextLine();
		System.out.print("닉네임 >>> ");
		String userNick = ScanUtil.nextLine();
		System.out.print("이름 >>> ");
		String userName = ScanUtil.nextLine();
		System.out.print("생년월일 >>> ");
		String userBirth = ScanUtil.nextLine();
		System.out.print("휴대폰 번호 >>> ");
		String userPhone = ScanUtil.nextLine();
		System.out.print("이메일 >>> ");
		String userEmail = ScanUtil.nextLine();
		List<Object> param = new ArrayList<>();
		param.add(userID);
		param.add(userPass);
		param.add(userNick);
		param.add(userName);
		param.add(userBirth);
		param.add(userPhone);
		param.add(userEmail);
		int result = dao.signUp(param);
		System.out.println("회원가입 " + result + "개 수행됨");
		return View2.HOME;
	}

	public int login() {
		System.out.println("--- 로그인 ---");
		System.out.print("아이디 >>> ");
		String userID = ScanUtil.nextLine();
		System.out.print("비밀번호 >>> ");
		String userPass = ScanUtil.nextLine();
		ControllerV2.userInfo = dao.login(userID, userPass);
		if (ControllerV2.userInfo == null || ControllerV2.userInfo.size() == 0) {
			
		}
		return 0;
	}

}
