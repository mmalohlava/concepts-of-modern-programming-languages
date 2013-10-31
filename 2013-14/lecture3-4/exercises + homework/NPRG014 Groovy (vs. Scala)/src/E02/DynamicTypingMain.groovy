package E02
class DynamicTypingMain {

	static void printValue(int value) {
		println("Integer: " + value);
	}
	
	static void printValue(Object value) {
		println("Any: " + value);
	}
	
	static getValue(int i) {
		if ((i*i + 1) % 2 == 0) {
			"Hello world"
		} else {
			3
		}
	}
	
	public static void main(String[] args) {
		def value;
		
		value = "Hello world"
		printValue(value);
		
		value = 3
		printValue(value);
			
		value = getValue(1);
		printValue(value);
		
		value = getValue(2);
		printValue(value);
	}
	
}

