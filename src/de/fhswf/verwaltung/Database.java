package de.fhswf.verwaltung;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

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
				
			// Füllen der Tabelle mit Einträgen
				@SuppressWarnings("deprecation")
				Date date1 = new Date(2000, 1, 15);
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
				
				id = rs1.getInt("id");
		        String name = rs1.getString("name");
		        String fKlasse = rs1.getString("fklasse");
		        Date fSeit = rs1.getDate("fseit");
		        
		        Fahrer fahrer = new Fahrer(name, fKlasse,fSeit);
		        fahrer.setFahrer_ID(id);
		        parent.addFahrer(fahrer);
		        parent.tableDataFahrer.addRow(fahrer, parent);
		        
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
				
				id = rs2.getInt("id");
		        String kennzeichen = rs2.getString("kennzeichen");
		        Date erstzulassung = rs2.getDate("erstzulassung");
		        
		        Fahrzeug fahrzeug = new Fahrzeug(kennzeichen, erstzulassung);
		        fahrzeug.setFahrzeug_ID(id);
		        parent.addFahrzeug(fahrzeug);
		        parent.tableDataFahrzeug.addRow(fahrzeug, parent);
		        
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