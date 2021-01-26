/*package Lab10

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.algorithm.filtering.ParticleFilter

object Ex1 {
	def transition(investment: Int,profit: Int,capital: Int):
 		(Element[(Int, Int, Int)]) = {
             capital = capital - investment + profit;
	}
	def nextUniverse(previous: Universe): Universe = {
 		val next = Universe.createNew()
 		val state = Chain(investment,profit,capital)
		Apply(state, (s: (Int, Int, Int)) => s._1)("seated", next)
 		Apply(state, (s: (Int, Int, Int)) => s._2)("waiting", next)
 		Apply(state, (s: (Int, Int, Int)) => s._3)("arriving", next)
 		next
}
	}
	def main(args: Array[String]) {
        val time_spent = 90;
        val investment: Array[Element[Int]] =
            Array.fill(time_spent)(Constant(100))
        val profit: Array[Element[Int]] =
            Array.fill(time_spent)(Constant(100))
        val capital: Array[Element[Int]] =
            Array.fill(time_spent)(Constant(100))
		val alg = ParticleFilter(initial,nextUniverse,100)
		alg.start()
		for(minute <- 1 to time_spent)
		{
			val evidence = {
				arrivingObservation(minute) match {
					case None -> List()
					case Some(n) -> List(NamedEvidence("higher",Observation(n)))
				}
			}
			alg.advanceTime(evidence)
			print("Minute " + time + ":")
			println(alg.currentExpectation("higher",(i : Int)=>i))
		}
	}
}