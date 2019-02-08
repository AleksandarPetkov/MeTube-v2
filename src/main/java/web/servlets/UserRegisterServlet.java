package web.servlets;

import domain.models.binding.UserBindingModel;
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

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {

    private final UserService userService;

    private final Mapper mapper;

    @Inject
    public UserRegisterServlet(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBindingModel userBindingModel = (UserBindingModel) req.getAttribute("userBinding");

        if (!userBindingModel.getPassword().equals(userBindingModel.getConfirmpawword())){
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
            return;
        }

        UserServiceModel userServiceModel = this.mapper.map(userBindingModel, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);

        resp.sendRedirect("/login");
    }
}
