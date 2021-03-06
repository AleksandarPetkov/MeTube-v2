package web.filters;
import domain.models.binding.UserLoginBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login")
public class UserLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().toUpperCase().equals("POST")) {
            UserLoginBindingModel userBindingModel = new UserLoginBindingModel();
            userBindingModel.setUsername(req.getParameter("username"));
            userBindingModel.setPassword(req.getParameter("password"));

            req.setAttribute("userBinding", userBindingModel);
        }
        filterChain.doFilter(req, resp);
    }
}
