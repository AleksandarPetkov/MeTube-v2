package web.filters;

import domain.models.binding.UserBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/register")
public class UserRegisterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().toUpperCase().equals("POST")){
            UserBindingModel userBindingModel = new UserBindingModel();
            userBindingModel.setUsername(req.getParameter("username"));
            userBindingModel.setPassword(req.getParameter("password"));
            userBindingModel.setConfirmpawword(req.getParameter("confirmPassword"));
            userBindingModel.setEmail(req.getParameter("email"));

            req.setAttribute("userBinding", userBindingModel);
        }
        filterChain.doFilter(req, resp);
    }
}
