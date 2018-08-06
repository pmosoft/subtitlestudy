

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	
    public static void main(String[] args) {
    	
    	TestJdbcBasicOracle oracleBasicJdbc = new TestJdbcBasicOracle();
    	oracleBasicJdbc.test01();
    }
}

class TestJdbcBasicOracle {

    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;

    TestJdbcBasicOracle() { DBConn(); }

    public void test01(){

        try {
            stmt = conn.createStatement();
            System.out.println( "start");
            String query = "SELECT * FROM STTL.TSSCM00020";        	

    		String qry = "\n ";
    		//qry += "\n DELIMETER; ";
    	    //qry += "\n BEGIN; ";
    	    qry += "\n INSERT INTO STTL.TSSCM00020 VALUES ('lifedomy@gmail.com','Silicon.smi','2','1602830','1606670','- Thank you.',now(),NULL,now(),NULL)";
    	    qry += "\n , ('lifedomy@gmail.com','Silicon.smi','2','1602830','1606670','- Thank you.',now(),NULL,now(),NULL)";
    	    qry += "\n , ('lifedomy@gmail.com','Silicon.smi','2','1602830','1606670','- Thank you.',now(),NULL,now(),NULL)";
    	    //qry += "\n INSERT INTO STTL.TSSCM00020 VALUES ('lifedomy@gmail.com','Silicon.Valley.S01E02.720p.HDTV.x264-2HD.smi','2','1602830','1606670','- Thank you.',now(),NULL,now(),NULL);";
    	    //qry += "\n END;";
    	    stmt.execute(qry);
            
    		            
//            rs = stmt.executeQuery(query);
//            while (rs.next()) { 
//                String usrId = rs.getString(1);
//
//                System.out.println( "usrId="+usrId ); 
//            }
        } catch ( Exception e ) { e.printStackTrace(); } finally { DBClose(); }
    	
    }
    
    void DBConn(){

    	
        String DB_URL = "jdbc:mariadb://pmosoft.net:3306/sttl";
        String DB_USER = "sttl";
        String DB_PASSWORD = "s1234";
       
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch ( Exception e ) { e.printStackTrace(); } finally {}
    }
    
    void DBClose(){ rs = null; stmt = null; conn = null; }
}

