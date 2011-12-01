package de.fhswf.verwaltung;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.fhswf.db.Testdb;

public class Database {

	private static final String DataBaseFileName = "fuhrpark_hsql_db";
	private static final File DataBaseFile = new File(DataBaseFileName+".script");
	private Testdb dataBase;
	private Integer id;
	/**
	 * Initialisiert eine HSQL-Datenbank. Beim erstmaligem Starten der Datenbank wird eine neue 
	 * Struktur angelegt. Andernfalls wird die bestehende Struktur geladen.
	 */
	private void initDataBase() {
		// Abfangen der moeglichen SQL-Exceptions
		boolean DB_exists = DataBaseFile.isFile();
		System.out.println(String.valueOf(DB_exists) + ", Datenbank vorhanden! ");
		try {
			// Starten der Datenbank
			dataBase = new Testdb(DataBaseFileName);
			
			// Prüfen ob DataBaseFile noch nicht angelegt wurde
			if(!DB_exists){
				// Anlegen einer neuen Tabellenstruktur, falls die Datenbank noch nicht besteht.
				update(
						"CREATE TABLE fahrer ( " +
						"id INTEGER IDENTITY, " +
						"name VARCHAR(30), " +
						"fklasse VARCHAR(55), " +
						"fseit DATE" +
						")");
				
				update(
						"CREATE TABLE fahrzeug ( " +
						"id INTEGER IDENTITY, " +
						"kennzeichen VARCHAR(30), " +
						"erstzulassung DATE" +
						")");
				
				update(
						"CREATE TABLE fahrer_fahrzeug ( " +
						"id INTEGER IDENTITY, " +
						"fahrer_id INTEGER, " +
						"fahrzeug_id INTEGER" +
						")");
				
			// Füllen der Tabelle mit Einträgen
				@SuppressWarnings("deprecation")
				Date date1 = new Date(2000, 1, 15);
				System.out.println(date1.toString());
				@SuppressWarnings("deprecation")
				Date date2 = new Date(2001, 3, 15);
				@SuppressWarnings("deprecation")
				Date date3 = new Date(2011, 5, 15);
				@SuppressWarnings("deprecation")
				Date date4 = new Date(2010, 7, 1);
				update("INSERT INTO fahrer(name, fklasse, fseit) VALUES('Schmidt', 'A', '" + date1 + "')");
				update("INSERT INTO fahrer(name, fklasse, fseit) VALUES('Heinzen', 'B', '" + date2 + "')");
				update("INSERT INTO fahrzeug(kennzeichen, erstzulassung) VALUES('HAM-HY 6', '" + date3 + "')");
				update("INSERT INTO fahrzeug(kennzeichen, erstzulassung) VALUES('HAM-EY 5', '" + date4 + "')");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * Sendet einen SQL-Ausdruck ab. Bitte für CREATE, DROP, INSERT und UPDATE benutzen.
	 * 
	 * @param expression
	 * SQL-Ausdruck
	 */
	public void update(String expression){
		if(dataBase == null)
			throw new IllegalAccessError("Bitte erst initDataBase() ausführen");
		
		try {
			this.dataBase.update(expression);
		} catch (SQLException e) {
			System.out.println("Fehler in folgendem Statement:");
			System.out.println(expression);
			e.printStackTrace();
		}
	}
	
	/**
	 * Schließt alle Verbindungen und beendet die Datenbank.
	 */
	public void shutdown(){
		try {
			dataBase.shutdown();
		} catch (SQLException e) {
			System.out.println("Fehler beim Beenden der Datenbank:");
			e.printStackTrace();
		}
	}
	
	/**
	 * Default Constructor.
	 */
	public Database(){
		initDataBase();
	}
	
	@SuppressWarnings("deprecation")
	public void load(MainWindow parent)
	{
		try {
			System.out.println("--- TABELLE 1 ---");
			// Ausgeben aller Personen über ein ResultSet
			ResultSet rs1 = dataBase.query("SELECT * FROM fahrer");
			
			while (rs1.next()) {
				
				Integer id = rs1.getInt("id");
		        String name = rs1.getString("name");
		        String fKlasse = rs1.getString("fklasse");
		        Date fSeit = rs1.getDate("fseit");
		        
		        
		        Fahrer fahrer = new Fahrer(name, fKlasse, fSeit);
		        fahrer.setFahrer_ID(id);
		        fahrer.setEdited(0);
		        parent.addFahrer(fahrer);
		        parent.tableDataFahrer.addRow(fahrer, parent);
		        
		        StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(name);
				stringBuilder.append(", ");
				stringBuilder.append(fKlasse);
				stringBuilder.append(", ");
				stringBuilder.append(fSeit.getDay());
				stringBuilder.append(".");
				stringBuilder.append(fSeit.getMonth()+1);
				stringBuilder.append(".");
				stringBuilder.append(fSeit.getYear());
				System.out.println(stringBuilder.toString());
			}
			
			System.out.println("--- TABELLE 2 ---");
			// Ausgeben aller Personen über ein ResultSet
			ResultSet rs2 = dataBase.query("SELECT * FROM fahrzeug");
			
			while (rs2.next()) {
				
				id = rs2.getInt("id");
		        String kennzeichen = rs2.getString("kennzeichen");
		        Date erstzulassung = rs2.getDate("erstzulassung");
		        
		        Fahrzeug fahrzeug = new Fahrzeug(kennzeichen, erstzulassung);
		        fahrzeug.setFahrzeug_ID(id);
		        fahrzeug.setEdited(0);
		        parent.addFahrzeug(fahrzeug);
		        parent.tableDataFahrzeug.addRow(fahrzeug, parent);
		        
		        StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(kennzeichen);
				stringBuilder.append(", ");
				stringBuilder.append(erstzulassung.getDay());
				stringBuilder.append(".");
				stringBuilder.append(erstzulassung.getMonth()+1);
				stringBuilder.append(".");
				stringBuilder.append(erstzulassung.getYear());
				System.out.println(stringBuilder.toString());
			}
			
			System.out.println("--- TABELLE 3 ---");
			// Ausgeben aller Personen über ein ResultSet
			ResultSet rs3 = dataBase.query("SELECT * FROM fahrer_fahrzeug");
			
			while (rs3.next()) {
				
				id = rs3.getInt("id");
		        Integer fahrerId = rs3.getInt("fahrer_id");
		        Integer fahrzeugId = rs3.getInt("fahrzeug_id");
		        
		        DriverCar driverCar = new DriverCar(fahrerId, fahrzeugId);
		        driverCar.setBez_ID(id);
		        parent.addDriverCar(driverCar);
		        parent.tableDataDriverCar.addRow(driverCar, parent);
		        
		        StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(id);
				stringBuilder.append(", ");
				stringBuilder.append(fahrerId);
				stringBuilder.append(" : ");
				stringBuilder.append(fahrzeugId);
				
				System.out.println(stringBuilder.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void write(MainWindow parent)
	{
	      System.out.println("delete Drivers...");
	      for ( Fahrer fahrer : parent.fahrerMapDel )
	      {
	    	 System.out.println(fahrer.toString());
	    	 if(fahrer.getFahrer_ID() != null)
	        	 update("DELETE FROM fahrer WHERE id = " + fahrer.getFahrer_ID()
	        			 			);
	      }
	      System.out.println(parent.fahrerMapDel.size() + " deleted Drivers...");
	      
				      System.out.println("writing Driver...");
				      for ( Fahrer fahrer : parent.fahrerMap )
				      {
				    	 Date date = new Date(fahrer.getFueSeit().getYear(), fahrer.getFueSeit().getMonth(), fahrer.getFueSeit().getDate());
				         System.out.println(fahrer.toString());
				         if (fahrer.getEdited() == 1)
				        	 update("UPDATE fahrer " +
		        			 			"SET (name, fKlasse, fseit) = ('" +
				        			 fahrer.getName() + "', '" +
		        			 		 fahrer.getFueKlasse() + "', '" +
				        			 date.toString() + "') " + 
		        			 		 "WHERE id = " + fahrer.getFahrer_ID()
				        			 			);
				         
				         if (fahrer.getEdited() == 3)
				        	 update("INSERT INTO fahrer(name, fklasse, fseit) " +
				        			 	"VALUES('" +
				        			 fahrer.getName() + "', '" +
		        			 		 fahrer.getFueKlasse() + "', '" +
				        			 date.toString() + "')"
				        			 			);
				      }
				      System.out.println(parent.fahrzeugMap.size() + " written Driver...");
		
				      
				      System.out.println("delete Cars...");
				      for ( Fahrzeug fahrzeug : parent.fahrzeugMapDel )
				      {
				    	 System.out.println(fahrzeug.toString());
				    	 if(fahrzeug.getFahrzeug_ID() != null)
				        	 update("DELETE FROM fahrzeug WHERE id = " + fahrzeug.getFahrzeug_ID()
				        			 			);
				      }
				      System.out.println(parent.fahrzeugMapDel.size() + " deleted Cars...");
				      
				      System.out.println("writing Cars...");
				      for ( Fahrzeug fahrzeug : parent.fahrzeugMap )
				      {
				    	  Date date = new Date(fahrzeug.getErstzulassung().getYear(), fahrzeug.getErstzulassung().getMonth(), fahrzeug.getErstzulassung().getDate());
					         System.out.println(fahrzeug.toString());
					         if (fahrzeug.getEdited() == 1)
					        	 update("UPDATE fahrzeug " +
			        			 			"SET (kennzeichen, erstzulassung) = ('" +
					        			 fahrzeug.getKennzeichen() + "', '" +
					        			 date.toString() + "') " + 
			        			 		 "WHERE id = " + fahrzeug.getFahrzeug_ID()
					        			 			);
					         
					         if (fahrzeug.getEdited() == 3)
					        	 update("INSERT INTO fahrzeug(kennzeichen, erstzulassung) " +
					        			 	"VALUES('" +
					        			 fahrzeug.getKennzeichen() + "', '" +
					        			 date.toString() + "')"
					        			 			);
				      }
				      System.out.println(parent.fahrzeugMap.size() + " written Cars...");
				      
				      System.out.println("delete Relations...");
				      for ( DriverCar driverCar : parent.driverCarMapDel )
				      {
				    	 System.out.println(driverCar.toString());
				    	 if(driverCar.getBez_ID() != null)
				        	 update("DELETE FROM fahrer_fahrzeug WHERE id = " + driverCar.getBez_ID()
				        			 			);
				      }
				      System.out.println(parent.driverCarMapDel.size() + " deleted Cars...");
				       
  
				      System.out.println("writing Relations...");
				      for ( DriverCar driverCar : parent.driverCarMap )
				      {
				         System.out.println(driverCar.toString());
				         if (driverCar.getNeu())
				        	 update("INSERT INTO fahrer_fahrzeug(fahrer_id, fahrzeug_id) " +
				        			 	"VALUES('" +
				        			 driverCar.getDriverId() + "', '" +
				        			 driverCar.getCarId() + "')"
				        			 			);
				      }
				      System.out.println(parent.driverCarMap.size() + "written Relations...");
	}
	
	@SuppressWarnings("deprecation")
	public void runTestSzenario(){
		try {
			
			System.out.println("--- TESTFALL 1 ---");
			System.out.println("--- TABELLE 1 ---");
			// Ausgeben aller Personen im Terminal
			dataBase.dumpQuery("SELECT * FROM fahrer");
			
			System.out.println("--- TABELLE 2 ---");
			// Ausgeben aller Personen im Terminal
			dataBase.dumpQuery("SELECT * FROM fahrzeug");
			
			System.out.println("--- TABELLE 3 ---");
			// Ausgeben aller Personen im Terminal
			dataBase.dumpQuery("SELECT * FROM fahrer_fahrzeug");
			
			System.out.println("--- TESTFALL 2 ---");
			System.out.println("--- TABELLE 1 ---");
			// Ausgeben aller Personen über ein ResultSet
			ResultSet rs1 = dataBase.query("SELECT * FROM fahrer");
			
			while (rs1.next()) {
		        String name = rs1.getString("name");
		        String fKlasse = rs1.getString("fklasse");
		        Date fSeit = rs1.getDate("fseit");
		        StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(name);
				stringBuilder.append(", ");
				stringBuilder.append(fKlasse);
				stringBuilder.append(", ");
				stringBuilder.append(fSeit.getDay());
				stringBuilder.append(".");
				stringBuilder.append(fSeit.getMonth());
				stringBuilder.append(".");
				stringBuilder.append(fSeit.getYear());
				System.out.println(stringBuilder.toString());
			}
			
			System.out.println("--- TABELLE 2 ---");
			// Ausgeben aller Personen über ein ResultSet
			ResultSet rs2 = dataBase.query("SELECT * FROM fahrzeug");
			
			while (rs2.next()) {
		        String kennzeichen = rs2.getString("kennzeichen");
		        Date erstzulassung = rs2.getDate("erstzulassung");
		        StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(kennzeichen);
				stringBuilder.append(", ");
				stringBuilder.append(erstzulassung.getDay());
				stringBuilder.append(".");
				stringBuilder.append(erstzulassung.getMonth());
				stringBuilder.append(".");
				stringBuilder.append(erstzulassung.getYear());
				System.out.println(stringBuilder.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Database dbh = new Database();
		dbh.runTestSzenario();
		dbh.shutdown();
	}
}