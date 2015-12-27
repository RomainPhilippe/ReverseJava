package dblab.hello;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import dblab.hello.SimpleFabrique.TypeDb;

/**
 * Hello world!
 *
 */
public class ReverseEngenering
{
	private static Connection connection;
	private static DatabaseMetaData metadata;

	public static void main( String[] args ) throws SQLException, FileNotFoundException, UnsupportedEncodingException
	{
		String url=args[0];
		String username=args[1];
		String password=args[2];
		String nameDb=args[3];
		connection = DBConnection.getConnection(url,username,password);
		System.out.println("connection ok");
		FactoryDb factoryDb = SimpleFabrique.createDataBase(TypeDb.MYSQL,connection.getMetaData());
		ArrayList<Table> listTable =factoryDb.getMetadataDb();
		System.out.println("ecriture du fichier");
		createSQLfile(nameDb,listTable);
		System.out.println("fin ecriture du fichier");

	}


	public static final void createSQLfile(String nameDb,ArrayList<Table> listTable) throws FileNotFoundException, UnsupportedEncodingException{

		// We chose writen the script line by line 
		PrintWriter writer = new PrintWriter(nameDb+".sql", "UTF-8");

		writer.println("DROP SCHEMA IF EXISTS "+nameDb+";");
		writer.println("CREATE SCHEMA "+nameDb+";");
		writer.println("USE "+nameDb+";");
		writer.println();


		for(Table currentTable:listTable){

			writer.println("--");
			writer.println("-- Table structure for table '"+currentTable.getTableName()+"';");
			writer.println("--");
			writer.println();
			writer.println("CREATE TABLE "+currentTable.getTableName()+" (");
			for(Column currentColumn:currentTable.getColumn()){
				String nullable=null;
				if(currentColumn.getNullable()){
					nullable="DEFAULT NULL";
				}else{
					nullable="NOT NULL";
					//System.out.println(currentColumn.getDefaultValue());
					if(currentColumn.getDefaultValue()!=null){
						nullable=nullable+" DEFAULT "+currentColumn.getDefaultValue();
					}
				}
				writer.println(currentColumn.getColumnName()+" "+currentColumn.getTypeName()+"("+currentColumn.getColumnSize()+") "+nullable+",");	
			}

			if(currentTable.getListprimaryKey().size()>0){
				writer.print("PRIMARY KEY (");
				for(String primaryKey:currentTable.getListprimaryKey()){
					writer.print(primaryKey);	
				}
				writer.println("),");
			}


			if(currentTable.getListConstraintUnique().size()>0){
				writer.print("UNIQUE KEY (");
				for(String uniqueKey:currentTable.getListConstraintUnique()){
					writer.print(uniqueKey);	
				}
				writer.println("),");
			}



			for(Key key:currentTable.getListKey()){
				writer.println("KEY "+key.getIndex()+" ("+key.getKeyName()+"),");
			}

			for(ForeignKey uniqueKey:currentTable.getListConstraintForeignKey()){
				writer.println("CONSTRAINT ("+uniqueKey.getForeignKeyName()+") FOREIGN KEY ("+uniqueKey.getForeignKeyColumnName()+")  REFERENCES "+uniqueKey.getForeignKeyTableName()+" ("+uniqueKey.getForeignKeyColumnName()+") ON DELETE RESTRICT ON UPDATE CASCADE,");	
			}
			writer.println("),");

			writer.println();
		}
		writer.close();
	}

	/**
	 * Prints in the console the general metadata.
	 *
	 * @throws SQLException
	 */
	public static void printGeneralMetadata() throws SQLException {
		System.out.println("Database Product Name: "
				+ metadata.getDatabaseProductName());
		System.out.println("Database Product Version: "
				+ metadata.getDatabaseProductVersion());
		System.out.println("Logged User: " + metadata.getUserName());
		System.out.println("JDBC Driver: " + metadata.getDriverName());
		System.out.println("Driver Version: " + metadata.getDriverVersion());
		System.out.println("\n");
	}
}
