public class Transitions {

	boolean userTrigger = false;
	int startTime;
	int duration;

	public Transitions(boolean userTrigger, int startTime, int duration) {
		this.userTrigger = userTrigger;
		this.startTime = startTime;
		this.duration = duration;
	}

	// public void setStart(String trigger) {
	// 	if(trigger == "trigger") {
	// 		this.userTrigger = true;
	// 	}
	// }

	public void setStart(int startTime) {
		this.startTime = startTime;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
