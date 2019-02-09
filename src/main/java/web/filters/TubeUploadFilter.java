package web.filters;

import domain.models.binding.TubeUploadBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tube/upload")
public class TubeUploadFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().toUpperCase().equals("POST")){
            TubeUploadBindingModel bindingModel = new TubeUploadBindingModel();
            bindingModel.setTitle(req.getParameter("title"));
            bindingModel.setAuthor(req.getParameter("author"));
            bindingModel.setYouTubeLink(req.getParameter("youtube-link"));
            bindingModel.setDescription(req.getParameter("description"));
            bindingModel.setUploader((String) req.getSession().getAttribute("username"));

            req.setAttribute("modelBinding", bindingModel);
        }

        filterChain.doFilter(req, resp);
    }
}
