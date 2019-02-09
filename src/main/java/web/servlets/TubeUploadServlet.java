package web.servlets;

import domain.models.binding.TubeUploadBindingModel;
import domain.models.service.TubeServiceModel;
import domain.models.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import service.TubeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/upload")
public class TubeUploadServlet extends HttpServlet {

    private final TubeService tubeService;

    private final ModelMapper mapper;

    @Inject
    public TubeUploadServlet(TubeService tubeService, ModelMapper mapper) {
        this.tubeService = tubeService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeUploadBindingModel bindingModel = (TubeUploadBindingModel) req.getAttribute("modelBinding");

        TubeServiceModel tubeServiceModel = this.mapper.map(bindingModel, TubeServiceModel.class);
        tubeServiceModel.setYouTubeId(bindingModel.getYouTubeLink().split("=")[1]);

        UserServiceModel userServiceModel = new UserServiceModel();
        userServiceModel.setUsername(bindingModel.getUploader());

        tubeServiceModel.setUploader(userServiceModel);

        this.tubeService.uploadTube(tubeServiceModel);
        resp.sendRedirect("/home");
    }
}
