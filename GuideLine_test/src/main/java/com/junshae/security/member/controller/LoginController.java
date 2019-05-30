package com.junshae.security.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.junshae.security.member.dao.MemberDAO;
//import org.springframework.web.servlet.mvc.Controller;


@Controller // @Component + Controller
public class LoginController{
	
	// 로그인
	@RequestMapping(value="/login.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인 처리");
		// 데이터
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
				
		// DB
		MemberDAO memberDAO = new MemberDAO();
		String name = memberDAO.login(id, pwd);
		// 화면 네비게이션
		ModelAndView modelAndView = new ModelAndView();
		if(name == null) {	// 로그인 실패
			modelAndView.setViewName("loginForm.jsp");
			//response.sendRedirect("loginForm.jsp");
			//return "loginForm";
		} else {			// 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("memId", id);
			session.setAttribute("memName", name);
						
			//modelAndView.setViewName("redirect:../board/boardList.do?pg=1");
			modelAndView.setViewName("redirect:./main/main.jsp");
			//response.sendRedirect("../board/boardList.do?pg=1");
			//return "../board/boardList.do?pg=1";
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response){		
		HttpSession session = request.getSession();
        
		System.out.println("before session : "+ session.getAttribute("memId"));
		
		session.invalidate();
      //session.removeAttribute("memId");
		
        System.out.println("after session : " + request.getSession());
        
        
        //return "redirect:/login/loginForm.jsp";
        return "redirect:./index.html";
        
    }

	
	@RequestMapping(value="/login/kakaologin.do", produces="application/json" ,method=RequestMethod.GET)
		public ModelAndView kakaologin(@RequestParam("code") String code, RedirectAttributes ra, HttpSession session,
				HttpServletRequest request, HttpServletResponse response) throws IOException {
			System.out.println("kakao code : " + code );
			JsonNode accessToken = null;
			
			 // JsonNode트리형태로 토큰받아온다
	        JsonNode jsonToken = KakaoAccessToken.getKakaoAccessToken(code);
	        
	        // 여러 json객체 중 access_token을 가져온다
	        accessToken = jsonToken.get("access_token");
	 
	        System.out.println("access_token : " + accessToken);	        
	        	        	        
	        JsonNode jsonToken2 = KakaoAccessToken.getKakaoUserInfo(accessToken, code);
	        
	        // Get id
	 		String kakao_id = jsonToken2.path("id").asText();
	 		String nickname = null;
	 		String thumbnailImage = null;
	 		String profileImage = null;
	 		String login_type = "kakao";
	     		

	        // 유저정보 카톡에서 가져오기 Get properties
			JsonNode properties = jsonToken2.path("properties");
			if (properties.isMissingNode()) {
				// if "name" node is missing
				System.out.println("miss \n");
				
			} else {
				
				nickname = properties.path("nickname").asText();
				thumbnailImage = properties.path("thumbnail_image").asText();
				profileImage = properties.path("profile_image").asText();
				

				System.out.println("kakao_id : " + kakao_id);
				System.out.println("nickname : " + nickname);
				System.out.println("thumbnailImage : " + thumbnailImage);
				System.out.println("profileImage : " + profileImage);
				System.out.println("login_type : " + login_type);
			}	  
			
			
			ModelAndView modelAndView = new ModelAndView();
			if(nickname == null) {	// 로그인 실패
				modelAndView.setViewName("loginForm.jsp");
				
			} else {			// 로그인 성공
				session = request.getSession();
				session.setAttribute("memId", kakao_id);
				session.setAttribute("memName", nickname);				
				session.setAttribute("login_type", login_type);
				session.setAttribute("accessToken", accessToken);
							
				
				System.out.println("kakao session id : "+session.getAttribute("memId"));
				
				//modelAndView.setViewName("redirect:../board/boardList.do?pg=1");
				modelAndView.setViewName("redirect:/main/main.jsp");
				
			}
			return modelAndView;
		}
	
	@RequestMapping(value="/kakao_logout.do")
		public ModelAndView logout(HttpSession session) {
			KakaoAccessToken.kakaoLogout((JsonNode)session.getAttribute("accessToken"));
			System.out.println("after logout Method");
		   
			
		    session.invalidate();    
			
			
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("redirect:/A-team-index.jsp");
		    return modelAndView;
	}

}

	/*// 로그아웃 
	
	@RequestMapping(value="/logout.do", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/test.jsp";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}*/


