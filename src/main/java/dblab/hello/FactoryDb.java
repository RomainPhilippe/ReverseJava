package dblab.hello;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;


public abstract class FactoryDb {
	protected DatabaseMetaData metadata;	

	public abstract ArrayList<Table> getMetadataDb() throws SQLException;

}
