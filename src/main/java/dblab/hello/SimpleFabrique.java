package dblab.hello;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

public class SimpleFabrique 
{
    // création de la base de données en fonction de son type
    public static FactoryDb createDataBase(TypeDb type, DatabaseMetaData meta)
    {
        FactoryDb db = null;
        switch(type)
        {
            case MYSQL:db = new MySQLDb(meta);break;
            case POSTGRE:db = new PostGreDb(meta);break;
            case ORACLE:db = new OracleDb(meta);break;
        }
        return db;
    }
    
 // Enumération des types de bases de données
    public enum TypeDb 
    {
        MYSQL,
        POSTGRE,
        ORACLE
    }
}
 
