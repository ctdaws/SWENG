public class Transitions {

<<<<<<< HEAD
	boolean userTrigger = false;
	int startTime;
	int duration;

	public Transitions(boolean userTrigger, int startTime, int duration) {
		this.userTrigger = userTrigger;
=======
	boolean userTrigger;
	int startTime;
	int duration;

	public Transitions(String userTrigger, int startTime, int duration) {
		this.setStart(userTrigger);
>>>>>>> master
		this.startTime = startTime;
		this.duration = duration;
	}

<<<<<<< HEAD
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
=======
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
>>>>>>> master

}
