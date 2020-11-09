package ru.itis.listener;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.RollHistRepository;
import ru.itis.repositories.RollHistRepositoryImpl;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcTemplateImpl;
import ru.itis.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(System.getenv("DB_URL"));
        dataSource.setUsername(System.getenv("DB_USERNAME"));
        dataSource.setPassword(System.getenv("DB_PASSWORD"));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        RollHistRepository historyRepository = new RollHistRepositoryImpl(dataSource);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository, passwordEncoder);
        SignInService signInService = new SignInServiceImpl(usersRepository, passwordEncoder);
        RollService rollService = new RollServiceImpl();
        RollHistService historyService = new RollHistServiceImpl(historyRepository);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("rollService", rollService);
        servletContext.setAttribute("historyService", historyService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
