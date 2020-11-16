package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import ru.itis.dto.RollForm;
import ru.itis.models.Roll;
import ru.itis.models.Trinket;
import ru.itis.services.RollService;
import ru.itis.services.TrinketByRollService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/trinkets")
public class TrinketServlet extends HttpServlet {

    private RollService rollService;
    private TrinketByRollService trinketService;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Validator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.rollService = (RollService) context.getAttribute("rollUniqueService");
        this.trinketService = (TrinketByRollService) context.getAttribute("trinketService");
        this.validator = (Validator) context.getAttribute("validator");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errors", new ArrayList<Object>());
        req.getRequestDispatcher("/WEB-INF/html/trinkets.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String countString = data.get("count").getAsString();
        Integer count = (!countString.isEmpty()) ?
                Integer.parseInt(countString) : 100;
        RollForm rollForm = RollForm.builder()
                .count(count)
                .dice(100)
                .build();
        boolean shuffle = data.get("shuffle").getAsBoolean();

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
            List<Trinket> trinkets =
                    (shuffle) ? trinketService.getShuffledTrinkets(rolls) : trinketService.getTrinkets(rolls);
            String trinketsAsJson = objectMapper.writeValueAsString(trinkets);

            resp.getWriter().write(trinketsAsJson);
        }

    }

}
