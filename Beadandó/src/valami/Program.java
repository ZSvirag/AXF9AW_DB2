package valami;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;


public class Program {
	private static final String URL = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Kérek egy felhasználónevet és jelszót.");
		String felhnev = sc.next(); 
		String jelszo = sc.next();
		
		try {
			Connection conn = connect(felhnev, jelszo);
			//createTable(conn);
			//insertTable(conn);
			//getAllData(conn);
			//Megjelenites(deleteTable(conn));
			
			
           int gomb;
		
			
			do {
				System.out.println("1-es gomb: Dolgozó módosítása");
				System.out.println("2-es gomb: Új gyártó hozzáadása");
				System.out.println("3-mas gomb: Táblák feltöltése");
				System.out.println("4-es gomb: Dolgozó tábla törlése");
				System.out.println("5-ös gomb: Minden megjelenítés");
				System.out.println("6-os gomb: Megjelenítés");
				System.out.println("0-ás gomb: Kilépés");
			gomb=sc.nextInt();
			switch (gomb) {
			case 1: {
				
				System.out.println("Kérem a dolgozó nevét!");
				String nev =sc.next();
				System.out.println("Kérem a dolgozó id-t!");
				int id = sc.nextInt();
				alterTable(conn, nev, id);
				
				break;
			}
			
			case 2: {
				System.out.println("Kérem a gyártó adatait!");
				System.out.print("Adja meg a gyártó id-t: ");
				int id = sc.nextInt();
				System.out.print("Adj meg a gyártó nevét: ");
				String nev = sc.next();
				System.out.print("Adja meg a gyártó adószámát: ");
				int ado = sc.nextInt();
				System.out.print("Adja meg a gyártó alapítási évét: ");
				int alapi = sc.nextInt();
				
				addGyarto(conn, id, nev, ado, alapi);
		
				break;
			}
			case 3: {
				insertTable(conn);
				break;
			}
			
			case 4: {
				deleteTable(conn);
				break;
			}
			
			case 5: {
				getAllData(conn);
				break;
			}
			
			case 6: {
				Megjelenites(deleteTable(conn));
				break;
			}
			
			}}while(gomb==0);
				
		} catch (Exception e) {
			
		}
		
		
	}
	public static Connection connect(String username, String password) throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(URL, username, password);

		return conn;
	}
	public static void createTable(Connection conn) throws SQLException {

	java.sql.Statement statement = conn.createStatement();
	String letrehoz;
	
   letrehoz=    "CREATE TABLE dolgozok(id INT,	nev varchar2(30),szuldatum varchar2(15), irsz int,varos varchar2(30), utca varchar2(50),hazszam int,PRIMARY KEY (id))";
  

   statement.execute(letrehoz);
  
    letrehoz= "CREATE TABLE eladasok(\r\n"
    		+ "			 id INT,\r\n"
    		+ "			 sorszam int,\r\n"
    		+ "			 termekek_id int,\r\n"
    		+ "			 db int,\r\n"
    		+ "			 vevok_id int,\r\n"
    		+ "			 dolgozok_id int,\r\n"
    		+ "			 PRIMARY KEY (id)\r\n"
    		+ "			)";
    
    statement.execute(letrehoz);
	
	
    letrehoz=  "	CREATE TABLE vevok(\r\n"
    		+ "			id INT,\r\n"
    		+ "			 nev varchar2(30),\r\n"
    		+ "			 adoszam int,\r\n"
    		+ "			 irsz int,\r\n"
    		+ "			 varos varchar2(30),\r\n"
    		+ "			 utca varchar2(50),\r\n"
    		+ "			 hazszam int,\r\n"
    		+ "			 PRIMARY KEY (id)\r\n"
    		+ "			)";
    statement.execute(letrehoz);
    
    letrehoz= "	CREATE TABLE termekek(\r\n"
    		+ "			 id INT,\r\n"
    		+ "			 nev varchar2(30),\r\n"
    		+ "			 gyartasi_sz int,\r\n"
    		+ "			 gyartasi_i varchar2(15),\r\n"
    		+ "			 egysegar int,\r\n"
    		+ "			 szarmazasi_h varchar2(30),\r\n"
    		+ "			 garancia int,\r\n"
    		+ "			 minosegi_o varchar2(30),\r\n"
    		+ "			 kategoriak_id int,\r\n"
    		+ "			 gyartok_id int,\r\n"
    		+ "			 PRIMARY KEY (id)\r\n"
    		+ "			)";
    
    statement.execute(letrehoz);
    letrehoz= "CREATE TABLE gyartok(\r\n"
    		+ "			 id INT,\r\n"
    		+ "			 nev varchar2(30),\r\n"
    		+ "			 adoszam int,\r\n"
    		+ "			 alapitasi_i int,\r\n"
    		+ "			 PRIMARY KEY (id)\r\n"
    		+ "			)";
    
    statement.execute(letrehoz);
    letrehoz= "CREATE TABLE kategoriak(\r\n"
    		+ "			 id int,\r\n"
    		+ "			 tipus varchar2(50),\r\n"
    		+ "			 PRIMARY KEY(id)\r\n"
    		+ "			)";
    
    statement.execute(letrehoz);

	
	}
	
	public static void insertTable(Connection conn ) throws SQLException {
		java.sql.Statement stm = conn.createStatement();
		
		/*Dolgozók tábla feltöltése*/
  String str ;/*Ide egy egyenlõség jel kell*/ /*"INSERT INTO dolgozok VALUES (4,'Hajdú Hajnalka', '1999-01-29 ',3100,'Salgótarján','Arany János út',89)";
		 		 "INSERT INTO dolgozok VALUES (1,'Kiss Dávid Miklós', '1988-10-31 ',3100,'Salgótarján','Rákóczi út',3)\r\n";
				 "INSERT INTO dolgozok VALUES (2,'Kovács András','1991-05-08 ',3100,'Salgótarján','Bem út',19)\r\n";
			     "INSERT INTO dolgozok VALUES (3,'Nagy Benedek','1985-03-22 ',3101,'Salgótarján','Ady Endre utca',22)";
			     "INSERT INTO dolgozok VALUES (5,'Balogh-Tóth Gergõ ', '1982-06-08 ',3101,'Salgótarján','Petõfi utca',35)";*/
				
		/*Eladások tábla feltöltése*/
				/*"INSERT INTO eladasok  VALUES (1,10000,1002,1,17,3)";
				  "INSERT INTO eladasok  VALUES (2,10000,1007,1,17,3)";
				  "INSERT INTO eladasok  VALUES (3,10000,1039,1,17,3)";
			      "INSERT INTO eladasok  VALUES (4,10001,1017,1,8,7)";
			      "INSERT INTO eladasok  VALUES (5,10002,1112,1,45,5)";*/
				
		/*Vevõk tábla feltöltése*/
			  /*"INSERT INTO vevok VALUES (1,'Kiss István',83254169,3100,'Salgótarján','Madách út',2)";
				"INSERT INTO vevok VALUES (2,'Nagy József',84631497,3100,'Salgótarján','Munkácsi út',25)";
		        "INSERT INTO vevok VALUES(3,'Takács Ferenc',89654787,3060,'Pásztó','Táncsics utca',22)";
		        "INSERT INTO vevok VALUES(4,'Kovács László',85321045,3070,'Bátonyterenye','Honvéd út',54)";
		        "INSERT INTO vevok VALUES (5,'Szerencsi András',89654789,3132,'Cered','Domb utca',66)";*/
		
		/*Termékek tábla feltöltése*/
		   	/*"INSERT INTO termekek  VALUES (1000 , 'Akkumulátor 95Ah',145241, '2020-10-01',72923,'Európai Unió',24,'Professional',10,1)";
		      "INSERT INTO termekek  VALUES (1001,'Akkumulátor 80Ah',145242, '2020-10-01 ',64817,'Európai Unió',24,'Professional',10,1)";
              "INSERT INTO termekek  VALUES (1002,'Akkumulátor 80Ah',214211, '2020-12-30 ',52316,'Németország',24,'Premium',10,2)";
              "INSERT INTO termekek  VALUES (1003, 'Kapcsoló',324512, '2020-01-01 ',58463,'Németország',6,'Premium',10,3)";
		      "INSERT INTO termekek  VALUES (1004, 'Fényszóró állító motor',241452, '2020-04-01 ',34499,'Németország',12,'Professional',11,4)";*/
		 
		/*Gyártók tábla feltöltése*/
		     /*"INSERT INTO gyartok VALUES (1,'Exide',45611421,2001)";
		       "INSERT INTO gyartok VALUES (2,'Bosch',65878965,1987)";
		       "INSERT INTO gyartok VALUES (3,'Hella',32457896,1963)";
		       "INSERT INTO gyartok VALUES (4,'Topran-HansPries',14598745,1974)";
		       "INSERT INTO gyartok VALUES (5,'TYC',65987854,2010)";*/
				
		/*Kategóriák tábla feltöltése*/
		    /*"INSERT INTO kategoriak VALUES (10,'Akkumulátor')";
		      "INSERT INTO kategoriak VALUES (11,'Elektromos alkatrész')";
		      "INSERT INTO kategoriak VALUES (12,'Differenciálmû és alkatrészei')";
		      "INSERT INTO kategoriak VALUES (13,'Féltengely alkatrészek')";
		      "INSERT INTO kategoriak VALUES (14,'Gázpedál')";*/
				
		//stm.executeUpdate(str);
	}
	
	public static void alterTable(Connection conn, String nev, int id) throws SQLException {
		PreparedStatement pst = conn.prepareStatement("UPDATE dolgozok SET nev = ? WHERE id=?");
		pst.setString(1, nev);
		pst.setInt(2, id);
		pst.executeUpdate();
	}
	
	public static void addGyarto(Connection conn, int id, String nev, int ado, int alapi) throws SQLException{
		PreparedStatement prstmt = conn.prepareStatement(""
				+ "INSERT INTO 	gyarto VALUES(?,?,?,?)");
		prstmt.setInt(1,id);
		prstmt.setString(2, nev);
		prstmt.setInt(3,ado);
		prstmt.setInt(4, alapi);
		prstmt.executeUpdate();
		prstmt.close();
	}
	
	public static void getAllData(Connection conn) throws SQLException {
		PreparedStatement prstmt = conn.prepareStatement(""
				+ "SELECT * From dolgozok "
				+ "INNER JOIN eladasok ON (dolgozok.id = eladasok.dolgozok_id)");
		ResultSet rs = prstmt.executeQuery();
		
		while (rs.next()) {
			System.out.println(rs.getString("nev")+ "\n"+rs.getString(""));
		}
		rs.close();
		prstmt.close();
	}
	
	public static ResultSet deleteTable(Connection conn) throws SQLException {
		java.sql.Statement stm = conn.createStatement();
		String valami = "SELECT *\r\n"
				+ "FROM dolgozok";
        ResultSet rs = stm.executeQuery(valami);
		return rs;
		
	}
	
	public static void Megjelenites(ResultSet rs) throws SQLException
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }

    }
}
