package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.itis.dto.UserDto;
import ru.itis.models.Roll;
import ru.itis.services.RollHistService;
import ru.itis.services.RollService;
import ru.itis.services.RollServiceImpl;
import ru.itis.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/roll")
public class RollServlet extends HttpServlet {

    private RollService rollService;
    private RollHistService historyService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.rollService = (RollService) context.getAttribute("rollService");
        this.historyService = (RollHistService) context.getAttribute("historyService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/html/roll.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        Integer count = Integer.parseInt(data.get("count").getAsString());
        Integer dice = Integer.parseInt(data.get("dice").getAsString());

        List<Roll> rolls = rollService.getRollResult(count, dice);
        UserDto user = (UserDto)req.getSession().getAttribute("user");
        historyService.updateRollHistory(user.getId(), rolls);

        String rollsAsJson = objectMapper.writeValueAsString(rolls);
        resp.setContentType("application/json");
        resp.getWriter().write(rollsAsJson);

    }
}

