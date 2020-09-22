The following is a guideline on how the distributed system is supposed to work ie the communication between client  and servlets on the server through RMI. 

Both clients and server implement the compute interface and this means that the objects created from the classes can have the same behaviour and can communicate the same information. 

In the client, the each task ie adding a vegetable price, deleting a vegetable price, updating price, calculating cost and calculating vegetable cost has a corresponding servlet file associate with it on the server, where an object will be spun off to take care of the requested service and respond. A database has also been implemented, where the object created in the servlet file to communicate with the object in the client, can access and modify the database table records where the vegetable names and prices are held.


The project is still being improved upon..
