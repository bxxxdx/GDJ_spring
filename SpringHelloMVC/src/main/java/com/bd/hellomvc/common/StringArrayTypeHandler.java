package com.bd.hellomvc.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class StringArrayTypeHandler implements TypeHandler<String[]> {

	@Override
	public String[] getResult(ResultSet rs, String col) throws SQLException {
		//String 변수 부분엔 Column명
		
		return rs.getString(col)!=null?rs.getString(col).split(","):new String[0];
	}

	@Override
	public String[] getResult(ResultSet rs, int col) throws SQLException {
		
		return rs.getString(col)!=null?rs.getString(col).split(","):new String[0];
	}

	@Override
	public String[] getResult(CallableStatement arg0, int arg1) throws SQLException {
		
		return arg0.getString(arg1)!=null?arg0.getString(arg1).split(","):new String[0];
	}

	@Override
	public void setParameter(PreparedStatement pstmt, int index, String[] param, JdbcType arg3) throws SQLException {
		if(param != null) {
			pstmt.setString(index, String.join(",", param));
		} else {
			pstmt.setString(index, "");
		}
	}

}
