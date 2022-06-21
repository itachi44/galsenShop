package sn.ept.galsenshop.resources;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ApiDocsServlet", value = "/api_docs")
public class ApiDocsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getContextPath()+"/api_docs/index.html");
        response.setContentType("text/html");
        response.sendRedirect(request.getContextPath()+"/api_docs/index.html");

    }

    public void destroy() {
    }
}
