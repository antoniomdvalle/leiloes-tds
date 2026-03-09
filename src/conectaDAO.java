
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    public Connection connectDB(){
        
        
        try {
            String url = "jdbc:mysql://localhost/Illex_main?useSSL=false&allowPublicKeyRetrieval=true";
            String user = "root";
            String password = "#cart3ir4el0k4..,";
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection suceeded");
            return conn;
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
            return null;
        }
        
    }
    
}
