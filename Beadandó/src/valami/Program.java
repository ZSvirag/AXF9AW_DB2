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
		System.out.println("K�rek egy felhaszn�l�nevet �s jelsz�t.");
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
				System.out.println("1-es gomb: Dolgoz� m�dos�t�sa");
				System.out.println("2-es gomb: �j gy�rt� hozz�ad�sa");
				System.out.println("3-mas gomb: T�bl�k felt�lt�se");
				System.out.println("4-es gomb: Dolgoz� t�bla t�rl�se");
				System.out.println("5-�s gomb: Minden megjelen�t�s");
				System.out.println("6-os gomb: Megjelen�t�s");
				System.out.println("0-�s gomb: Kil�p�s");
			gomb=sc.nextInt();
			switch (gomb) {
			case 1: {
				
				System.out.println("K�rem a dolgoz� nev�t!");
				String nev =sc.next();
				System.out.println("K�rem a dolgoz� id-t!");
				int id = sc.nextInt();
				alterTable(conn, nev, id);
				
				break;
			}
			
			case 2: {
				System.out.println("K�rem a gy�rt� adatait!");
				System.out.print("Adja meg a gy�rt� id-t: ");
				int id = sc.nextInt();
				System.out.print("Adj meg a gy�rt� nev�t: ");
				String nev = sc.next();
				System.out.print("Adja meg a gy�rt� ad�sz�m�t: ");
				int ado = sc.nextInt();
				System.out.print("Adja meg a gy�rt� alap�t�si �v�t: ");
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
		
		/*Dolgoz�k t�bla felt�lt�se*/
  String str ;/*Ide egy egyenl�s�g jel kell*/ /*"INSERT INTO dolgozok VALUES (4,'Hajd� Hajnalka', '1999-01-29 ',3100,'Salg�tarj�n','Arany J�nos �t',89)";
		 		 "INSERT INTO dolgozok VALUES (1,'Kiss D�vid Mikl�s', '1988-10-31 ',3100,'Salg�tarj�n','R�k�czi �t',3)\r\n";
				 "INSERT INTO dolgozok VALUES (2,'Kov�cs Andr�s','1991-05-08 ',3100,'Salg�tarj�n','Bem �t',19)\r\n";
			     "INSERT INTO dolgozok VALUES (3,'Nagy Benedek','1985-03-22 ',3101,'Salg�tarj�n','Ady Endre utca',22)";
			     "INSERT INTO dolgozok VALUES (5,'Balogh-T�th Gerg� ', '1982-06-08 ',3101,'Salg�tarj�n','Pet�fi utca',35)";*/
				
		/*Elad�sok t�bla felt�lt�se*/
				/*"INSERT INTO eladasok  VALUES (1,10000,1002,1,17,3)";
				  "INSERT INTO eladasok  VALUES (2,10000,1007,1,17,3)";
				  "INSERT INTO eladasok  VALUES (3,10000,1039,1,17,3)";
			      "INSERT INTO eladasok  VALUES (4,10001,1017,1,8,7)";
			      "INSERT INTO eladasok  VALUES (5,10002,1112,1,45,5)";*/
				
		/*Vev�k t�bla felt�lt�se*/
			  /*"INSERT INTO vevok VALUES (1,'Kiss Istv�n',83254169,3100,'Salg�tarj�n','Mad�ch �t',2)";
				"INSERT INTO vevok VALUES (2,'Nagy J�zsef',84631497,3100,'Salg�tarj�n','Munk�csi �t',25)";
		        "INSERT INTO vevok VALUES(3,'Tak�cs Ferenc',89654787,3060,'P�szt�','T�ncsics utca',22)";
		        "INSERT INTO vevok VALUES(4,'Kov�cs L�szl�',85321045,3070,'B�tonyterenye','Honv�d �t',54)";
		        "INSERT INTO vevok VALUES (5,'Szerencsi Andr�s',89654789,3132,'Cered','Domb utca',66)";*/
		
		/*Term�kek t�bla felt�lt�se*/
		   	/*"INSERT INTO termekek  VALUES (1000 , 'Akkumul�tor 95Ah',145241, '2020-10-01',72923,'Eur�pai Uni�',24,'Professional',10,1)";
		      "INSERT INTO termekek  VALUES (1001,'Akkumul�tor 80Ah',145242, '2020-10-01 ',64817,'Eur�pai Uni�',24,'Professional',10,1)";
              "INSERT INTO termekek  VALUES (1002,'Akkumul�tor 80Ah',214211, '2020-12-30 ',52316,'N�metorsz�g',24,'Premium',10,2)";
              "INSERT INTO termekek  VALUES (1003, 'Kapcsol�',324512, '2020-01-01 ',58463,'N�metorsz�g',6,'Premium',10,3)";
		      "INSERT INTO termekek  VALUES (1004, 'F�nysz�r� �ll�t� motor',241452, '2020-04-01 ',34499,'N�metorsz�g',12,'Professional',11,4)";*/
		 
		/*Gy�rt�k t�bla felt�lt�se*/
		     /*"INSERT INTO gyartok VALUES (1,'Exide',45611421,2001)";
		       "INSERT INTO gyartok VALUES (2,'Bosch',65878965,1987)";
		       "INSERT INTO gyartok VALUES (3,'Hella',32457896,1963)";
		       "INSERT INTO gyartok VALUES (4,'Topran-HansPries',14598745,1974)";
		       "INSERT INTO gyartok VALUES (5,'TYC',65987854,2010)";*/
				
		/*Kateg�ri�k t�bla felt�lt�se*/
		    /*"INSERT INTO kategoriak VALUES (10,'Akkumul�tor')";
		      "INSERT INTO kategoriak VALUES (11,'Elektromos alkatr�sz')";
		      "INSERT INTO kategoriak VALUES (12,'Differenci�lm� �s alkatr�szei')";
		      "INSERT INTO kategoriak VALUES (13,'F�ltengely alkatr�szek')";
		      "INSERT INTO kategoriak VALUES (14,'G�zped�l')";*/
				
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
