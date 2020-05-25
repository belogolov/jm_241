package hiber.filter;

import hiber.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        boolean logged = session != null && session.getAttribute("loggedUser") != null;
        String loginURI = req.getContextPath() + "/login";
        if (logged) {
            //String role = (String) session.getAttribute("role");
            User user = (User) session.getAttribute("loggedUser");
            if (user.getRole().equals("admin")) {
                chain.doFilter(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/user");
            }
        } else {
            resp.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }
}