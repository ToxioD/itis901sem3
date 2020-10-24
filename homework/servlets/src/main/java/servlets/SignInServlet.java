package servlets;

import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {

    private static final String dbUserName = System.getenv("DB_NAME");
    private static final String dbPassword = System.getenv("DB_PASSWORD");
    private static final String dbAddress = "jdbc:postgresql://localhost:5432/postgres";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/html/signin.html").forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(dbAddress,dbUserName,dbPassword);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        String login = request.getParameter("login_input");
        String password = request.getParameter("password_input");

        String sqlSearch = "select count(*) from reg where name='?' and surname='?';";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlSearch);
        preparedStatement.setString(Integer.valueOf(1), login);
        preparedStatement.setString(Integer.valueOf(2), password);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int result = resultSet.getInt(1);

        if(result == 1){
            UUID uuid = UUID.randomUUID();
            Cookie cookie = new Cookie("cookie", uuid.toString());
            response.addCookie(cookie);
            String sqlCookie = "update signin set cookie='?';";
            PreparedStatement preparedStatementCookie = connection.prepareStatement(sqlCookie);
            preparedStatement.setString(Integer.valueOf(1), uuid.toString());
            System.out.println(sqlCookie);
            preparedStatement.execute();
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/reg");
        }


    }
}
