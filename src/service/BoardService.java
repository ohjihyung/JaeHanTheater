package service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import dao.BoardDAO;
import util.ScanUtil;
import util.View2;

public class BoardService {

	private static BoardService instance = null;

	private BoardService() {
	}

	public static BoardService getInstance() {
		if (instance == null) {
			instance = new BoardService();
		}
		return instance;
	}

	BoardDAO dao = BoardDAO.getInstance();

	public int showBoard() {

		List<Map<String, Object>> rows = dao.showBoard();

		System.out.println("--- 게시판 리스트 ---");

		if (rows == null || rows.size() == 0) {
		} else {
			for (Map<String, Object> item : rows) {
				System.out.printf("%s %s %s\n", item.get("BOARD_ID"),
						item.get("BOARD_TITLE"), item.get("BOARD_NICK"));
			}
		}

		System.out.println("------");

		while (true) {
			System.out.println("1. 게시판 선택 2. 게시판 작성 0. 게시판 나가기");
			System.out.print("입력 >>> ");

			switch (ScanUtil.nextInt()) {
			case 1:
				return View2.BOARD_SELECT;
			case 2:
				return View2.BOARD_WRITE;
			case 0:
				return View2.HOME;

			default:
				break;
			}
		}

	}

	public int selectBoard() {

		System.out.print("게시판 번호 입력 >>> ");
		int input = ScanUtil.nextInt();

		Map<String, Object> row = dao.selectBoard(input);

		if (row == null || row.size() == 0) {
			System.out.println("글이 존재하지 않습니다");
			return View2.BOARD;
		}

		System.out.println(row);

		while (true) {
			System.out.println("1. 게시판 수정 2. 게시판 삭제 0. 나가기");
			System.out.print("입력 >>> ");
			switch (ScanUtil.nextInt()) {
			case 1:
				return View2.BOARD_MODIFY;
			case 2:
				return View2.BOARD_DELETE;
			case 0:
				return View2.BOARD;

			default:
				break;
			}
		}
	}

	public int writeBoard() {
		System.out.print("게시판 제목 >>> ");
		String title = ScanUtil.nextLine();
		System.out.print("게시판 내용 >>> ");
		String content = ScanUtil.nextLine();
		System.out.print("게시판 닉네임 >>> ");
		String nickName = ScanUtil.nextLine();
		System.out.print("게시판 비밀번호 >>> ");
		String password = ScanUtil.nextLine();
		List<Object> param = new ArrayList<>();
		param.add(title);
		param.add(content);
		param.add(nickName);
		param.add(password);
		int result = dao.writeBoard(param);
		System.out.println(result + "개 수정됨");
		return View2.BOARD;
	}

}
