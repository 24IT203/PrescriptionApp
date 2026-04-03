package com.app;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h2>Prescription Records</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Patient</th><th>Doctor</th><th>Medicine</th><th>Dosage</th><th>Days</th><th>Cost/Day</th><th>Total</th></tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/medicinedb",
                    "root",
                    "12345678");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM prescription");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("patient_name") + "</td>");
                out.println("<td>" + rs.getString("doctor_name") + "</td>");
                out.println("<td>" + rs.getString("medicine") + "</td>");
                out.println("<td>" + rs.getString("dosage") + "</td>");
                out.println("<td>" + rs.getInt("days") + "</td>");
                out.println("<td>" + rs.getInt("cost_per_day") + "</td>");
                out.println("<td>" + rs.getInt("total_cost") + "</td>");
                out.println("</tr>");
            }

            con.close();

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }

        out.println("</table>");
    }
}