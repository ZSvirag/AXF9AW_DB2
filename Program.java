package valami;

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
				
					int gomb;
					do {
						System.out.println("Mit szeretn�l csin�lni?");
						System.out.println("1-es �j benzink�t hozz�ad�sa");
						System.out.println("2-es Tankol�s mennyis�g�nek m�dos�t�sa");
						System.out.println("3-as Tankol�s �zemanyag t�pus�nak m�dos�t�sa");
						System.out.println("4-es Tankol�s �zemanyag �r�nak m�dos�t�sa");
						System.out.println("5-�s Tankol�s d�tum�nak m�dos�t�sa");
						System.out.println("6-os Benzinkutak megjelen�t�se");
						System.out.println("7-es Tankol�sok megjelen�t�se");
						System.out.println("8-as D�tum szerinti keres�s");
						gomb=sc.nextInt();
						switch (gomb) {
						case 1: {
							System.out.print("K�rem az �llom�s azonos�t�j�t: ");
							int azonosito = sc.nextInt();
							System.out.print("K�rem az �llom�s nev�t: ");
							String nev = sc.next();
							System.out.print("K�rem a v�rost amelyben tal�lhat� az �llom�s: ");
							String varos = sc.next();
							System.out.print("K�rem az �llom�s c�m�t: ");
							String cim = sc.next();
							
							addNewPetrolStation(conn, azonosito, nev, varos, cim);
							
							break;
						}
						case 2:{
							System.out.println("K�rem a m�dos�tand� tankol�s azonos�t�j�t");
							int tankolas_azonosito = sc.nextInt();
							System.out.println("K�rem a mennyis�get amire m�dos�tani szeretn� a jelenlegit:");
							int mennyiseg = sc.nextInt();
							refuelingDataModificationQuantity(conn, mennyiseg, tankolas_azonosito); 
							break;
						}
						case 3:{
							System.out.println("K�rem a m�dos�tand� tankol�s azonos�t�j�t");
							int tankolas_azonosito = sc.nextInt();
							System.out.println("K�rem az �zemanyag t�pus�t amire m�dos�tani szeretn� a jelenlegit:");
							String uzemanyag_tipus = sc.next();
							refuelingDataModificationFuel(conn, uzemanyag_tipus, tankolas_azonosito);
						}
						case 4:{
							System.out.println("K�rem a m�dos�tand� tankol�s azonos�t�j�t");
							int tankolas_azonosito = sc.nextInt();
							System.out.println("K�rem az �zemanyag �r�t amire m�dos�tani szeretn� a jelenlegit:");
							int ar = sc.nextInt();
							refuelingDataModificationPrice(conn, tankolas_azonosito, ar);
						}
						case 5:{
							System.out.println("K�rem a m�dos�tand� tankol�s azonos�t�j�t");
							int tankolas_azonosito = sc.nextInt();
							System.out.println("K�rem a d�tumot amire m�dos�tani szeretn� a jelenlegit:");
							boolean ok = true;
							int ev;
							int honap;
							int nap;
							do {
								System.out.println("�v:");
								 ev = sc.nextInt();
								ok=true;
								if(ev<1900) {
									System.out.println("Kicsi!");
									ok=false;
								
								}
								else if(ev>2022) {
									System.out.println("Nagy!");
									ok= false;
								}
								
							} while (!ok);
							do {
								System.out.println("H�nap:");
								 honap = sc.nextInt();
								ok=true;
								if(honap<1) {
									System.out.println("Kicsi!");
									ok=false;
								
								}
								else if(honap>12) {
									System.out.println("Nagy!");
									ok= false;
								}
								
							} while (!ok);
							
							do {
								System.out.println("Nap:");
								 nap = sc.nextInt();
								ok=true;
								if(nap<1) {
									System.out.println("Kicsi!");
									ok=false;
								
								}
								else if(nap>31) {
									System.out.println("Nagy!");
									ok= false;
								}
							} while (!ok);
							String tankolas_datuma = ev+"-"+honap+"-"+nap; 
						    refuelingDataModificationDate(conn, tankolas_azonosito, tankolas_datuma);
						}
						case 6:{
							 Megjelenites(petrolStation(conn));  
						}
						case 7:{
							 Megjelenites(fuelling(conn));  
						}
						case 8:{
							System.out.println("K�rem a d�tumot amit keres!");
							boolean ok = true;
							int ev;
							int honap;
							int nap;
							do {
								System.out.println("�v:");
								 ev = sc.nextInt();
								ok=true;
								if(ev<1900) {
									System.out.println("Kicsi!");
									ok=false;
								
								}
								else if(ev>2022) {
									System.out.println("Nagy!");
									ok= false;
								}
								
							} while (!ok);
							do {
								System.out.println("H�nap:");
								 honap = sc.nextInt();
								ok=true;
								if(honap<1) {
									System.out.println("Kicsi!");
									ok=false;
								
								}
								else if(honap>12) {
									System.out.println("Nagy!");
									ok= false;
								}
								
							} while (!ok);
							
							do {
								System.out.println("Nap:");
								 nap = sc.nextInt();
								ok=true;
								if(nap<1) {
									System.out.println("Kicsi!");
									ok=false;
								
								}
								else if(nap>31) {
									System.out.println("Nagy!");
									ok= false;
								}
							} while (!ok);
							String tankolas_datuma = ev+"-"+honap+"-"+nap; 
							Megjelenites(dateSearch(conn, tankolas_datuma));
						}

					}
					}
					while(gomb==0);
					
					
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
				
			    letrehoz= "CREATE TABLE benzinkutak(\r\n"
			    		+ "			 allomas_azonosito int,\r\n"
			    		+ "			 allomas_nev varchar2(30),\r\n"
			    		+ "			 varos varchar2(30),\r\n"
			    		+ "			 cim varchar2(50),\r\n"
			    		+ "			 PRIMARY KEY (allomas_azonosito)\r\n"
			    		+ "			)";
			    
			    statement.execute(letrehoz);
				
			    letrehoz=  "CREATE TABLE tankolasok(\r\n"
			    		+ "			 tankolas_azonosito INT,\r\n"
			    		+ "			 all_azonosito INT,\r\n"
			    		+ "			 mennyiseg int,\r\n"
			    		+ "			 uzemanyag_tipus varchar2(30),\r\n"
			    		+ "			 teljes_ar int,\r\n"
			    		+ "			 tankolas_datuma varchar2(10),\r\n"
			    		+ "			 PRIMARY KEY (tankolas_azonosito)\r\n"
			    		+ "			)";
			    statement.execute(letrehoz);
		}
		public static void insertTable(Connection conn ) throws SQLException {
				java.sql.Statement stm = conn.createStatement();
				String str; // "INSERT INTO benzinkutak VALUES (1,'Shell', 'Miskolc', 'Arany J�nos �t 89')";
							 //"INSERT INTO benzinkutak VALUES (2,'OMW', 'Miskolc','Pet�fi �t 2')";
							//  "INSERT INTO benzinkutak VALUES (3,'MOL', 'Miskolc','R�mai k�r�t 62')";
				
							//"INSERT INTO tankolasok VALUES (10, 1, 25, 'Benzin',8000, '2022-05-21')";
							// "INSERT INTO tankolasok VALUES (11, 2, 50, 'G�zolaj',20000, '2022-05-22')";
						  //"INSERT INTO tankolasok VALUES (12, 3, 65, 'Benzin', 35000, '2022-04-21')";
				//stm.executeUpdate(str);
	}
		public static void addNewPetrolStation(Connection conn, int allomas_azonosito, String allomas_nev, String varos, String cim) throws SQLException{
			PreparedStatement prstmt = conn.prepareStatement(""
					+ "INSERT INTO 	benzinkutak VALUES(?,?,?,?)");
			prstmt.setInt(1,allomas_azonosito);
			prstmt.setString(2, allomas_nev);
			prstmt.setString(3,varos);
			prstmt.setString(4, cim);
			prstmt.executeUpdate();
			prstmt.close();
		}
		public static void refuelingDataModificationQuantity(Connection conn, int mennyiseg, int tankolas_azonosito) throws SQLException {
			PreparedStatement pst = conn.prepareStatement("UPDATE tankolasok SET mennyiseg = ? WHERE tankolas_azonosito = ?");
			pst.setInt(1, mennyiseg);
			pst.setInt(2, tankolas_azonosito);

			pst.executeUpdate();
		}
		public static void refuelingDataModificationFuel(Connection conn, String uzemanyag_tipus, int tankolas_azonosito) throws SQLException {
			PreparedStatement pst = conn.prepareStatement("UPDATE tankolasok SET uzemanyag_tipus = ? WHERE tankolas_azonosito = ?");
			pst.setString(1, uzemanyag_tipus);
			pst.setInt(2, tankolas_azonosito);
			pst.executeUpdate();
		}
		public static void refuelingDataModificationPrice(Connection conn, int tankolas_azonosito, int teljes_ar) throws SQLException {
			PreparedStatement pst = conn.prepareStatement("UPDATE tankolasok SET teljes_ar = ? WHERE tankolas_azonosito = ?");
			pst.setInt(1, teljes_ar);
			pst.setInt(2, tankolas_azonosito);

			pst.executeUpdate();
		}
		public static void refuelingDataModificationDate(Connection conn, int tankolas_azonosito, String tankolas_datuma) throws SQLException {
			PreparedStatement pst = conn.prepareStatement("UPDATE tankolasok SET tankolas_datuma = ? WHERE tankolas_azonosito = ?");
			pst.setString(1, tankolas_datuma);
			pst.setInt(2, tankolas_azonosito);

			pst.executeUpdate();
		}
		public static ResultSet petrolStation(Connection conn) throws SQLException {
			java.sql.Statement stm = conn.createStatement();
			String valami = "SELECT *\r\n"
					+ "FROM benzinkutak";
	        ResultSet rs = stm.executeQuery(valami);
			return rs;
			
		}
		public static ResultSet fuelling(Connection conn) throws SQLException {
			java.sql.Statement stm = conn.createStatement();
			String valami = "SELECT *\r\n"
					+ "FROM tankolasok";
	        ResultSet rs = stm.executeQuery(valami);
			return rs;
			
		}
		public static ResultSet dateSearch(Connection conn, String tankolas_datuma) throws SQLException {
			java.sql.Statement stm = conn.createStatement();
			String valami = "SELECT * FROM tankolasok INNER JOIN benzinkutak ON (tankolasok.all_azonosito = benzinkutak.allomas_azonosito) WHERE datum=?";
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
