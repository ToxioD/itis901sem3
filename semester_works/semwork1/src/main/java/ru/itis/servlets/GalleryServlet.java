package ru.itis.servlets;

import ru.itis.services.FileEncodeService;
import ru.itis.services.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/gallery")
@MultipartConfig
public class GalleryServlet extends HttpServlet {

    private FileService photoService;
    private FileEncodeService photoEncodeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.photoService = (FileService) context.getAttribute("photoService");
        this.photoEncodeService = (FileEncodeService) context.getAttribute("photoEncodeService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("photosForJsp", photoEncodeService.encodeFiles(photoService.getAllFiles()));
        req.getRequestDispatcher("/WEB-INF/jsp/gallery.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        if (part.getContentType().contains("image")) {
            photoService.upload(part.getInputStream(), part.getSubmittedFileName());
        }
        resp.sendRedirect("/gallery");
    }
}
