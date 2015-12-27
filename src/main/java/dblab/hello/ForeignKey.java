package dblab.hello;

public class ForeignKey {

	String foreignKeyName;
	String foreignKeyColumnName;
	String foreignKeyTableName;
	String foreignKeyColumnNameOtherTable;
	
	
	public ForeignKey(String foreignKeyName, String foreignKeyColumnName, String foreignKeyTableName, String foreignKeyColumnNameOtherTable) {
		this.foreignKeyName = foreignKeyName;
		this.foreignKeyColumnName = foreignKeyColumnName;
		this.foreignKeyTableName = foreignKeyTableName;
		this.foreignKeyColumnNameOtherTable=foreignKeyColumnNameOtherTable;
	}
	
	public String getForeignKeyName() {
		return foreignKeyName;
	}
	public void setForeignKeyName(String foreignKeyName) {
		this.foreignKeyName = foreignKeyName;
	}
	public String getForeignKeyColumnName() {
		return foreignKeyColumnName;
	}
	public void setForeignKeyColumnName(String foreignKeyColumnName) {
		this.foreignKeyColumnName = foreignKeyColumnName;
	}
	public String getForeignKeyTableName() {
		return foreignKeyTableName;
	}
	public void setForeignKeyTableName(String foreignKeyTableName) {
		this.foreignKeyTableName = foreignKeyTableName;
	}

	public String getForeignKeyColumnNameOtherTable() {
		return foreignKeyColumnNameOtherTable;
	}

	public void setForeignKeyColumnNameOtherTable(String foreignKeyColumnNameOtherTable) {
		this.foreignKeyColumnNameOtherTable = foreignKeyColumnNameOtherTable;
	}
	
		
}
