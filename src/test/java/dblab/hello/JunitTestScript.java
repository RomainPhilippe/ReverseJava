package dblab.hello;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;


/**
 * @author mkyong
 *
 */
public class JunitTestScript {

	public static String getSpecificLine(int numberLine) throws FileNotFoundException{
		File file = new File("sakila.sql");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String lineIWant="";
		try {
			for(int i = 1; i <numberLine; ++i)
				br.readLine();
			
			lineIWant = br.readLine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lineIWant;
	}

	@Test
	public void columnName() throws FileNotFoundException {
		assertEquals("last_name VARCHAR(45) NOT NULL,",getSpecificLine(12));
		System.out.println("@Test - columnName");
	}
	
	@Test
	public void primaryKey() throws FileNotFoundException {
		assertEquals("PRIMARY KEY (address_id),",getSpecificLine(31));
		System.out.println("@Test - primaryKey");
	}
	
	@Test
	public void uniqueKey() throws FileNotFoundException {
		assertEquals("UNIQUE KEY (manager_staff_id),",getSpecificLine(268));
		System.out.println("@Test - uniqueKey");
	}
	@Test
	public void key() throws FileNotFoundException {
		assertEquals("KEY idx_fk_customer_id (customer_id),",getSpecificLine(228));
		System.out.println("@Test - Key");
	}
	
	
	@Test
	public void constraints() throws FileNotFoundException {
		assertEquals("CONSTRAINT (fk_staff_address_id) FOREIGN KEY (address_id)  REFERENCES address (address_id) ON DELETE RESTRICT ON UPDATE CASCADE,",getSpecificLine(227));
		System.out.println("@Test - constraints");
	}
	
	
	
	
}