package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import ru.itis.dto.RollForm;
import ru.itis.dto.UserDto;
import ru.itis.dto.UserForm;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/roll")
public class RollServlet extends HttpServlet {

    private RollService rollService;
    private RollHistService historyService;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Validator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.rollService = (RollService) context.getAttribute("rollService");
        this.historyService = (RollHistService) context.getAttribute("historyService");
        this.validator = (Validator) context.getAttribute("validator");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errors", new ArrayList<Object>());
        req.getRequestDispatcher("/WEB-INF/html/roll.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String countString = data.get("count").getAsString();
        Integer count = (!countString.isEmpty()) ?
                Integer.parseInt(countString) : null;
        String diceString = data.get("dice").getAsString();
        Integer dice = (!diceString.isEmpty()) ?
                Integer.parseInt(diceString) : null;
        RollForm rollForm = RollForm.builder()
                .count(count)
                .dice(dice)
                .build();

        Set<ConstraintViolation<RollForm>> constraintViolations = validator.validate(rollForm);

        resp.setContentType("application/json");

        if (!constraintViolations.isEmpty()) {
            List<String> violations = constraintViolations.stream().map(x -> x.getMessage())
                    .collect(Collectors.toList());
            String violationsAsJson = objectMapper.writeValueAsString(violations);

            resp.setStatus(400);
            resp.getWriter().write(violationsAsJson);
        } else {
            List<Roll> rolls = rollService.getRollResult(rollForm);
            UserDto user = (UserDto) req.getSession().getAttribute("user");
            historyService.updateRollHistory(user.getId(), rolls);

            String rollsAsJson = objectMapper.writeValueAsString(rolls);
            resp.getWriter().write(rollsAsJson);
        }

    }
}

