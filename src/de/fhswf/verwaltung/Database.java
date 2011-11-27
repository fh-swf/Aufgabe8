package de.fhswf.verwaltung;

import java.io.File;

import de.fhswf.db.Testdb;

public class Database {
	
	private static final String DataBaseFileName = "fuhrpark_hsql_db";
	private static final File DataBaseFile = new File(DataBaseFileName+".script");
	private Testdb dataBase;

}
