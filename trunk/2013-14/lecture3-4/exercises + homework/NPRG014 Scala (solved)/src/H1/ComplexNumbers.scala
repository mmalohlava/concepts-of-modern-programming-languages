package H1

// Add necessary class and object definitions in order to make the statements in the main work.

class Complex(private val real: Int, private val imag: Int) {
	def +(that: Complex) = Complex(real + that.real, imag + that.imag)
	def *(that: Complex) = Complex(real*that.real - imag*that.imag, imag*that.real + real*that.imag)
	def unary_- = Complex(-real, -imag)
	
	override def toString = real + (if (imag >= 0) "+" + imag else "-" + (-imag)) + "i"
}

object Complex {
	def apply(real: Int, imag: Int) = new Complex(real, imag)
}

object I extends Complex(0, 1)

object ComplexNumbers {
	implicit def intToComplex(num: Int) = Complex(num, 0)

	
	def main(args: Array[String]) {
		
		println(Complex(1,2)) // 1+2i

		println(1 + 2*I + I*3 + 2) // 3+5i

		val c = (2+3*I + 1 + 4*I) * I
		println(-c) // 7-3i
	}
}