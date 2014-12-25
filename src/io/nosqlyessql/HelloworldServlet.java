package io.nosqlyessql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HelloworldServlet",urlPatterns = {"/", "/home"}, initParams = {@WebInitParam(name = "ProductName", value = "Hello World Application")})
public class HelloworldServlet extends HttpServlet {

    String product = "My Application";
    String appLevelParam = "";

    @Override
    public void init() throws ServletException {

        // getInitParameter gets Servlet level parameters (in annotations or web.xml). Servlet level params in web.xml will override annotation values
        product = getInitParameter("ProductName");

        // getServletContext().getInitParameter(...) gets application level parameters (in web.xml nested inside <context-param>)
        appLevelParam = getServletContext().getInitParameter("AppLevelParam");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost  (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String responseType = request.getParameter("response");
        String requestType = request.getMethod();
        if(name != null && !name.equals("")) {
            if(responseType.equals("xml")) {
                response.setContentType("text/xml");
                response.getWriter().write("<root>");
                response.getWriter().printf("<name>Hello %s</name>", name);
                response.getWriter().printf("<app-setting>%s</app-setting>", appLevelParam);
                response.getWriter().printf("<product>%s</product>", product);
                response.getWriter().printf("<requestType>%s</requestType>", requestType);
                response.getWriter().write("</root>");
            }
            else if(responseType.equals("json")) {
                response.setContentType("application/json");
                response.getWriter().printf("{ \"response\" : \"Hello %s\", \"app-setting\":\"%s\", \"product\":\"%s\", \"requestType\":\"%s\"}", name, appLevelParam, product, requestType);

            }
            else {
                response.setContentType("text/html");
                response.getWriter().printf("<div>Hello %s</div>", name);
                response.getWriter().printf("<div>App setting = %s</div>", appLevelParam);
                response.getWriter().printf("<div>Product = %s</div>", product);
                response.getWriter().printf("<div>request = %s</div>", requestType);
            }
        }
        else {
            if(requestType.equals("GET")) {
                //response.sendRedirect("/WEB-INF/index.jsp"); //redirect form back to self if no name value
                getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            }
            else {
                //This error handling example is for example purposes only
                //Obviously in the context of form submission we would have client site validation and therefore this wouldn't be called
                //but this is a good demo of how to setup error handling on the server side
                throw new ServletException("A name should be entered.");
            }
        }
    }
}
