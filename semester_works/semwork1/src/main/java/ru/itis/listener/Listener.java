package ru.itis.listener;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.*;
import ru.itis.services.*;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //context
        ServletContext servletContext = sce.getServletContext();

        //datasource
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(System.getenv("DB_URL"));
        dataSource.setUsername(System.getenv("DB_USERNAME"));
        dataSource.setPassword(System.getenv("DB_PASSWORD"));

        //encoders
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        BASE64Encoder fileEncoder = new BASE64Encoder();

        //repositories
        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        RollHistRepository historyRepository = new RollHistRepositoryImpl(dataSource);
        TrinketCrudRepositoryImpl trinketCrudRepository = new TrinketCrudRepositoryImpl(dataSource);
        PhotoCrudRepositoryImpl photoCrudRepository = new PhotoCrudRepositoryImpl(dataSource);

        //services
        SignUpService signUpService = new SignUpServiceImpl(usersRepository, passwordEncoder);
        SignInService signInService = new SignInServiceImpl(usersRepository, passwordEncoder);
        RollService rollService = new RollServiceImpl();
        RollService rollUniqueService = new RollUniqueServiceImpl();
        RollHistService historyService = new RollHistServiceImpl(historyRepository);
        TrinketByRollService trinketService = new TrinketByRollServiceImpl(trinketCrudRepository);
        FileService photoService = new PhotoService(photoCrudRepository);
        FileEncodeService photoEncodeService = new FileBase64EncodeServiceImpl(fileEncoder);

        //adding services to context
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("rollService", rollService);
        servletContext.setAttribute("rollUniqueService", rollUniqueService);
        servletContext.setAttribute("historyService", historyService);
        servletContext.setAttribute("trinketService", trinketService);
        servletContext.setAttribute("photoService", photoService);
        servletContext.setAttribute("photoEncodeService", photoEncodeService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
