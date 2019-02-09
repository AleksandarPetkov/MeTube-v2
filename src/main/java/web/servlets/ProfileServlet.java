package web.servlets;

import domain.models.view.UserProfileViewModel;
import org.modelmapper.ModelMapper;
import service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private final UserService userService;

    private final ModelMapper mapper;

    @Inject
    public ProfileServlet(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserProfileViewModel userViewModel = this.mapper
                .map(this.userService.findUserByUsername(req.getSession().getAttribute("username").toString()), UserProfileViewModel.class);

        req.setAttribute("viewModel", userViewModel);
        req.getRequestDispatcher("jsp/profile.jsp").forward(req, resp);
    }
}
