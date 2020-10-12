package kr.co.dong.board;

import java.util.List;
import java.util.Map;

public interface BoardService {
   // 삽입, 삭제, 갱신(수정) 메소드의 리턴타입은 되도록이면 int
   // 전체 목록을 가져오는 메소드
   public List<BoardDTO> list();
   
   // 상세보기 처리를 위한 메소드 (글읽기)
   public int updateReadCnt(int bno);	//조회수 증가
   public BoardDTO getDetail(int bno);	//글읽기
// public BoardDTO getRead(int bno);	//조회수 증가 및 글 읽기 처리
   
   // 글추가
   public int register(BoardDTO boardDTO); 
   
   // 글 삭제 처리
   public int delete(int bno);
   
   // 글 수정 처리
   public int update(BoardDTO dto);
   
   // 로그인 처리를 위한 메소드
   public Map login(Map<String,Object> map);
   
   // 댓글 쓰기를 위한 메소드
   public int reply(BoardReply BoardReply);
   // 게시물 번호에 해당하는 댓글 번호를 가져오는 메소드
   public List<BoardReply> getDetail1(int bno);
   // 댓글 수정을 처리하기 위한 메소드
   public int replyupdate(BoardReply BoardReply);
   //해당 댓글을 보기 위한(수정) 메소드
   public BoardReply replydetail(int reno);
   //해당 댓글을 삭제 메소드
   public int replydelete(int reno); 
   // 게시물 총 갯수
   public int countBoard();
   // 페이징 처리 게시글 조회
   public List<BoardDTO> selectBoard(PagingVO vo);

}