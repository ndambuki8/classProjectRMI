package client;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseConnector {
    
    String DRIVER="com.mysql.jdbc.Driver";
    String URL="jdbc:mysql://localhost";
    String USER="root";
    String PASSWORD="speed";
    String sql="INSERT into vegetables.veg (vegetableName, Price) VALUE (?,?)";
    int i;

    public int addVegetablePrice(VegetableHelper veg) {
        
        String n = veg.getName();
        int p= veg.getPrice();

        
        try {
            //Connection to DB and store values we create table veg
            Class.forName(DRIVER);
            Connection con=DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, n);
            ps.setInt(2, p);

            i = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        return i;
        
        
        
    }
    
    public int updateVegetablePrice()
    {
        return i;
    }
    
    public int deleteVegetablePrice()
    {
        return i;
    }
    public int calcVegetableCost()
    {
        return i;
    }
    public int calcCost()
    {
        return i;
    }
    
}