package Filter;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        if (httpServletRequest.getUserPrincipal() != null && httpServletRequest.getSession().getAttribute("user") == null)
        {
        saveUserInSession(httpServletRequest);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
    private void saveUserInSession(HttpServletRequest httpServletRequest)
    {
        UserService userService = new UserService();
        String username = httpServletRequest.getUserPrincipal().getName();
        User resultUser = userService.getUserByUsername(username);
        httpServletRequest.getSession(true).setAttribute("user",resultUser);
    }

}
