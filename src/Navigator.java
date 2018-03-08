class Navigator {
	
	public boolean Qbutton = true;
	public boolean Xbutton = false;
	public boolean Sbutton = false;
	public String id;
	
	public Navigator() {
		
	}
	
	public void checkButtons() {
			
		switch(id){
			case "Q":
				setButtons(false, true, false);
				break;
			case "X":
				setButtons(true, false, false);
				break;
			case "A":
				setButtons(true, true, true);
				break;	
			case "S":
				setButtons(false, false, false);
				break;	
			case "F":
				setButtons(false, false, false);
				break;	
			case "E":
				setButtons(false, false, false);
				break;	
			default:
				setButtons(true, false, false);
			break;
		}
	}
	
	public void next() {
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

	public void setButtons(boolean Q, boolean X, boolean S) {
		Qbutton = Q;
		Xbutton = X;
		Sbutton = S;
	}
	
	public void move(String new_id) {
		id = new_id;
	}
	
}	