package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import controller.ControllerV2;
import dao.ReviewDAO;
import util.ScanUtil;
import util.View2;

public class ReviewService {

	private static ReviewService instance = null;
    private ReviewService() {
    }
    public static ReviewService getInstance() {
        if (instance == null) {
            instance = new ReviewService();
        }
        return instance;
    }
	
    ReviewDAO dao = ReviewDAO.getInstance();
    DramaService dramaService = DramaService.getInstance();
    public int showReview() {
        Map<String, Object> dramaInfo = dao.dramaInfo(dramaService.selectedDrama);
        System.out.println("<" + dramaService.selectedDrama + "> 리뷰 게시판");
        if (dramaInfo == null || dramaInfo.size() == 0) {
            System.out.println("등록된 리뷰가 없습니다.");
            System.out.println("아무 버튼이나 클릭하시면 다시 연극 메뉴로 돌아갑니다.");
            ScanUtil.nextLine();
            return View2.DRAMA;
        }else {
        System.out.print("평균 평점 " + dramaInfo.get("AVG(REVIEW_SCORE)") + "점(");
        System.out.println(dramaInfo.get("COUNT(REVIEW_ID)") + "명 참여)");
        }
        
        
        List<Map<String, Object>> rows = dao.showReview((String) dramaService.selectedDrama);
        System.out.printf("\n%s%20s%18s%30s\n", "번호", "평점", "예매번호", "리뷰");
        if (rows == null || rows.size() == 0) {
        } else {
            for (Map<String, Object> item : rows) {
                System.out.printf("%2s%20s", item.get("REVIEW_ID"), "");
                int result = Integer.parseInt((String.valueOf(item.get("REVIEW_SCORE"))));
                switch (result) {
                case 1:
                    System.out.print("☆☆☆☆★");
                    break;
                case 2:
                    System.out.print("☆☆☆★★");
                    break;
                case 3:
                    System.out.print("☆☆★★★");
                    break;
                case 4:
                    System.out.print("☆★★★★");
                    break;
                case 5:
                    System.out.print("★★★★★");
                    break;
                default:
                    break;
                }
                System.out.printf("%20s\t", item.get("TICKETING_ID"));
                if (String.valueOf(item.get("REVIEW_CONTENT")).length() < 20){
                    System.out.print(String.valueOf(item.get("REVIEW_CONTENT")) + "…더보기");
                }else{System.out.println(String.valueOf(item.get("REVIEW_CONTENT")).substring(0, 20)+ "…더보기");
                }
                System.out.println();
            }
        }
        System.out.println("--------------------------------------------------");
        while (true) {
            if (ControllerV2.loggedInUser == false) {
                System.out.println("1. 리뷰 자세히 보기 0. 리뷰게시판 나가기");
            } else {
                System.out.println("1. 리뷰 자세히 보기 2. 리뷰 작성 0. 리뷰게시판 나가기");
            }
            System.out.print("입력 >>> ");
            switch (ScanUtil.nextInt()) {
            case 1:
                return View2.REVIEW_SELECT;
            case 2:
                return View2.REVIEW_WRITE;
            case 0:
                return View2.HOME;
            default:
                break;
            }
        }
    }
    public int selectReview() {
        Map<String, Object> row = null;
        System.out.print("조회할 리뷰 번호 입력>>");
        int input = ScanUtil.nextInt();
        row = dao.selectReview(input);
        if (row == null || row.size() == 0) {
            System.out.println("글이 존재하지 않습니다");
        } else {
            System.out.println("▶ 글 번호: " + row.get("REVIEW_ID"));
            System.out.println("▶ 평점: " + row.get("REVIEW_SCORE"));
            System.out.println("▶ 리뷰 내용 ");
            System.out.println(row.get("REVIEW_CONTENT"));
            System.out.print("리뷰게시판으로 돌아갑니다.");
            ScanUtil.nextLine();
        }
        return View2.REVIEW;
    }
    
    public int writeReview() { //예매내역, 리뷰 작성 여부 확인 후 리뷰 작성
        String userID = (String) ControllerV2.userInfo.get("USER_ID");
        Map<String, Object> myRow = dao.myTicketing(userID);
        if (myRow == null || myRow.size() == 0) {
            System.out.println("예매내역이 없으므로 리뷰 작성 대상이 아닙니다.");
            return View2.REVIEW;
        }else {
            System.out.print("예매내역이 존재하는 당신! ");    
        }
        Map<String, Object> row2 = dao.checkReview((String) myRow.get("TICKETING_ID"));
        if (row2 == null || row2.size() == 0) {
            System.out.println("리뷰 작성 대상입니다.");
            System.out.print("리뷰 평점 입력(최하 1점 / 최대 5점) >>>");
            int reviewScore;
            do {
                reviewScore = ScanUtil.nextInt();
                if (reviewScore < 1 || reviewScore > 5)
                    System.out.print("1~5점 사이의 평점이 아닙니다. 다시 입력하십시오>>");
            } while (reviewScore < 1 || reviewScore > 5);
            System.out.print("리뷰 내용 입력>>");
            String reviewContent = ScanUtil.nextLine();
            List<Object> param = new ArrayList<>();
            param.add(reviewScore);
            param.add(reviewContent);
            param.add((String) myRow.get("TICKETING_ID"));
            param.add("드립소년단"); //**********************임시
            int result = dao.writeReview(param);
            System.out.println(result + "개 작성되었습니다.");
        }
        else {
            System.out.println("이미 작성된 리뷰가 있습니다.");
        }
        return View2.REVIEW;
    }
    public int modifyReview() {
        return 0;
    }
    public int deleteReview() {
        return 0;
    }
    
}
