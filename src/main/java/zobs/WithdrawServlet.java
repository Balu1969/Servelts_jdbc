package zobs;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
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

        // Retrieve amount to withdraw from the form
        String withdrawAmountStr = request.getParameter("withdrawAmount");

        if (cardNumber == null || withdrawAmountStr == null || withdrawAmountStr.isEmpty()) {
            // Error: Invalid session or withdraw amount
            request.setAttribute("message", "Error: Invalid session or withdraw amount.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        double withdrawAmount = Double.parseDouble(withdrawAmountStr);

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish the database connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Perform withdrawal operation
            String updateQuery = "UPDATE accounts SET balance = balance - ? WHERE card_number = ? AND balance >= ?";
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setDouble(1, withdrawAmount);
            pstmt.setString(2, cardNumber);
            pstmt.setDouble(3, withdrawAmount); // Ensure sufficient balance
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // Successful withdrawal
                request.setAttribute("message", "Withdrawal successful! Amount: $" + withdrawAmount);
                request.getRequestDispatcher("withdrawResult.jsp").forward(request, response);
            } else {
                // Insufficient balance or withdrawal failed
                request.setAttribute("message", "Withdrawal failed. Insufficient balance or other error occurred.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: Failed to perform withdrawal operation. " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
}
