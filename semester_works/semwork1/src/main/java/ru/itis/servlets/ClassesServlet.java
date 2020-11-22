package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.itis.models.DndClass;
import ru.itis.services.DndEntityService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/classes")
public class ClassesServlet extends HttpServlet {

    private DndEntityService<DndClass> classService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.classService = (DndEntityService) context.getAttribute("classEntityService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/html/classes.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String namePrefix = data.get("name").getAsString();
        if (!namePrefix.isEmpty()) {
            String classes = objectMapper.writeValueAsString(classService.getMatching(namePrefix));
            resp.getWriter().write(classes);
        } else {
            String classes = objectMapper.writeValueAsString(classService.getAll());
            resp.getWriter().write(classes);
        }
    }
}

