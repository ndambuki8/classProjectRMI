package computeengine;

import client.DatabaseConnector;
import client.VegetableHelper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author ndambuki
 */
public class UpdateVegetablePriceServlet extends HttpServlet{
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        super.doPost(request, response);


////*##############

//Create an object of type ComputeEngine/VegComputeEngine and have it do sth

/////////##########*/
        
       String n = request.getParameter("k1");
       String p = request.getParameter("k2");
        
       
       //Vegetable Name and Price to be set in VegetableHelper class
       VegetableHelper veg=new VegetableHelper();
       veg.setName(n);
       int pr = Integer.parseInt(p);
       veg.setPrice(pr);
//         veg.setPrice(p);
       
       DatabaseConnector db = new DatabaseConnector();
       db.updateVegetablePrice();
       int i = db.updateVegetablePrice();
       
       if(i>0)
       {
           System.out.println("Veg Register Successful");
           JSONObject jsonObject = new JSONObject();
           
           try{
                jsonObject.put("REGISTER","SUCCESS");
                PrintWriter pw=response.getWriter();
                pw.write(jsonObject.toString());
                pw.print(jsonObject.toString());
                
                System.out.println("REGISTER Successful"+jsonObject.toString());
           } catch (JSONException e)
           {
               e.printStackTrace();
           }
       }
        
    }
            
}
