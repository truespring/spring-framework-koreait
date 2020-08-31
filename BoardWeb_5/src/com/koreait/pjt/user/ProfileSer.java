package com.koreait.pjt.user;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/profile")
public class ProfileSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 프로필 화면 (나의 프로필 이미지, 이미지 변경 가능한 화면)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login");
			return;
		}
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		request.setAttribute("data", UserDAO.selUser(loginUser.getI_user()));
		ViewResolver.forward("user/profile", request, response);
		
	}
	
	// 이미지 변경 처리 - 쿼리 스트링의 길이에 한계가 없음
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		String savePath = getServletContext().getRealPath("img") + "/user/" + loginUser.getI_user(); // 어플리케이션을 받아오고   
		System.out.println("savePath : " + savePath); // 절대경로
		
		// 만약 폴더(디렉토리)가 없다면
		File directory = new File(savePath);
		if(!directory.exists()) { // 존재하면 true, 존재하지 않으면 false
			directory.mkdirs(); // 여러개를 만들 수 있다.
		}
		
		
		int maxFileSize = 10_485_760; // 1024 * 1024 * 10(10Mb) _는 없는 것과 같다(자릿수 표현)
		String fileNm = "";
//		String originFileNm = "";
		String saveFileNm = "";
		
		try {
			MultipartRequest mr = new MultipartRequest(request, savePath, maxFileSize
					, "UTF-8", new DefaultFileRenamePolicy());
			
			Enumeration files = mr.getFileNames(); // 파일을 여러 개 업로드 할 경우 Enumeration 타입으로 변환
			
			while(files.hasMoreElements()) {
				String key = (String)files.nextElement(); // jsp에서 가져온 키값
//				originFileNm = mr.getOriginalFileName(key); // 업로드한 파일의 이름
				fileNm = mr.getFilesystemName(key); // 파일의 이름을 바꿈(중복되면 (1)을 붙임)
				
				System.out.println("key : " + key);
//				System.out.println("originFileNm : " + originFileNm);
				System.out.println("fileNm : " + fileNm);
				
				
				int pos = fileNm.lastIndexOf( "." ); // 마지막에 있는 . 찾는 방법
				String ext = fileNm.substring(pos);
				saveFileNm = UUID.randomUUID() + ext;
				
				System.out.println("ext : " + ext); // 확장자 가져오기
				System.out.println("saveFileNm : " + saveFileNm);
				
				
				File oldFile = new File(savePath + "/" + fileNm); // savePath의 경로의 마지막에는 폴더로 끝나기 때문에 /를 삽입해야 한다.
				File newFile = new File(savePath + "/" + saveFileNm); // UUID.randomUUID() 임의의 문자열을 생성해준다.
				
				oldFile.renameTo(newFile); // 예전 파일의 이름을 변경하는 작업
			}
		} catch(Exception e) {
			e.printStackTrace();
		} 
		if(saveFileNm != null) { // DB에 프로필 파일명 저장
			UserVO param = new UserVO();
			param.setProfile_img(saveFileNm);
			param.setI_user(loginUser.getI_user());
			
			UserDAO.updUser(param);
		}
		response.sendRedirect("/profile");
	}

}
