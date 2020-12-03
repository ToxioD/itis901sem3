package ru.itis.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // преобразуем запросы к нужному виду
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // берем сессию у запроса
        // берем только существующую, если сессии не было - то вернет null
        HttpSession session = request.getSession(false);
        // флаг, аутентифицирован ли пользователь
        Boolean isAuthenticated = false;
        // существует ли сессия вообще?
        Boolean sessionExists = session != null;
        // идет ли запрос на страницу входа или регистрации?
        Boolean isRequestOnOpenPage = request.getRequestURI().equals("/signIn") ||
                request.getRequestURI().equals("/signUp");
        // идет ли запрос на домашнюю страницу
        Boolean isRequestOnHomePage = request.getRequestURI().equals("/home");
        // идет ли запрос на пустую страницу
        Boolean isRequestOnEmptyPage = request.getRequestURI().equals("/");

        // если сессия есть
        if (sessionExists) {
            // проверим, есть ли атрибует user?
            isAuthenticated = session.getAttribute("user") != null;
        }

        // если авторизован и запрашивает не открытую страницу или если не авторизован и запрашивает открытую
        if (isAuthenticated && !isRequestOnOpenPage || !isAuthenticated && isRequestOnOpenPage || isRequestOnHomePage) {
            // отдаем ему то, что он хочет
            filterChain.doFilter(request, response);
        } else if (isAuthenticated && isRequestOnOpenPage) {
            // пользователь аутенцифицирован и запрашивает страницу входа
            // - отдаем ему профиль
            response.sendRedirect("/profile");
        } else if (isRequestOnEmptyPage) {
            // если пользователь вошел в корень сайта, отдаем ему домашнюю страницу
            response.sendRedirect("/home");
        } else {
            // если пользователь не аутенцицицирован и запрашивает другие страницы
            response.sendRedirect("/signIn");
        }

    }

    @Override
    public void destroy() {

    }
}
