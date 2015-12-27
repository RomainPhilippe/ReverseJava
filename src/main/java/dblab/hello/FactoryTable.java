package dblab.hello;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FactoryTable {

	ArrayList<String> listTableName;
	
	public FactoryTable(){
	}
	
	public FactoryTable(ArrayList<String> listTableName){
		this.listTableName=null;
	}
	/**
	 *
	 * @return Arraylist with the table's name
	 * @throws SQLException
	 */
	public ArrayList<String> getTablesMetadata(DatabaseMetaData metadata) throws SQLException {
		String table[] = { "TABLE" };
		ResultSet rs = null;
		ArrayList<String> tables = null;
		// receive the Type of the object in a String array.
		rs = metadata.getTables(null, null, null, table);
		tables = new ArrayList<String>();
		while (rs.next()) {
			tables.add(rs.getString("TABLE_NAME"));
		}
		return tables;
	}
	
	public ArrayList<Table> getColumnsMetadata(DatabaseMetaData metadata) throws SQLException {
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		ResultSet rs5 = null;
		ArrayList<Table> listTable=new ArrayList<Table>();
		
		// Print the columns properties of the actual table
		for (String actualTable : listTableName) {
			
			

			rs = metadata.getColumns(null, null, actualTable, null);
			System.out.println(actualTable.toUpperCase());
			
			ArrayList<Column> listColumn=new ArrayList<Column>();
			while (rs.next()) {
				int nullable = rs.getInt("NULLABLE");
				Boolean bNullable;
			      if (nullable == DatabaseMetaData.columnNullable) {
			    	  bNullable=true;
			      } else {
			    	  bNullable=false;
			      }
			      String defaultValue = rs.getString("COLUMN_DEF");
				Column col =new Column(rs.getString("COLUMN_NAME"),rs.getString("TYPE_NAME"),rs.getString("COLUMN_SIZE"),bNullable,defaultValue);
				
				listColumn.add(col);
				//System.out.println(rs.getString("COLUMN_NAME") + " "+ rs.getString("TYPE_NAME") + " "+ rs.getString("COLUMN_SIZE")+" "+defaultValue);
			}
			//System.out.println("\n");
			
			
			/////////////////////////////////////////////////////////:
			//primary key
			rs2 = metadata.getPrimaryKeys(null, null, actualTable);
			ArrayList<String> listPrimaryKey=new ArrayList<String>();
			while (rs2.next()) {
				String pkey = rs2.getString("COLUMN_NAME");
				listPrimaryKey.add(pkey);
				//System.out.println("primary key = " + pkey);
			}
			//System.out.println("\n");
			
			
			/////////////////////////////////////////////////////////:
			//constraint unique
			rs3 = metadata.getIndexInfo(null, null, actualTable,true,true);
			ArrayList<String> listConstraintUnique=new ArrayList<String>();
			while (rs3.next()) {
				String indexName = rs3.getString("INDEX_NAME");
                String columnName = rs3.getString("COLUMN_NAME");
                if(indexName.equals("PRIMARY")) {
                    continue;
                }
                //System.out.println("****************************************");
                //System.out.println("indexName : " + indexName);
                //System.out.println("Column Name: " + columnName);
                listConstraintUnique.add(columnName);
			}
			
			
			////////////////////////////////////////////////////////////////
			///list key
//			rs5 = metadata.getExportedKeys(null, null, actualTable);
//		     while (rs5.next()) {
//		       String fkTableName = rs5.getString("FKTABLE_NAME");
//		       String fkColumnName = rs5.getString("FKCOLUMN_NAME");
//		       int fkSequence = rs5.getInt("KEY_SEQ");
//		       //System.out.println("getExportedKeys(): fkTableName="+fkTableName);
//		       //System.out.println("getExportedKeys(): fkColumnName="+fkColumnName);
//		       //System.out.println("getExportedKeys(): fkSequence="+fkSequence);
//		     }
//			
			
			//list foreign key
			rs4 = metadata.getImportedKeys(null, null, actualTable);
			ArrayList<ForeignKey> listForeignKeyConstraint=new ArrayList<ForeignKey>();
		     while (rs4.next()) {
		       String fkColumnName = rs4.getString("FKCOLUMN_NAME");
		       String fkTableName= rs4.getString(3);
		       String columnNameOtherTable=rs4.getString(4);
		       String currentTable=rs4.getString(7);
		       //System.out.println("CONSTRAINT FOREIGN KEY : constraint name =fk_"+currentTable+"_"+fkTableName+" // colonne :"+fkColumnName+" //  fkTableName="+fkTableName+ ",  columnNameOtherTable="+columnNameOtherTable);
		       ForeignKey foreignKey=new ForeignKey("fk_"+currentTable+"_"+columnNameOtherTable,fkColumnName,fkTableName,columnNameOtherTable);
			     
		       listForeignKeyConstraint.add(foreignKey);
		     }
		     
		     //list key === list index
		     rs5 = metadata.getIndexInfo(null, null, actualTable, false, false);
		     ArrayList<Key> listKey=new ArrayList<Key>();
		        while (rs5.next()){
		            System.out.println(rs5.getString("INDEX_NAME") + " " +
		                    rs5.getString("COLUMN_NAME")+ " " +
		                    rs5.getString("ASC_OR_DESC"));
		            if(!rs5.getString("INDEX_NAME").equals("PRIMARY")){
		            	Key key = new Key(rs5.getString("INDEX_NAME"),rs5.getString("COLUMN_NAME"));
			            listKey.add(key);
		            }
		            
		        }
		     
			Table currentTable=new Table(actualTable,listColumn,listPrimaryKey,listConstraintUnique,listKey,listForeignKeyConstraint);
			listTable.add(currentTable);
			System.out.println("\n");
		}
		//System.out.println("before return");
		return listTable;
	}
	
	public ArrayList<String> getListTableName() {
		return listTableName;
	}
	public void setListTableName(ArrayList<String> listTableName) {
		this.listTableName = listTableName;
	}

	
	
}
