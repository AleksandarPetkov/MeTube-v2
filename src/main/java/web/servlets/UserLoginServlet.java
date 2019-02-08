package web.servlets;

import domain.models.binding.UserLoginBindingModel;
import domain.models.service.UserServiceModel;
import service.UserService;
import util.Mapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {

    private final UserService userService;

    private final Mapper mapper;

    @Inject
    public UserLoginServlet(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLoginBindingModel model = (UserLoginBindingModel) req.getAttribute("userBinding");


        if (!this.userService.loginUser(this.mapper.map(model, UserServiceModel.class))) {
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute("username", model.getUsername());

        resp.sendRedirect("/home");
    }
}
