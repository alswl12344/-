package kr.co.dong.board;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO {
   @Inject
   private SqlSession sqlSession;

   private static final String nameSpace = "kr.co.dong.board";
   
   @Override
   public List<BoardDTO> list() {
      // 목록
      return sqlSession.selectList(nameSpace + ".list");
   }

   @Override
   public int updateReadCnt(int bno) {
      // 조회수
      return sqlSession.update(nameSpace + ".updateReadCnt",bno);
   }

   @Override
   public BoardDTO getDetail(int bno) {
      // 하나
      return sqlSession.selectOne(nameSpace + ".detail", bno);
   }

   @Override
   public int register(BoardDTO boardDTO) {
      // 추가
      return sqlSession.insert(nameSpace + ".register", boardDTO);
   }

   @Override
   public int delete(int bno) {
      // 삭제
      return sqlSession.delete(nameSpace + ".delete", bno);
   }

   @Override
   public int update(BoardDTO dto) {
      // 수정
      return sqlSession.update(nameSpace + ".update", dto);
   }

   @Override
   public Map login(Map<String, Object> map) {
      // 로그인하나?
      return sqlSession.selectOne(nameSpace+".login", map);
   }

@Override
public int reply(BoardReply BoardReply) {
	// 댓글 추가
	return sqlSession.insert(nameSpace+".reply", BoardReply);
}

@Override
public List<BoardReply> getDetail1(int bno) {
	// 댓글 목록
	return sqlSession.selectList(nameSpace+".detail1", bno);
}

@Override
public int replyupdate(BoardReply BoardReply) {
	// 댓글 수정
	return sqlSession.update(nameSpace+".replyupdate", BoardReply);
}

@Override
public BoardReply replydetail(int reno) {
	// 댓글 목록 하나
	return sqlSession.selectOne(nameSpace+".replydetail", reno);
}

@Override
public int replydelete(int reno) {
	// 댓글 삭제
	return sqlSession.delete(nameSpace+".replydelete", reno);
}

@Override
public int countBoard() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public List<BoardDTO> selectBoard(PagingVO vo) {
	// TODO Auto-generated method stub
	return null;
}

   
}