/*
 * DAO 호출하기 
 */

package kr.co.dong.board;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;
	@Override
	   public List<BoardDTO> list() {
	      // TODO Auto-generated method stub
	      return boardDAO.list();
	   }
	
	   @Override
	   public int updateReadCnt(int bno) {
	      // TODO Auto-generated method stub
	      return boardDAO.updateReadCnt(bno);
	   }
	
	   @Override
	   public BoardDTO getDetail(int bno) {
	      // TODO Auto-generated method stub
	      return boardDAO.getDetail(bno);
	   }
	
	   @Override
	   public int register(BoardDTO boardDTO) {
	      // TODO Auto-generated method stub
	      return boardDAO.register(boardDTO);
	   }
	
	   @Override
	   public int delete(int bno) {
	      // TODO Auto-generated method stub
	      return boardDAO.delete(bno);
	   }
	
	   @Override
	   public int update(BoardDTO dto) {
	      // TODO Auto-generated method stub
	      return boardDAO.update(dto);
	   }
	
	   @Override
	   public Map login(Map<String, Object> map) {
	      // TODO Auto-generated method stub
	      return boardDAO.login(map);
	   }

	@Override
	public int reply(BoardReply BoardReply) {
		// TODO Auto-generated method stub
		return boardDAO.reply(BoardReply);
	}

	//해당 게시글에 대한 댓글 전체목록
	@Override
	public List<BoardReply> getDetail1(int bno) {
		// TODO Auto-generated method stub
		return boardDAO.getDetail1(bno);
	}

	@Override
	public int replyupdate(BoardReply BoardReply) {
		// TODO Auto-generated method stub
		return boardDAO.replyupdate(BoardReply);
	}

	@Override
	public BoardReply replydetail(int reno) {
		// TODO Auto-generated method stub
		return boardDAO.replydetail(reno);
	}

	@Override
	public int replydelete(int reno) {
		// TODO Auto-generated method stub
		return boardDAO.replydelete(reno);
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