/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author victor.barba
 */
@WebServlet(name = "redireccionar", urlPatterns = {"/redireccionar"})
public class redireccionar extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet redireccionar</title>");
//            String url = "http-equiv=\"refresh\" content=\"0;url=http://localhost:8080/resetuser/"+request.getQueryString()+"\"";
//            out.println("<meta "+url+" /> ");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet redireccionar at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {
//            out.close();
//        }
//        String newURL = response.encodeRedirectURL("http://localhost:8080/resetuser");
//        response.setStatus(307);
//
//        System.out.println("processRequest " + request.getQueryString());
        URL myUrl = new URL("http://localhost:8080/MenuDos");
        URLConnection urlConn = myUrl.openConnection();
        String myCookie = "par1=10";
        urlConn.setRequestProperty("myCookie", myCookie);
        urlConn.connect();
////                    System.out.println(urlConn.getHeaderFields());
//        Cookie cookie = new Cookie("myCookie", "myCookieValue");
//        cookie.setDomain("http://localhost:8080/");


        response.sendRedirect("http://localhost:8080/resetuser");





    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet");
        Cookie cookie2 = new Cookie("myCookie", "myCookieValue");
        response.addCookie(cookie2);

        processRequest(request, response);
//        URL url = new URL("localhost");
//        URLConnection connection = url.openConnection();
//        connection.getContent();

//        Cookie[] cookies = request.getCookies();
//        for (int i = 0; i < cookies.length; i++) {
//            cookie = cookies[i];
//            System.out.println("redireccionar "+cookie.getName());
//        }     

    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost");
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
