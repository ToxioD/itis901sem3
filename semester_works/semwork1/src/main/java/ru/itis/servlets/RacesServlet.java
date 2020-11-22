package ru.itis.servlets;

import ru.itis.models.DndRace;
import ru.itis.services.DndEntityService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/races")
public class RacesServlet extends HttpServlet {

    private DndEntityService<DndRace> raceService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.raceService = (DndEntityService) context.getAttribute("raceEntityService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("races", raceService.getAll());
        req.getRequestDispatcher("/WEB-INF/jsp/races.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}


