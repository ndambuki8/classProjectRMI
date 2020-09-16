package tests;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
//import com.oreilly.servlet.RemoteHttpServlet;
// New addition
public class DaytimeServlet extends RemoteHttpServlet // New addition
implements DaytimeServer { // New addition
// The single method from DaytimeServer
// Note: the throws clause isn't necessary here
public Date getDate() {
return new Date();
}
public void init(ServletConfig config) throws ServletException {
super.init(config);
// Additional code could go here
}
public void doGet(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {
// If the client says "format=object" then
// send the Date as a serialized object
if ("object".equals(req.getParameter("format"))) {
ObjectOutputStream out = new ObjectOutputStream(res.getOutputStream());
out.writeObject(getDate());
}
// Otherwise send the Date as a normal ASCII string
else {
PrintWriter out = res.getWriter();
out.println(getDate().toString());
}
}
public void doPost(HttpServletRequest req, HttpServletResponse res)
throws ServletException, IOException {
doGet(req, res);
}
public void destroy() {
// If you override destroy() you have to call super.destroy()
super.destroy();
}
}