import java.util.Arrays;

class Navigator {

	private String current_id;
	private int topic, level, number, part;
	private int last_qnumber, saved_level;
	private int n;
	private String type;
	private Boolean final_q = false, final_part = false;
	
	//TODO - remove max values, make basic funcionality first, maybe add folder/id append system
	public Navigator() {
		
		// ID = topic/type/level/number/part
		//String test_id = "10/X/10/20,20/20,20";
		//System.out.println("id:" + test_id);
		
		//String new_id = nextPart(test_id);
		//System.out.println("new id:" + new_id);
		
		//String new_id_1 = nextNumber(new_id);
		//System.out.println("new id 1:" + new_id_1);
		
		//String new_id_2 = changeType(new_id_1, "q");
		//System.out.println("new id 2:" + new_id_2);
		
		//boolean is_it_viable = isTypeViable("1/x/1/2/2","10/s/10/20/20");
		//System.out.println("Viable =" + is_it_viable); 
		
		//String id = "1/q/1/1/1";
		//System.out.println("id = " + id);
		//String new_type = "q";
		//n = calculate_n(0,0);
		//String new_id = navigate(id, new_type, n);
		//System.out.println("new id = " + new_id);
		
		System.out.println("Type change, not viable");
		String new_id = navigate("1/a/1/1/1", "q", 0);
		
		System.out.println("Type change q to x");
		final_q = false;
		final_part = false;
		new_id = navigate("1/q/1/3/1", "x", 0);
		
		System.out.println("Type change x to q");
		final_q = false;
		final_part = false;
		new_id = navigate("1/x/1/1/4", "q", 0);
		
		System.out.println("Type change q to s");
		final_q = false;
		final_part = false;
		new_id = navigate("1/q/1/1/1", "s", 0);
		
		System.out.println("Type change x to s");
		final_q = false;
		final_part = false;
		new_id = navigate("1/x/1/1/1", "s", 0);
		
		System.out.println("Type change s to a");
		final_q = false;
		final_part = false;
		new_id = navigate("1/s/1/1/1", "a", 0);
		
		System.out.println("Level change +2");
		final_q = false;
		final_part = false;
		new_id = navigate("1/q/1/1/1", "q", 2);
		
		System.out.println("Number change");
		final_q = false;
		final_part = true;
		new_id = navigate("1/q/2/1/1", "q", 0);
		
		System.out.println("Part change");
		final_q = false;
		final_part = false;
		new_id = navigate("1/q/3/2/1", "q", 0);
		
		
	}
	
	public static void main(String[] args) {
		new Navigator();
	}	
	
	public String navigate(String id, String new_type, int n) {
		System.out.println("Current id = " + id);
		String new_id = id;
		boolean viable = isTypeViable(id, new_type);
		if (viable == true) {
			new_id = nextPart(new_id, final_part);
			new_id = nextNumber(new_id, final_q);
			new_id = changeLevel(new_id, n);
			new_id = changeType(new_id, new_type);
		}
		else {
			System.out.println("Type change not viable");
		}
		System.out.println("New id = " + new_id + "\n");
		return new_id;
	}	
	
	//ONLY PLACE IN ACTION LISTENER //
	//public String goToFeedback(String id) {
	//	current_id = id;
	//	String new_id = FBK;
	//	return new_id;
	//}
	
	//splits id into parameters
	private void split_id(String id) {
		
		String array[] = id.split("/");
		
		topic = Integer.parseInt(array[0]);
		//System.out.println("Topic:" + topic);
		
		type = array[1];
		//System.out.println("Type:" + type);
		
		level = Integer.parseInt(array[2]);
		//System.out.println("Level:" + level);
		
		number = Integer.parseInt(array[3]);
		//System.out.println("Number:" + number);
		
		part = Integer.parseInt(array[4]);
		//System.out.println("Part:" + part);
	}

	//combines new id CHANGE TO REMOVE MAX TAGS
	private String combine_id() {
		
		String new_id = (topic + "/" + type + "/" + 
						 level + "/" + 
						 number + "/" + 
						 part );
		
		return new_id;
	}	
	
	//increments part value in id
	public String nextPart(String id, Boolean final_part) {
		split_id(id);
		if (final_part == true){
			System.out.println("Final part reached");
			//if (type == "q") {
			//	goToFeedback(id);
			//}	
		}
		else {
			part++;
		}
		
		String new_id = combine_id();
		return new_id;
	}
	
	//increments number value in id
	public String nextNumber(String id, Boolean final_q) {
		split_id(id);
		if (final_part == true) {
			if (final_q == true){
				System.out.println("You have reached the last question on this level.");
				number = 1;
			}
			else {
				number++;
			}
		}	
		
		String new_id = combine_id();
		return new_id;
	}
	
	//changes type in the id
	//TODO - changing types doesn't change the max number/part values
	public String changeType(String id, String new_type) {
		
		split_id(id);
		
		switch(new_type){
			//
			case "q":
				break;
			//
			case "x":
				//number = 0;
				break;
			//
			case "a":
				//saved_level = level;
				level = 1;
				//number = 0;
				break;	
			default: 
			break;
		}
		
		if (!type.equals(new_type)) {
			number = 1;
			part = 1;
		}

		type = new_type;
		String new_id = combine_id();
		return new_id;
	}	
	
	//Debugged but not tested, don't know how to test this file on it's own. //
	//Returns a boolean to check if you can move from current ID to a 'next ID'. The Topic List does not have a type and should always be linked.
	public boolean isTypeViable(String id, String new_type){
		
		boolean viable = true;
		split_id(id);
		String current_type = type;
		
		switch(current_type){
			//You can navigate to all types from a question//
			case "q":
				break;
			//You can navigate to all types but solutions from an example//
			case "x":
				if (new_type.equals("s")) {
					viable = false;
				}
				break;
			//You can navigate to all types but solutions from an solution//
			case "s":
				if (new_type.equals("s")) {
					viable = false;
				}
				break;
			//You can't navigate to any other types from an assesment//
			case "a":
				viable = false;
				break;	
			default: 
			break;
		}
		return viable;
	}
	
	public String changeTopic(String id, int new_topic) {
		split_id(id);
		topic = new_topic;
		last_qnumber = number;
		saved_level = level;
		part = 1;
		level = 1;
		number = 1;
		type = "x";
		String new_id = combine_id();
		return new_id;
	}	
	
	public String changeLevel(String id, int n) {
		split_id(id);
		level += n;
		if (level > 10) {
			level = 10;
		}
		if (n != 0) {
			part = 1;
		}
		String new_id = combine_id();
		return new_id;
	}
	
	public String set_level(String id, int level_selected) {
		split_id(id);
		level = level_selected;
		String new_id = combine_id();
		return new_id;
	}
	
	public int calculate_n(int feedback, int answer) {
		int n = feedback + answer;
		return n;
	}
	
	//Check Questions... increment a number and check ID list until there is no ID returned //
	//Check Topics... "	"
	//Examples = 10;
	//Levels = 10;
	//Check Parts
	//current question
	
	//split navigate into auto-navigate and manual controls
	//feedback slide - 0/f/0/0/0 ?
	//topic menu slide - 0/m/0/0/0 ?
	//save id and q progress
	//call function to check if slide exists
}