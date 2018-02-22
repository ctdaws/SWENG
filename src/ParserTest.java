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