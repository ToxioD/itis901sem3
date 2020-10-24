package servlets;

import models.User;
import repositories.UsersRepository;
import repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
    private static final String dbUserName = System.getenv("DB_NAME");
    private static final String dbPassword = System.getenv("DB_PASSWORD");
    private static final String dbAddress = "jdbc:postgresql://localhost:5432/postgres";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/html/reg.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();

        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(dbAddress,dbUserName,dbPassword);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        String name = request.getParameter("name_input");
        String surname = request.getParameter("surname_input");
        String age = request.getParameter("age_input");
        Integer agel = Integer.parseInt(age);


        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(connection);
        User entity = User.builder().name(name).surname(surname).age(agel).build();
        System.out.println(usersRepository.save(entity));

        pw.println("Success");
    }
}
