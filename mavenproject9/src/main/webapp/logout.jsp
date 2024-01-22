<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.Timestamp"%>
<%@ page import="jakarta.servlet.ServletException, jakarta.servlet.annotation.WebServlet, jakarta.servlet.http.HttpServlet, jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse, jakarta.servlet.http.HttpSession"%>

<%
    // Check if the user is logged in
    HttpSession session1 = request.getSession(false);
    if (session1 != null && session1.getAttribute("username") != null) {
        String username = (String) session1.getAttribute("username");
        Date loginTime = (Date) session1.getAttribute("loginTime");
        // Update logout time in the database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/login", "root", "");
         
            String query = "UPDATE user_sessions SET logout_time = ? WHERE username = ? and login_time = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                pst.setString(2, username);
                pst.setTimestamp(3, new Timestamp(loginTime.getTime()));
                pst.executeUpdate();
                
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Invalidate the session
        session1.invalidate();
    }

    // Redirect to the login page
    response.sendRedirect("index.html"); // Change to the appropriate page
%>