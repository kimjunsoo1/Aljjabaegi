package com.junshae.security.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64.Decoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.junshae.security.board.bean.AttachFile;
import com.junshae.security.board.bean.BoardDTO;
import com.junshae.security.board.dao.BoardDAO;
import com.junshae.security.my.commons.WebContants;


@Controller
public class BoardController {
	@Autowired
	private BoardDAO boardDAO;
	
	@RequestMapping(value="/board/boardList.do")
	public ModelAndView boardList(HttpServletRequest request) {
		System.out.println("글 목록 처리");
		
		HttpSession session = request.getSession();
		
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("memId") == null) {
			System.out.println("memId Null.");
			modelAndView.setViewName("/login/no-cache-warning.jsp"); 
			return modelAndView;
		}
		
		// 데이터
		//String test = request.getParameter("test");
		int pg = Integer.parseInt(request.getParameter("pg"));
		// DB 
		// 1페이지당 5개씩
		int endNum = pg*10;			// 1 * 10 = 10
		int startNum = endNum - 9;	// 10 - 9 = 1
		
		//BoardDAO boardDAO = new BoardDAO();
		List<BoardDTO> list = boardDAO.boardList(startNum, endNum);
		// 페이징 처리
		int totalA = boardDAO.getTotalA();	// 총글수 = 58
		int totalP = (totalA + 9) / 10;		// 총페이지수 = (58 + 9) / 10 = 6
		// 3블록
		int startPage = (pg-1)/5*5+1;	// (1-1)/3*3+1 = 1
		int endPage = startPage + 4;	// 1+2 = 5
		if(totalP < endPage) endPage = totalP;
		// 검색 결과를 request에 저장하고 목록 화면으로 이동한다.
		
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("list", list);
		modelAndView.addObject("startPage", startPage);
		modelAndView.addObject("endPage", endPage);
		modelAndView.addObject("totalP", totalP);
		
		modelAndView.setViewName("boardList.jsp");
		return modelAndView;
//		request.setAttribute("pg", pg);
//		request.setAttribute("list", list);
//		request.setAttribute("startPage", startPage);
//		request.setAttribute("endPage", endPage);
//		request.setAttribute("totalP", totalP);
//		return "boardList";
	}
	
	@RequestMapping(value="/board/boardView.do")
	public ModelAndView boardView(HttpServletRequest request) {
		System.out.println("글 상세 조회 처리");
		// 데이터
		//String test = URLDecoder.decode(request.getParameter("test"));
		String test= "good";
		
		
		//String test = request.getParameter("test");
		System.out.println("test"+test);
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		int pg = Integer.parseInt(request.getParameter("pg"));
		List<AttachFile> attachFileList = boardDAO.getAttachFileList(seq);
		// DB
		//BoardDAO boardDAO = new BoardDAO();
		// 조회수 증가
		boardDAO.updateHit(seq);
		BoardDTO boardDTO = boardDAO.boardView(seq);
		// 검색 결과를 request에 저장하고 상세 화면으로 이동한다.
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("seq", seq);
		modelAndView.addObject("test", test);
		modelAndView.addObject("boardDTO", boardDTO);
		modelAndView.addObject("attachFileList", attachFileList);
		for (AttachFile attachFile : attachFileList) {
			System.out.println("finally : " + attachFile.getFilename());
		}
		
		modelAndView.setViewName("boardView.jsp");
		return modelAndView;
//		request.setAttribute("pg", pg);
//		request.setAttribute("seq", seq);
//		request.setAttribute("boardDTO", boardDTO);
//		return "boardView";
	}
	
	@RequestMapping(value="/board/boardDelete.do")
	public ModelAndView boardDelete(HttpServletRequest request) {
		System.out.println("글 삭제 처리");
		// 데이터
		int seq = Integer.parseInt(request.getParameter("seq"));
		// DB
		//BoardDAO boardDAO = new BoardDAO();
		int su = boardDAO.boardDelete(seq);
		// 검색 결과를 request에 저장하고 삭제 화면으로 이동한다.
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("su", su);
		modelAndView.setViewName("boardDelete.jsp");
		return modelAndView;
//		request.setAttribute("su", su);
//		return "boardDelete";
	}
	
	@RequestMapping(value="/board/boardWriteForm.do")
	public ModelAndView boardWriteForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("boardWriteForm.jsp");
		return modelAndView;
		//return "boardWriteForm";
	}
	
	@RequestMapping(value="/board/boardWrite.do")
	public ModelAndView boardWrite(MultipartHttpServletRequest request) {
		System.out.println("글 등록 처리");
		// 데이터
		try { //한글이 깨지지 않도록 인코딩 설정
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String id = (String) session.getAttribute("memId");
		String name = (String) session.getAttribute("memName");
		
		
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setName(name);
		boardDTO.setId(id);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		
		int su = boardDAO.boardWrite(boardDTO);
				
		boardDTO.setSeq(boardDAO.getNewArticle().getSeq());
		
		List<MultipartFile> fileList = request.getFiles("upload");
		for(MultipartFile mf : fileList){
			String filename = mf.getOriginalFilename();
			try {
				mf.transferTo(new File(WebContants.BASE_PATH + filename));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("su : "+su);
		System.out.println("boardDTO " + boardDTO.getSeq());
		//파일데이터 삽입
				int size = fileList.size();
				for (int i = 0; i < size; i++) {
					MultipartFile mpFile = fileList.get(i);
					AttachFile attachFile = new AttachFile();
					String filename = mpFile.getOriginalFilename();	
					System.out.println("파일명 저장 " +filename);
					attachFile.setFilename(filename);
					attachFile.setFiletype(mpFile.getContentType());
					attachFile.setFilesize(mpFile.getSize());
					attachFile.setArticleNo(boardDTO.getSeq());
					boardDAO.insertAttachFile(attachFile);
				}	
		System.out.println("글등록 호출 Controller : ");
		
		// DB
		//BoardDAO boardDAO = new BoardDAO();
		
		// 검색 결과를 request에 저장하고 저장 화면으로 이동한다.
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("su", su);
		modelAndView.setViewName("boardWrite.jsp");
		return modelAndView;
//		request.setAttribute("su", su);
//		return "boardWrite";
	}
	
	@RequestMapping(value="/board/download.do", method=RequestMethod.POST)
	public String download(String filename, ModelAndView model){
		model.addObject("filename", filename);
		return "../include/download.jsp";
	}
	
	/*@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(Article article,
			MultipartHttpServletRequest mpRequest) throws Exception {
		
		article.setEmail("비회원"); //임시
		boardService.insert(article);
		article.setArticleNo(boardService.getNewArticle().getArticleNo());
		
		//파일업로드
		List<MultipartFile> fileList = mpRequest.getFiles("upload");
		for(MultipartFile mf : fileList){
			String filename = mf.getOriginalFilename();
			mf.transferTo(new File(WebContants.BASE_PATH + filename));
		}
		
		//파일데이터 삽입
		int size = fileList.size();
		for (int i = 0; i < size; i++) {
			MultipartFile mpFile = fileList.get(i);
			AttachFile attachFile = new AttachFile();
			String filename = mpFile.getOriginalFilename();
			attachFile.setFilename(filename);
			attachFile.setFiletype(mpFile.getContentType());
			attachFile.setFilesize(mpFile.getSize());
			attachFile.setArticleNo(article.getArticleNo());
			boardService.insertAttachFile(attachFile);
		}					
		
		return "redirect:/bbs/list?boardCd=" + article.getBoardCd();
	}*/
	
}










