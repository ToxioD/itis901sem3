package ru.itis.servlets;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import ru.itis.dto.UserForm;
import ru.itis.models.User;
import ru.itis.services.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;
    private Validator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.signUpService = (SignUpService) context.getAttribute("signUpService");
        this.validator = (Validator) context.getAttribute("validator");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("errors", new ArrayList<Object>());
        req.getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserForm form = UserForm.builder()
                .email(req.getParameter("email"))
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .password(req.getParameter("password"))
                .build();

        Set<ConstraintViolation<UserForm>> constraintViolations = validator.validate(form);

        if (!constraintViolations.isEmpty()) {
            req.setAttribute("errors", constraintViolations);
            req.getRequestDispatcher("/WEB-INF/jsp/sign_up.jsp").forward(req, resp);
        } else {
            signUpService.signUp(form);
            resp.sendRedirect("/signIn");
        }
    }
}
