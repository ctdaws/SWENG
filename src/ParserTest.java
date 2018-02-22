import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	XMLParser xmlReader;


	public ParserTest(){
			System.out.println("Starting to build XML Parser.");
			xmlReader = new XMLParser("../resources/example.xml");
			System.out.println("Finished building XML Parser.");
		}

	public static void main(String[] args){		
		
		new ParserTest();

	}

	
}