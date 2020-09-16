package tests;

import java.applet.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
// New addition
// New addition

public class DaytimeApplet extends Applet {
TextField httpText, httpObject, socketText, socketObject, RMIObject;
Button refresh;
public void init() {
// Construct the user interface
setLayout(new BorderLayout());
// On the left create labels for the various communication
// mechanisms
Panel west = new Panel();
west.setLayout(new GridLayout(5, 1));
west.add(new Label("HTTP text: ", Label.RIGHT));
west.add(new Label("HTTP object: ", Label.RIGHT));
west.add(new Label("Socket text: ", Label.RIGHT));
west.add(new Label("Socket object: ", Label.RIGHT));
west.add(new Label("RMI object: ", Label.RIGHT));

add("West", west);
// On the right create text fields to display the retrieved time values
Panel center = new Panel();
center.setLayout(new GridLayout(5, 1));

httpText = new TextField();
httpText.setEditable(false);
center.add(httpText);
httpObject = new TextField();
httpObject.setEditable(false);
center.add(httpObject);
socketText = new TextField();
socketText.setEditable(false);
center.add(socketText);
socketObject = new TextField();
socketObject.setEditable(false);
center.add(socketObject);
RMIObject = new TextField();
RMIObject.setEditable(false);
center.add(RMIObject);
add("Center", center);
// On the bottom create a button to update the times
Panel south = new Panel();
refresh = new Button("Refresh");
south.add(refresh);
add("South", south);
}

public void start() {
refresh();
}
private void refresh() {
RMIObject.setText(getDateUsingRMIObject());
}

private String getRegistryHost() {
return getCodeBase().getHost();
}
private int getRegistryPort() {
try { return Integer.parseInt(getParameter("registryPort")); }
catch (NumberFormatException e) { return Registry.REGISTRY_PORT; }
}
private String getRegistryName() {
String name = getParameter("registryName");
if (name == null) {
name = "DaytimeServlet"; // default
}
return name;
}

private String getDateUsingRMIObject() {
try {
Registry registry =
LocateRegistry.getRegistry(getRegistryHost(), getRegistryPort());
DaytimeServer daytime =
(DaytimeServer)registry.lookup(getRegistryName());
return daytime.getDate().toString();
}
catch (ClassCastException e) {
System.out.println("Retrieved object was not a DaytimeServer: " +
e.getMessage());
}
catch (NotBoundException e) {
System.out.println(getRegistryName() + " not bound: " + e.getMessage());
}
catch (RemoteException e) {
System.out.println("Hit remote exception: " + e.getMessage());
}
catch (Exception e) {
System.out.println("Problem getting DaytimeServer reference: " +
e.getClass().getName() + ": " + e.getMessage());
}
return null;
}


public boolean handleEvent(Event event) {
// When the refresh button is pushed, refresh the display
// Use JDK 1.0 events for maximum portability
switch (event.id) {
case Event.ACTION_EVENT:
if (event.target == refresh) {
refresh();
return true;
}
}
return false;
}
}