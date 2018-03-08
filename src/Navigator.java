class Navigator {
	
	public boolean Qbutton = true;
	public boolean Xbutton = false;
	public boolean Sbutton = false;
	public String id;		
	//private String[] IDs = new String[6];
	
	public Navigator() {
		id = "Q";
		checkButtons();
		System.out.println("id = " + id + "\n" + 
							"Qbutton = " + Qbutton + "\n" +
							"Xbutton = " + Xbutton + "\n" +
							"Sbutton = " + Sbutton);
		next();
		System.out.println("new id = " + id + "\n");
		
		System.out.println("Move test (to X)");
		move("X");
		System.out.println("new id = " + id + "\n");
		
	}
	
	public static void main(String[] args) {
		new Navigator();
	}
	
	public void checkButtons() {
			
		switch(id){
			case "Q":
			Qbutton = false;
			Xbutton = true;
			Sbutton = false;
				break;
			case "X":
			Qbutton = true;
			Xbutton = false;
			Sbutton = false;
				break;
			case "A":
			Qbutton = true;
			Xbutton = true;
			Sbutton = true;
				break;	
			case "S":
			Qbutton = false;
			Xbutton = false;
			Sbutton = false;
				break;	
			case "F":
			Qbutton = false;
			Xbutton = false;
			Sbutton = false;
				break;	
			case "E":
			Qbutton = false;
			Xbutton = false;
			Sbutton = false;
				break;	
			default:
			Qbutton = true;
			Xbutton = false;
			Sbutton = false;
			break;
		}
	}
	
	public void next(){
		switch(id){
			case "Q":
			id = "A";
				break;
			case "X":
			id = "Q";
				break;
			case "A":
			id = "F";
				break;	
			case "S":
			id = "F";
				break;	
			case "F":
			id = "E";
				break;	
			case "E":
			id = "E";
				break;	
			default:
			id = id;
			break;
		}	
	}
	
	public void move(String new_id){
	id = new_id;
	}
	
}	