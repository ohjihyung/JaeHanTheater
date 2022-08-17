package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class ReviewDAO {

	private static ReviewDAO instance = null;

	private ReviewDAO() {
	}

	public static ReviewDAO getInstance() {
		if (instance == null) {
			instance = new ReviewDAO();
		}
		return instance;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();
	
String sql = null;
	
	public Map<String, Object> dramaInfo (Object selectedDrama) {		
		sql = "SELECT THEATER_TITLE, " 
				+ "   AVG(REVIEW_SCORE), " 
				+ "   COUNT(REVIEW_ID) "
				+ "FROM REVIEW "
				+ "WHERE THEATER_TITLE = '" + selectedDrama + "' "
				+ "GROUP BY THEATER_TITLE ";
		Map<String, Object> row = jdbc.selectOne(sql);
		return row;
	}
	
	public List<Map<String, Object>> showReview(String dramaTitle){
		sql = "SELECT * FROM REVIEW "
				+ " WHERE THEATER_TITLE = TRIM('" + dramaTitle + "')";
		List<Map<String, Object>> rows = jdbc.selectList(sql);
		return rows;
	}

	public Map<String, Object> selectReview(int input){
		sql = " SELECT * FROM REVIEW WHERE REVIEW_ID=' " + input + " ' ";
		Map<String, Object> row = jdbc.selectOne(sql);
		return row;
	}
	
	public int writeReview(List<Object> param){
		sql = "INSERT INTO REVIEW( 					"
				+ "REVIEW_ID, 						"
				+ "REVIEW_SCORE, 					"
				+ "REVIEW_CONTENT, 					"
				+ "TICKETING_ID, 					"
				+ "THEATER_TITLE) 					"
				+ "									"
				+ "VALUES(							"
				+ "   SEQ_NUMBER_REVIEW.nextval,	"
				+ "	  ?,							"
				+ "	  ?,							"
				+ "	  ?,         					" //★임시 '0202-0814-002'
				+ "	  ? )						    ";//★임시 '드립소년단'						
		return jdbc.update(sql, param);	
	}
		
	public Map<String, Object> myTicketing(String userID) {
		sql = "SELECT * "
				+"FROM TICKETING "
				+"WHERE USER_ID = '" + userID + "'";
		Map<String, Object> row = jdbc.selectOne(sql);
		return row;
	}
	
	public Map<String, Object> checkReview(String ticketingID) {
		sql = "Select * "
				+ "FROM REVIEW "
				+ "WHERE TICKETING_ID = '" + ticketingID + "'";
		Map<String, Object> row = jdbc.selectOne(sql);
		return row;
	}

	public int modifyReview(int num, String value, int selectReviewID) {
		sql = "UPDATE REVIEW "
				+ "SET ";
		switch(num){
		case 1:
			sql += "REVIEW_SCORE = ";
			break;
		case 2:
			sql += "REVIEW_CONTENT = ";
			break;
		}
		sql += " '" + value + "' ";
		sql +=  " WHERE REVIEW_ID = '" + selectReviewID + "' ";	 
		return jdbc.update(sql);
	}

	public int deleteReview(int selectReviewID) {
		sql = " DELETE FROM BOARD " + 
				  " WHERE REVIEW_ID = '" + selectReviewID + "' ";
			return jdbc.update(sql);
	}
	
}
