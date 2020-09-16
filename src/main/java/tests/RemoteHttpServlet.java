package tests;

import java.io.*;
import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public abstract class RemoteHttpServlet extends HttpServlet
implements Remote {
protected Registry registry;
public void init(ServletConfig config) throws ServletException {
super.init(config);
try {
// Export ourself
UnicastRemoteObject.exportObject(this);
// Register ourself
bind();
}
catch (RemoteException e) {
getServletContext().log(e, "Problem binding to RMI registry");
}
}
public void destroy() {
// Unregister ourself
unbind();
}
// Returns the name under which we are to be registered
protected String getRegistryName() {
// First name choice is the "registryName" init parameter
String name = getInitParameter("registryName");
if (name != null) return name;
// Fallback choice is the name of this class
return this.getClass().getName();
}
// Returns the port on which the registry server is listening
// (or should be listening)
protected int getRegistryPort() {
// First port choice is the "registryPort" init parameter
try { return Integer.parseInt(getInitParameter("registryPort")); }
// Fallback choice is the default registry port (1099)
catch (NumberFormatException e) { return Registry.REGISTRY_PORT; }
}
protected void bind() {
    // Try to find the appropriate registry already running
try {
registry = LocateRegistry.getRegistry(getRegistryPort());
registry.list(); // Verify it's alive and well
}
catch (Exception e) {
// Couldn't get a valid registry
registry = null;
}
// If we couldn't find it, we need to create it.
// (Equivalent to running "rmiregistry")
if (registry == null) {
try {
registry = LocateRegistry.createRegistry(getRegistryPort());
}
catch (Exception e) {
log("Could not get or create RMI registry on port " +
getRegistryPort() + ": " + e.getMessage());
return;
}
}
// If we get here, we must have a valid registry.
// Now register this servlet instance with that registry.
// "Rebind" to replace any other objects using our name.
try {
registry.rebind(getRegistryName(), this);
}
catch (Exception e) {
log("Could not bind to RMI registry: " + e.getMessage());
return;
}
}
protected void unbind() {
try {
if (registry != null) registry.unbind(getRegistryName());
}
catch (Exception e) {
getServletContext().log(e, "Problem unbinding from RMI registry");
}
}
}