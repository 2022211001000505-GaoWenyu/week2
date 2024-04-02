package com.GaoWenyu.week3.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet
        (name = "RegisterServlet",urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    Connection connection = null;
    Statement stmt = null;

    @Override
    public void init(){
        String driver = getServletContext().getInitParameter("driver");
        String url = getServletContext().getInitParameter("url");
        String username = getServletContext().getInitParameter("username");
        String password = getServletContext().getInitParameter("password");
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
            stmt = connection.createStatement();
            System.out.println(connection);;
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");

        PrintWriter writer = response.getWriter();
//        writer.println("<br>username:" + username);
//        writer.println("<br>password:" + password);
//        writer.println("<br> email:" + email);
//        writer.println("<br> gender:" + gender);
//        writer.println("<br> birthday:" + birthday);

        String sql = "INSERT INTO usertable (username, password, email, gender, birthday) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, birthday);
            preparedStatement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql1 = "SELECT * FROM usertable";

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                if (!resultSet.next()) break;

                writer.println("<table border=\"1\" width=\"60%\" cellpadding=\"2\">");
                writer.println("<tr align=\"center\">\n" +
                        "            <td>id</td>\n" +
                        "            <td>username</td>\n" +
                        "            <td>password</td>\n" +
                        "            <td>email</td>\n" +
                        "            <td>gender</td>\n" +
                        "            <td colspan=\"2\">birthday</td>\n" +
                        "        </tr>");
                writer.println("<tr align=\"center\">\n" +
                        "            <td>" + resultSet.getObject("id") + "</td>\n" +
                        "            <td> " + resultSet.getObject("username") + "</td>\n" +
                        "            <td>" + resultSet.getObject("password") + "</td>\n" +
                        "            <td>" + resultSet.getObject("email") + "</td>\n" +
                        "            <td>" + resultSet.getObject("gender") + " </td>\n" +
                        "            <td colspan=\"2\">" + resultSet.getObject("birthday") + "</td>\n" +
                        "        </tr>");
                writer.println("</table>");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
