package kr.co.dong.board;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class Boardcontroller {
   private static final Logger logger = LoggerFactory.getLogger(Boardcontroller.class);
   
   @Autowired
   BoardService boardService;
   
   @RequestMapping(value="board/login", method=RequestMethod.GET)
   public String login() {
      logger.info("로그인 뷰로 이동");
      return "login";
   }
   
   @RequestMapping(value="board/login", method=RequestMethod.POST)
   public String login(@RequestParam Map<String,Object> map, HttpServletRequest request,HttpSession session) throws Exception {
      request.setCharacterEncoding("utf-8");
      logger.info("=====[login id password]=====");
      Map user = boardService.login(map);
      if(user == null) {
         logger.info("로그인 안 됨");
         return "redirect:login";
      }
      else {
         //세션 부여
         session.setAttribute("user", user);
         logger.info("로그인 됨");
         return "redirect:/"; //홈으로 감
      }
   }
   
   @RequestMapping(value="board/list", method=RequestMethod.GET)
   public ModelAndView list() {
      ModelAndView mav = new ModelAndView();
      
      List<BoardDTO> list = boardService.list();
      mav.addObject("list", list);
      mav.setViewName("list");
      return mav;
   }
   //글 추가
   @RequestMapping(value="board/register", method=RequestMethod.GET)
   public String register() {
      
      return "register";
   }
   
   @RequestMapping(value="board/register", method=RequestMethod.POST)
   public String register(BoardDTO boardDTO,RedirectAttributes rttr) {
      int r = boardService.register(boardDTO);
      
      if(r>0) { //성공
         rttr.addFlashAttribute("msg", "추가에 성공하였습니다.");
      }
      return "redirect:list";
   }
// 전체목록
	@RequestMapping(value="board/listE", method=RequestMethod.GET)
	public ModelAndView listE() {
		ModelAndView mav = new ModelAndView();
		List<BoardDTO> list = boardService.list();
		mav.addObject("list", list);
		mav.setViewName("list");
		return mav;
	}
// 전체목록(페이징 처리)
	@RequestMapping(value="board/boardPaging", method=RequestMethod.GET)
	public String list(PagingVO vo, Model model, @RequestParam(value="nowPage", required=false)String nowPage, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		int total = boardService.countBoard();
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if(nowPage == null) {
			nowPage = "1";
		} else if(cntPerPage == null) {
			cntPerPage = "5";
		}
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", boardService.selectBoard(vo));
		return "boardPaging";
	}
   //상세보기
   @RequestMapping(value="board/detail",method=RequestMethod.GET)
   public String detail(@RequestParam("bno") int bno, Model model) {
      // 조회수 증가와 글읽기 ===> service에서 처리해야함.
      boardService.updateReadCnt(bno);
      BoardDTO boardDTO = boardService.getDetail(bno);
      model.addAttribute("board", boardDTO);
//      model.addAttribute("board", boardServie.getDetail(bno));
      
      //댓글 목록 조회
      List<BoardReply> replylist = boardService.getDetail1(bno);
      model.addAttribute("list", replylist);
      
      return "detail";
   }
   
   
   //글 수정
   @RequestMapping(value="board/update", method=RequestMethod.GET)
   public String update() {

      return "update";
   }
   @RequestMapping(value="board/update", method=RequestMethod.POST)
   public String update(BoardDTO dto,RedirectAttributes rttr) {
      int r = boardService.update(dto);
      if(r>0) {
         rttr.addFlashAttribute("msg", "수정에 성공하였습니다.");
      }
      return "redirect:list";
   }
   //글 삭제
   @RequestMapping(value="board/delete", method=RequestMethod.GET)
   public String delete(@RequestParam("bno") int bno,RedirectAttributes rttr) {
      int r = boardService.delete(bno);
      if(r>0) {
         rttr.addFlashAttribute("msg", "삭제에 성공하였습니다.");
      }
      return "redirect:list";
   }
   //댓글작성
   @RequestMapping(value="board/reply", method=RequestMethod.GET)
   public String reply() {
      return "reply";
   }
   
   @RequestMapping(value="board/reply", method=RequestMethod.POST)
   public String reply(BoardReply boardReply) {
      int r = boardService.reply(boardReply);
      // 
      if(r>0) {
         return "redirect:detail?bno=" + boardReply.getBno();
      }
      return "reply";
   }
   
   @RequestMapping(value="board/replyupdate", method=RequestMethod.GET)
   public ModelAndView replyupdate(int reno) {
      ModelAndView mav = new ModelAndView();
      
      BoardReply br = boardService.replydetail(reno);
      mav.addObject("br", br);
      mav.setViewName("replyupdate");
      return mav;
   }
   
   @RequestMapping(value="board/replyupdate", method=RequestMethod.POST)
   public String replyupdate(BoardReply boardr) {
      int r = boardService.replyupdate(boardr);
      if(r>0) {
         return "redirect:detail?bno=" + boardr.getBno();
      }
      return "redirect:/";
   }
   
   @RequestMapping(value="board/replydelete", method=RequestMethod.GET)
   public String replydelete(@RequestParam("reno") int reno,RedirectAttributes rttr) {
      int r = boardService.replydelete(reno);
      if(r>0) {
         rttr.addFlashAttribute("msg", "댓글 삭제 성공");
      }
      return "redirect:list";
   }
   
   // ajax 댓글을 위한 매핑 "board/replylist" 설정
   // ajax를 할 때에는 꼭 @ResponseBody를 붙여줘야함
   // 글 번호가 요청되어야 함.
   // json 처리를 위한 라이브러리가 필요 => jackson-databind
   @ResponseBody
   @RequestMapping(value="board/replylist",method=RequestMethod.POST)
   public List<BoardReply> replylist(@RequestParam("bno") int bno) {
      return boardService.getDetail1(bno);
   }
   
   // ajax 댓글에 대한 매핑과 메소드 구현
   @ResponseBody
   @RequestMapping(value="board/replyUpdate2", method=RequestMethod.POST)
//   public int replyUpdate2(BoardReply boardr) {
//      return boardService.replyupdate(boardr);
//   }
   public Map<String,Object> replyUpdate2(BoardReply boardr) {
      Map<String, Object> result = new HashMap<String, Object>();
      try {
         boardService.replyupdate(boardr);
         result.put("status","OK");
      } catch (Exception e) {
         e.printStackTrace();
         result.put("status", "fail");
      }
      return result;
   }
   
   // ajax 댓글 삭제 매핑과 메소드 구현
   @ResponseBody
   @RequestMapping(value="board/replyDelete2", method=RequestMethod.POST)
   
   public Map<String,Object> replyDelete2(int reno) {
      Map<String, Object> result = new HashMap<String, Object>();
      try {
         boardService.replydelete(reno);
         result.put("status","OK");
      } catch (Exception e) {
         e.printStackTrace();
         result.put("status", "fail");
      }
      return result;
   }
   // ajax 쓰기
   @ResponseBody
   @RequestMapping(value="board/reply2", method=RequestMethod.POST)
   
   	  public int reply2(BoardReply boardReply) {
	   return boardService.reply(boardReply);
   }
   
}