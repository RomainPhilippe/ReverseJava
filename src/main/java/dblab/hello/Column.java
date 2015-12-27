package dblab.hello;

public class Column {

	String columnName;
	String typeName;
	String columnSize;
	Boolean nullable;
	String defaultValue;
	
	
	public Column(String columnName,String typeName,String columnSize,Boolean nullable,String defaultValue) {
		this.columnName = columnName;
		this.typeName = typeName;
		this.columnSize = columnSize;
		this.nullable=nullable;
		this.defaultValue=defaultValue;
	}


	public String getColumnName() {
		return columnName;
	}


	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getColumnSize() {
		return columnSize;
	}


	public void setColumnSize(String columnSize) {
		this.columnSize = columnSize;
	}


	public Boolean getNullable() {
		return nullable;
	}


	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}


	public String getDefaultValue() {
		return defaultValue;
	}


	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	

	

}
