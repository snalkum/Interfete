package proiectinterfete;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
public class ComenziBD {
    private Connection conexiune;
    private Statement stmt;
    private DatabaseMetaData dbmd;
    private ResultSet rs;
    private String [] dateTabelBD;
    private ResultSet resultSet;
    private ResultSetMetaData rsmd;
    private ResultSetMetaData rsmd1;
    private String rezultat;
    ComenziBD(Connection conexiune){
        this.conexiune = conexiune;
    }
    
    public void initializareDate(String interogareSql){
        try{
        dbmd=conexiune.getMetaData();
        stmt=conexiune.createStatement();
        rs=stmt.executeQuery(interogareSql);
        String [] types={"TABLE"};
        resultSet=dbmd.getTables(null, null, "%", types);
        rsmd= rs.getMetaData();
        rsmd1= resultSet.getMetaData();
        } catch(Exception e) {}
    }
    
    public String [] retrageNumeTabel(String interogareSql){
        try{
        this.initializareDate(interogareSql);
        int cc=rsmd.getColumnCount();
        dateTabelBD = new String[cc];
        for(int i=1; i<cc; i++){
            this.dateTabelBD[i] = rsmd.getColumnName(i);  
        }
        } catch(Exception e){
            
        }
        return this.dateTabelBD;
      
    }
    public String retragereDateTabel(String interogareSql) {
        try{
        this.rezultat ="<html>";
        this.initializareDate(interogareSql);
        int cc=rsmd.getColumnCount();
        dateTabelBD = new String[cc];
        while(rs.next())
			{
				for(int i=1; i<=cc; i++)
				{
					rezultat += rs.getString(i)+"\t";
				}
				rezultat += "<br />";
			}
        } catch(Exception e){
            
        }
        rezultat += "</html>";
        return rezultat;
    }
   
}
