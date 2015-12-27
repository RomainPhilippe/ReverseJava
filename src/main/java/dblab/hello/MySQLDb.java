package dblab.hello;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLDb extends FactoryDb{	
	
	// Constructeur MySQL database
    public MySQLDb(DatabaseMetaData metadata){
        this.metadata = metadata;
    }

	public ArrayList<Table> getMetadataDb() throws SQLException{
		//on meet a jour le nom de toutes les tables
		FactoryTable tables= new FactoryTable();
	    tables.setListTableName(tables.getTablesMetadata(metadata));
	    
	    //on cré notre liste de tables pour creer ensuite le fichier
		ArrayList<Table> listTable=tables.getColumnsMetadata(metadata);
		return listTable;
	}	
	
}
