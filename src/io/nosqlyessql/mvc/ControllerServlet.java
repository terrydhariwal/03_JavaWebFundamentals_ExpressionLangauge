package io.nosqlyessql.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", urlPatterns = {"*.do"})
public class ControllerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel user = new UserModel();
        user.setName("Terry");
        user.setEmail("terry@nosqlyessql.io");

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view.jsp");
        //We need to pass the bean to the view (/index.jsp)
        //1. We can pass the bean through global scope - all requests (jsp or servlet) can see it
        getServletContext().setAttribute("global_user", user);
        //2. Or pass via session scope - all requests for this user (session) can see it
        request.getSession().setAttribute("session_user", user);
        //3. Or pass via request scope - only this request (this servlet and dispatched request can see it (bean is destroyed once the request completes
        request.setAttribute("request_user", user);

        //Dispatch request to the view
        //1. if we use include, the request can be sent to the jsp, but returns here for further processing (i.e. after user has seen the JSP response)
        // 2. if we use forward the request finishes at the forwarded jsp
        requestDispatcher.forward(request, response);

    }
}
