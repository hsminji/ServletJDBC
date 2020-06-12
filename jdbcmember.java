package com.hansin.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class jdbcmember extends HttpServlet{
	
	public static void main(String[] args) {

		String jdbc_driver = "com.mysql.cj.jdbc.Driver";

		String jdbc_url = "jdbc:mysql://127.0.0.1:3306/jdbcmember?serverTimezone=UTC";

		try {

			Class.forName(jdbc_driver).newInstance();

			Connection con = DriverManager.getConnection(jdbc_url, "root", "123456");

			Statement st = con.createStatement();

			String sql = "SELECT * FROM jdbcmember.memberdb";

			ResultSet rs = st.executeQuery(sql);
			

			while(rs.next()) {

				String id = rs.getString("id");

				String pwd = rs.getString("pwd");
				
				String name = rs.getString("name");

				String tel = rs.getString("tel");

				String email = rs.getString("email");

				String dept = rs.getString("dept");
				
				String gender = rs.getString("gender");
				
				String birth = rs.getString("birth");
				
				String introduction = rs.getString("introduction");

				System.out.printf("id:  %s, pwd: %s, name: %s, tel: %s, email: %s, dept: %s, gender: %s, birth: %s, introduction: %s"

						+ "\n", id, pwd, name, tel, email, dept, gender, birth, introduction);
			}
			rs.close();

			st.close();

			con.close();

		} catch (Exception e) {

			e.printStackTrace();

		} 
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 실행 됨");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.sendRedirect("member.jsp");
		super.doGet(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost 실행 됨");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		resp.sendRedirect("member.jsp");
		
		if(req.id==resp.id && req.name==resp.name) {
			if(req.pwd==resp.pwd) {
				PrintWriter out = resp.getWriter();
				out.print("<html><head><title>Test</title></head>");
				out.print("<body><h1>업데이트 성공!</h1>" + "id" + "name" +"</body>");
				out.print("</html>");
				out.close();
			}else {
				PrintWriter out = resp.getWriter();
				out.print("<html><head><title>Test</title></head>");
				out.print("<body><h1>비밀번호가 다릅니다.</h1></body>");
				out.print("</html>");
				out.close();
			}
		}
	}
}
