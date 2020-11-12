package Lab7

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._

object Ex1 {
	val s = 7/13
	val par = Map(s/8.00 -> "Par-2", s/2 -> "Par-1", s -> "Par",(4/5)*(1-((13*s)/8)) -> "Par+1", (1/5)*(1-((13*s)/8)) -> "Par+2")
	val holes = List(3,4,5,3,5,4,3,3,4,5,5,4,3,5,4,4,5,5)
	val targets = holes ::: data.keys.toList
	val golf_holes = generate(targets)
	for { (chance, par_value) <- data } {
			golf_holes(chance).observe(par_value)
	}

	def main(args: Array[String]) {
		val queryElements = holes.map(golf_holes(_))
		val queryTargets = golfPrior :: golfChangeRate :: queryElements
		val algorithm = VariableElimination(queryTargets:_*)
		algorithm.start()
		for { hole <- holes } {
			println("Probability to get the hole " + hole + " = " +
			algorithm.probability(golf_holes(hole), "Par"))
		}
		println("Expected prior probability of par = " +
		algorithm.mean(golfPrior))
		println("Expected par change rate = " +
		algorithm.mean(golfChangeRate))
		algorithm.kill()
	}
}