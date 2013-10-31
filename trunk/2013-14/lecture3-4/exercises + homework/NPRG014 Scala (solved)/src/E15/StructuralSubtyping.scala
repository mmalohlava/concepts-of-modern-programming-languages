package E15

class Logger(printer : { def println(msg: String)}) {
	def log(msg: String) {
		printer.println(msg)
	}
}

object ConsoleLogger {
	def println(msg: String) {
		Console.println(msg)
	}
}

object StructuralSubtyping {
	
	def main(args: Array[String]) {
		val logger = new Logger(ConsoleLogger)

		logger.log("Hello world!")
	}
}