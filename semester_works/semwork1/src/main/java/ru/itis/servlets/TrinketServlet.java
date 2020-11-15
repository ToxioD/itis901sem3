package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import java.util.List;

@WebServlet("/trinkets")
public class TrinketServlet extends HttpServlet {

    private RollService rollService;
    private TrinketByRollService trinketService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.rollService = (RollService) context.getAttribute("rollUniqueService");
        this.trinketService = (TrinketByRollService) context.getAttribute("trinketService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/html/trinkets.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        Integer count = Integer.parseInt(data.get("count").getAsString());
        boolean shuffle = data.get("shuffle").getAsBoolean();
        if (count < 1 || count > 100) {
            throw new IllegalArgumentException("Number is not in range [1;100]");
        }

        List<Roll> rolls = rollService.getRollResult(count, 100);
        List<Trinket> trinkets = (shuffle) ? trinketService.getShuffledTrinkets(rolls) : trinketService.getTrinkets(rolls);

        String trinketsAsJson = objectMapper.writeValueAsString(trinkets);
        resp.setContentType("application/json");
        resp.getWriter().write(trinketsAsJson);

    }

}
