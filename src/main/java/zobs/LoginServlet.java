package zobs;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/atm";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "balaji";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String cardNumber = request.getParameter("cardNumber");
        String pin = request.getParameter("pin");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the database connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // Prepare and execute the query
            String query = "SELECT * FROM accounts WHERE card_number=? AND pin=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, cardNumber);
            pstmt.setString(2, pin);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("cardNumber", cardNumber);
                out.println("<h2>Success.</h2>");
                response.sendRedirect("home.html"); // Redirect to home page after successful login
            } else {
                out.println("<h2>Login failed. Invalid card number or PIN.</h2>");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h2>Error: MySQL JDBC driver not found.</h2>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>Error: Failed to connect to the database.</h2>");
            out.println("<p>Error details: " + e.getMessage() + "</p>");
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}