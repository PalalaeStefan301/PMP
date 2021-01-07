package Lab12

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._

object Ex2 {
	def main(args: Array[String]) {
        val x = Flip(0.8)
        val y = Flip(0.6)
        val z = If(x === y, Flip(0.9), Flip(0.1))
        z.observe(false)
        val veAnswer = VariableElimination.probability(y, true)
        for { i <- 10000 to 100000 by 10000 } {
            var totalSquaredError = 0.0
            for { j <- 1 to 100 } {
                Universe.createNew()
                val x = Flip(0.999)
                val y = Flip(0.99)
                val z = If(x === y, Flip(0.9999), Flip(0.0001))
                z.observe(false)
                val mh = MetropolisHastings(i, ProposalScheme.default, y)
                mh.start()
                val mhAnswer = mh.probability(y, true)
                val diff = veAnswer - mhAnswer
                totalSquaredError += diff × diff
            }
            val rmse = math.sqrt(totalSquaredError / 100)
            println(i + " samples: RMSE = " + rmse)
        }

    }
} // error of about 4×10-4 for importance sampling