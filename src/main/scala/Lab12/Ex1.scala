/*package Lab12

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._

object Ex1 {
	def main(args: Array[String]) {
		val x = Flip(0.8)
		val y = Flip(0.6)
		val z = If(x === y, Flip(0.9), Flip(0.1))
		z.observe(false)
		val veAnswer = VariableElimination.probability(y, true)
		for { i <- 1000 to 10000 by 1000 } {
			var totalSquaredError = 0.0
			for { j <- 1 to 100 } {
				val imp = Importance(i, y)
				imp.start()
				val impAnswer = imp.probability(y, true)
				val diff = veAnswer - impAnswer
				totalSquaredError += diff × diff
			}
			val rmse = math.sqrt(totalSquaredError / 100)
			println(i + " samples: RMSE = " + rmse)
			}
		}
}