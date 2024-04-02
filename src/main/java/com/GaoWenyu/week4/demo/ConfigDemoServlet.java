package com.GaoWenyu.week4.demo;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
@WebServlet
        (urlPatterns = {"/config"},
        initParams = {
                @WebInitParam(name = "name1", value = "Gao Wenyu"),
                @WebInitParam(name = "studentid1" , value = "2022211001000505")
        },loadOnStartup = 1
        )
public class ConfigDemoServlet extends HttpServlet {
    Connection connection = null;
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        String name = config.getServletContext().getInitParameter("name");
        String id = config.getServletContext().getInitParameter("studentid");

        response.setContentType("text/html");
        response.getWriter().println("name:" + name + "<br>id:" + id);


        String name1 = config.getInitParameter("name1");
        String id1 = config.getInitParameter("studentid1");
        response.getWriter().println("<br>name1:" + name1 + "<br>studentid1:" + id1);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
