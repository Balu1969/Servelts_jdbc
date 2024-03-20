package zobs;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/atm";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "balaji";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve card number from session
        HttpSession session = request.getSession(false);
        String cardNumber = (String) session.getAttribute("cardNumber");

        // Retrieve amount to deposit from the form
        String depositAmountStr = request.getParameter("depositAmount");

        if (cardNumber == null) {
            out.println("<h2>Error: Session expired. Please login again.</h2>");
            return;
        }

        if (depositAmountStr == null || depositAmountStr.isEmpty()) {
            out.println("<h2>Error: Invalid deposit amount.</h2>");
            return;
        }

        double depositAmount = Double.parseDouble(depositAmountStr);

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Perform deposit operation
            String updateQuery = "UPDATE accounts SET balance = balance + ? WHERE card_number = ?";
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setDouble(1, depositAmount);
            pstmt.setString(2, cardNumber);
            int rowsAffected = pstmt.executeUpdate();
/*
            if (rowsAffected > 0) {
                out.println("<h2>Deposit successful!</h2>");
                out.println("<p>Thank you for using our ATM.</p>");
            } else {
                out.println("<h2>Deposit failed. Please try again later.</h2>");
            }*/
            if (rowsAffected > 0) {
                // Successful deposit
                request.setAttribute("message", "Deposit successful! Amount: $" + depositAmount);
                request.getRequestDispatcher("depositResult.jsp").forward(request, response);
            } else {
                // Deposit failed
                request.setAttribute("message", "Deposit failed. Please try again later.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            
            }} catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h2>Error: MySQL JDBC driver not found.</h2>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>Error: Failed to perform deposit operation.</h2>");
            out.println("<p>Error details: " + e.getMessage() + "</p>");
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the GET request to the doPost method to handle it
        doPost(request, response);
    }
}
