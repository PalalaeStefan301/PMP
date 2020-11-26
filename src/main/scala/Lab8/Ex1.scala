package Lab8

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._

object Ex1 {
	abstract class Department (name: String) {

	}
	class Research_and_Development(name: String) extends Department(name){
		val quality = Uniform(0, 1)
	}
	class Production(name: String) extends Department(name){
		val comunication_with_sales = Uniform(0,1)
		val quality = Uniform(0, 1)
	}
	class Sales(name: String) extends Department(name){
		val nr_prod_sold
		val quality = Uniform(0, 1)
	}
	class Human_resources(name: String) extends Department(name){
		val quality = Uniform(0, 1)
	}
	class Finance(name: String) extends Department(name){
		val quality = Uniform(0, 1)
	}
	case class Company(res_dev: Research_and_Development, production: Production, sales: Sales, hr: Human_resources, finance: Finance){
		
	}
	def main(args: Array[String]) {
		val test = Constant("Test")

		val algorithm = Importance(1000, test)
		algorithm.start()
		
		println(algorithm.probability(test, "Test"))
	}
}