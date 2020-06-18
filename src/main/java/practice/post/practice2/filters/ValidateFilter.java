package practice.post.practice2.filters;

import practice.post.practice2.User;
import practice.post.practice2.UserCache;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


public class ValidateFilter extends BaseFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        String login = req.getParameter("login");
        UserCache userCache = UserCache.getInstance();
        Collection<User> users = userCache.values();
        boolean customerNotExist = true;
        for (User user : users) {
            String userLogin = user.getLogin();
            if (userLogin.equals(login)) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("views/ErrorRegistered.jsp"); // внутренний редирект на Users.jsp
                dispatcher.forward(req, resp);
                customerNotExist = false;
            }
        }
        if (customerNotExist) {
            filterChain.doFilter(req, resp);
        }
    }
}
