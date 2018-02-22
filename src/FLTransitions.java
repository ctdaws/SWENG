public class FLTransitions {

	boolean userTrigger = false;
	int startTime;
	int duration;
	
	public void setStart(String trigger) {
		if(trigger == "trigger") {
			this.userTrigger = true;
		}
	}
	
	public void setStart(int startTime) {
		this.startTime = startTime;
	}
	
}
