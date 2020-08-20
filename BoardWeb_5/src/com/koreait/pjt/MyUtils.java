package com.koreait.pjt;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.vo.UserVO;

public class MyUtils {
//	return으로 true:로그인이 안됨, false:로그인 된 상태
	public static boolean isLogout(HttpServletRequest request) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		if(null == hs.getAttribute(Const.LOGIN_USER)) {
			return true;
		}
		return false;
	}
	
	public static UserVO getLoginUser(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		return (UserVO)hs.getAttribute(Const.LOGIN_USER);
	}
	
	public static String encryptString(String str) {
//		비밀번호 단방향 암호화
		String sha = "";

		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); // 문자열 합칠 때 사용
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			sha = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// e.printStackTrace();
			System.out.println("Encrypt Error - NoSuchAlgorithmException");
			sha = null;
		}

		return sha;
	}
}
