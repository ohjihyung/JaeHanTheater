package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class BoardDAO {

	private static BoardDAO instance = null;

	private BoardDAO() {
	}

	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	String sql = null;

	public List<Map<String, Object>> showBoard() {
		sql = " SELECT * FROM BOARD ";
		List<Map<String, Object>> rows = jdbc.selectList(sql);
		return rows;
	}

	public Map<String, Object> selectBoard(int input) {
		sql = " SELECT * FROM BOARD WHERE BOARD_ID=' " + input + " ' ";
		Map<String, Object> row = jdbc.selectOne(sql);
		return row;
	}

	public int writeBoard(List<Object> param) {
		sql = " INSERT INTO BOARD(                             "
				+ " BOARD_ID,                                  "
				+ " BOARD_DATE,                                "
				+ " BOARD_TITLE,                               "
				+ " BOARD_WRITE,                               "
				+ " BOARD_NICK,                                "
				+ " BOARD_PW)                                  "
				+ "                                            "
				+ " VALUES(                                    "
				+ "     SEQ_NUMBER_BOARD.nextval,              "
				+ "     SYSDATE,                               "
				+ "     ?,                                     "
				+ "     ?,                                     "
				+ "     ?,                                     "
				+ "     ?                                      "
				+ " )                                          ";
		return jdbc.update(sql, param);
	}

	

}
