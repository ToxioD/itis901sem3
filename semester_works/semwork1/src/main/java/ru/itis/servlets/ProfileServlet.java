package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.models.Roll;
import ru.itis.services.RollHistService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private RollHistService rollHistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.rollHistService = (RollHistService) context.getAttribute("historyService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto udto = (UserDto) req.getSession().getAttribute("user");
        req.setAttribute("firstName", udto.getFirstName());
        req.setAttribute("lastName", udto.getLastName());

        List<Roll> rolls = rollHistService.getRollHistory(udto.getId());
        req.setAttribute("rolls", rolls);

        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }
}
