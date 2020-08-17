package com.koreait.pjt.db;

import java.sql.*;

public interface JdbcUpdateInterface {
	int update(PreparedStatement ps) throws SQLException;
}
