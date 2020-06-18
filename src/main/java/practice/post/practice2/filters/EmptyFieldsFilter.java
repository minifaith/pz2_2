package practice.post.practice2.filters;


import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;

public class EmptyFieldsFilter extends BaseFilter{

    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Boolean emptyFieldFound = false;


        if(login.equals("") || password.equals("")) {
            emptyFieldFound = true;
        }

        if(emptyFieldFound){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/Error.jsp");
            dispatcher.forward(req, resp);
        }
        else filterChain.doFilter(req, resp);


    }



}