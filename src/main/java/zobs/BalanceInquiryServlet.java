package zobs;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/BalanceInquiryServlet")
public class BalanceInquiryServlet extends HttpServlet {
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

        if (cardNumber == null) {
            out.println("<h2>Error: Session expired. Please login again.</h2>");
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Retrieve balance from the database
            String query = "SELECT balance FROM accounts WHERE card_number=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, cardNumber);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");

                // Embed the balance information in an HTML response
                out.println("<!DOCTYPE html>");
                out.println("<html lang='en'>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<title>Balance Inquiry</title>");
                out.println("<link rel=\"stylesheet\" href=\"styles.css\" />");
                out.println("<style>");
                out.println("body {");
                out.println("  background-image:  url(\"https://i.postimg.cc/4Nd0DzNR/Picsart-24-03-14-20-18-16-468.jpg\");");
                out.println("  background-size: cover;");
                out.println("  display: inline;");
                out.println("  justify-content: center;");
                out.println("  align-items: center;");
                out.println("  height: 100vh;");
                out.println("}");
                out.println("h2 {");
                out.println("  color: skyblue;");
                out.println("  width: 180px;");
                out.println("  display: block;");
                out.println("  margin-left:550px;");
                out.println("  margin-top:350px;");
                out.println("}");
                out.println("h1 {");
                out.println("  color: white;");
                out.println("  width: 180px;");
                out.println("  display: block;");
                out.println("  margin-left:550px;");
                out.println("  margin-top:3px;");
                out.println("}");
                out.println("button {");
                out.println(" height:28px;");
                out.println(" height:28px;");
                out.println(" border:none;");
                out.println(" text-decoration: none;");
                out.println("background-color: #4caf50;");
                out.println("  display: block;");
                out.println("  margin-top: 30px;");
                out.println("margin-left:550px;");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div>");
                out.println("<h2>Your current balance is: $" +"<h1>"+ balance+"</h1>" + "</h2>");
                out.println("</div>");
                out.println("<button class=\"btn btn-logout\" onclick=\"location.href='home.html'\">Back</button>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<h2>Error: Unable to retrieve balance.</h2>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<h2>Error: Failed to retrieve balance.</h2>");
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the GET request to the doPost method to handle it
        doPost(request, response);
    }
}
