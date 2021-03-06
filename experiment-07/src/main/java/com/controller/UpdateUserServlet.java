package com.controller;

import com.util.DataSourceUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("uid"));
        String newName = req.getParameter("name");
        String sql = "update user set name=? where id=?";
        try(Connection connection = DataSourceUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
