package Lab8

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._

object Ex1 {
	class Research {
		val  
	}
	def main(args: Array[String]) {
		val test = Constant("Test")

		val algorithm = Importance(1000, test)
		algorithm.start()
		
		println(algorithm.probability(test, "Test"))
	}
}
//: Research and Development, Production, Sales,Human Resources, and Finance