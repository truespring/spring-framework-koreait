package com.koreait.pjt.db;

import java.sql.*;
// 인터페이스는 부모역할만 하기 때문에 자식을 가리킬 수 있다
public interface JdbcUpdateInterface {
	void update(PreparedStatement ps) throws SQLException; // public과 abstract가 생략되어 있다
//	선언부만 있기 때문에 객체화가 되지 않는다
//	인터페이스에 있는 메소드는 사용시 무조건 구현해야한다. 강제성이 있기 때문이다.
//	UserDAO에서 사용하게 된다.
}
