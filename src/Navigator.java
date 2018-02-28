import java.util.Arrays;

class Navigator {

	//public String id;
	private int topic, level, number, max_number, part, max_part;
	private String type, number_str, part_str;
	
	//TODO - remove max values, make basic funcionality first, maybe add folder/id append system
	public Navigator() {
		// ID = topic/type/level/number,max_number/part,max_part
		String test_id = "10/X/10/20,20/20,20";
		System.out.println("id:" + test_id);
		
		String new_id = nextPart(test_id);
		System.out.println("new id:" + new_id);
		
		String new_id_1 = nextNumber(new_id);
		System.out.println("new id 1:" + new_id_1);
		
		String new_id_2 = changeType(new_id_1, "Q");
		System.out.println("new id 2:" + new_id_2);
	}
	
	public static void main(String[] args) {
		new Navigator();
	}	
	
	//splits id into parameters
	private void split_id(String id) {
		
		String array[] = id.split("/");
		topic = Integer.parseInt(array[0]);
		System.out.println("Topic:" + topic);
		
		type = array[1];
		System.out.println("Type:" + type);
		
		level = Integer.parseInt(array[2]);
		System.out.println("Level:" + level);
		
		number_str = array[3];
		String num_array[] = number_str.split(",");
		number = Integer.parseInt(num_array[0]);
		max_number = Integer.parseInt(num_array[1]);
		System.out.println("Number:" + number);
		System.out.println("Max Number:" + max_number);
		
		part_str = array[4];
		String part_array[] = part_str.split(",");
		part = Integer.parseInt(part_array[0]);
		max_part = Integer.parseInt(part_array[1]);
		System.out.println("Part:" + part);
		System.out.println("Max Part:" + max_part);
	}

	//combines new id
	private String combine_id() {
		
		String new_id = (topic + "/" + type + "/" + 
						 level + "/" + 
						 number + "," + max_number + "/" + 
						 part + "," + max_part);
		
		return new_id;
	}	
	
	//increments part value in id
	public String nextPart(String id) {
		split_id(id);
		if (part == max_part){
			System.out.println("max part achieved");
		}
		else {
			part++;
		}
		
		String new_id = combine_id();
		return new_id;
	}
	
	//increments number value in id
	public String nextNumber(String id) {
		split_id(id);
		if (number == max_number){
			System.out.println("max number achieved");
		}
		else {
			number++;
		}
		
		String new_id = combine_id();
		return new_id;
	}
	
	//changes type in the id
	//TODO - changing types doesn't change the max number/part values
	public String changeType(String id, String new_type) {
		split_id(id);
		type = new_type;
		String new_id = combine_id();
		return new_id;
	}	
	
		//Returns a boolean to check if you can move from current ID to a 'next ID'. The Topic List does not have a type and should always be linked.
	public boolean type_navigation_viable(String id, String new_id){
		
		boolean viable = true;
		split_id(id);
		type = current_type;
		split_id(new_id);
		type = next_type;
		
		switch(type){
			case 1: current_type = "q";
				break;
		//you can't go from a soultion to a solution//
			case 2: type = "s";
				if next_type = "s" {
					viable = false;
				}
				break;
		 //you can't go from a example to a assesment//
			case 3: type = "x";
				if next_type = "a" {
					viable = false;
				}
					break;
		 //you can't navigate away from an assesment//
			case 4: type = "a"; {
					viable = false;
				}
			default: break;
		return viable;
	}
	
	
	
	//chooseTopic -change topic value
	//goToExample, goToQuestion, goToAssessment, goToFeedback, goToSolution -manual movement, change type
	//nextSlide -general method to move to next slide based on N
	//nextQuestion/Example? -increment number---done----
	//chooseLevel -change level value based on N
}