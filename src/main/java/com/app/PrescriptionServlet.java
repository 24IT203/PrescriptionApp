package com.app;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/PrescriptionServlet")
public class PrescriptionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String id = request.getParameter("id");
        String patient = request.getParameter("patient");
        String doctor = request.getParameter("doctor");
        String medicine = request.getParameter("medicine");
        String dosage = request.getParameter("dosage");
        String days = request.getParameter("days");
        String cost = request.getParameter("cost");

        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect DB
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/medicinedb",
                    "root",
                    "12345678");

            // SQL query
            String query = "INSERT INTO prescription VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, patient);
            ps.setString(3, doctor);
            ps.setString(4, medicine);
            ps.setString(5, dosage);
            ps.setInt(6, Integer.parseInt(days));
            ps.setInt(7, Integer.parseInt(cost));

            int total = Integer.parseInt(days) * Integer.parseInt(cost);
            ps.setInt(8, total);

            // Execute
            ps.executeUpdate();

            // Response
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Data Inserted Successfully!</h2>");
            out.println("<a href='index.html'>Go Back</a>");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}