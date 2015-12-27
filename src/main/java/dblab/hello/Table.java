package dblab.hello;
import java.util.ArrayList;

public class Table {

	String tableName;
	ArrayList<Column> column;
	ArrayList<String> listprimaryKey;
	ArrayList<String> listConstraintUnique;
	ArrayList<Key> listKey;
	ArrayList<ForeignKey> listConstraintForeignKey;
	
	
	public Table(String tableName, ArrayList<Column> column,ArrayList<String> listprimaryKey,ArrayList<String> listConstraintUnique,ArrayList<Key> listKey,ArrayList<ForeignKey> listConstraintForeignKey) {
		this.tableName = tableName;
		this.column = column;
		this.listprimaryKey = listprimaryKey;
		this.listConstraintUnique=listConstraintUnique;
		this.listKey=listKey;
		this. listConstraintForeignKey= listConstraintForeignKey;
	}
	
	public Table(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public ArrayList<Column> getColumn() {
		return column;
	}
	public void setColumn(ArrayList<Column> column) {
		this.column = column;
	}

	public ArrayList<String> getListprimaryKey() {
		return listprimaryKey;
	}

	public void setListprimaryKey(ArrayList<String> listprimaryKey) {
		this.listprimaryKey = listprimaryKey;
	}

	public ArrayList<String> getListConstraintUnique() {
		return listConstraintUnique;
	}

	public void setListConstraintUnique(ArrayList<String> listConstraintUnique) {
		this.listConstraintUnique = listConstraintUnique;
	}

	public ArrayList<ForeignKey> getListConstraintForeignKey() {
		return listConstraintForeignKey;
	}

	public void setListConstraintForeignKey(ArrayList<ForeignKey> listConstraintForeignKey) {
		this.listConstraintForeignKey = listConstraintForeignKey;
	}

	public ArrayList<Key> getListKey() {
		return listKey;
	}

	public void setListKey(ArrayList<Key> listKey) {
		this.listKey = listKey;
	}
	
	
	
}
