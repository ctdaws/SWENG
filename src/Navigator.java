class Navigator {
	
	public boolean Qbutton;
	public boolean Xbutton;
	public boolean Sbutton;
	public String id;
	
	public Navigator() {
		this.setButtonStatus(true, false, true);
	}
	
	public void checkButtonStatus() {
		switch(this.id){
			case "Q":
				setButtonStatus(false, true, false);
				break;
			case "X":
				setButtonStatus(true, false, false);
				break;
			case "A":
				setButtonStatus(true, true, true);
				break;	
			case "S":
				setButtonStatus(false, false, false);
				break;	
			case "F":
				setButtonStatus(false, false, false);
				break;	
			case "E":
				setButtonStatus(false, false, false);
				break;	
			default:
				setButtonStatus(true, false, false);
			break;
		}
	}
	
	public String next() {
		switch(this.id){
			case "Q":
				this.id = "A";
				break;
			case "X":
				this.id = "Q";
				break;
			case "A":
				this.id = "F";
				break;	
			case "S":
				this.id = "F";
				break;	
			case "F":
				this.id = "E";
				break;
			case "E":
				this.id = "E";
				break;	
			default:
				break;
		}
		return this.id;
	}

	public void setButtonStatus(boolean Q, boolean X, boolean S) {
		this.Qbutton = Q;
		this.Xbutton = X;
		this.Sbutton = S;
	}
	
	public void setID(String new_id) { this.id = new_id; }
}	