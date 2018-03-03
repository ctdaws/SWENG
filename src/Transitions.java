public class Transitions {

	boolean userTrigger;
	int startTime;
	int duration;

	public Transitions(String userTrigger, int startTime, int duration) {
		this.setStart(userTrigger);
		this.startTime = startTime;
		this.duration = duration;
	}

	public void setStart(String trigger) {
		if(trigger.equals("trigger")) {
			this.userTrigger = true;
		}
		else {
			this.userTrigger = false;
		}
	}

	public void setStart(int startTime) { this.startTime = startTime; }

	public void setDuration(int duration) { this.duration = duration; }

}
