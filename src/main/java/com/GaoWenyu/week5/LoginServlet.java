package com.GaoWenyu.week5;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet{
    Connection connection = null;
    Statement stmt = null;
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sql = "SELECT * FROM userdb.usertable WHERE username = '" + username + "' AND password =" + password;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1,username);
//            preparedStatement.setString(1,password);
            preparedStatement.executeQuery();
            connection.setAutoCommit(false);
            connection.commit();
            Statement statement = null;
            ResultSet resultSet = null;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            PrintWriter writer = response.getWriter();
            if (resultSet.next()){
                writer.println("<title>login success</title>");
                writer.println("Login Success!!!<br/>");
                writer.println("welcome"+username);
            }else {
                writer.println("username or password error!!!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
