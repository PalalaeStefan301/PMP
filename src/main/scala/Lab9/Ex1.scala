/*package Lab9

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.algorithm.filtering.ParticleFilter

object Ex1 {
	def transition(learned: Int,difficulty: Int):
 		(Element[(Int, Int, Int)]) = {
 		
	}
	def nextUniverse(previous: Universe): Universe = {
 		val next = Universe.createNew()
 		val previousSeated = previous.get[List[Int]]("seated")
 		val previousWaiting = previous.get[Int]("waiting")
 		val state = Chain(previousSeated, previousWaiting, transition _)
		Apply(state, (s: (Int, Int, Int)) => s._1)("seated", next)
 		Apply(state, (s: (Int, Int, Int)) => s._2)("waiting", next)
 		Apply(state, (s: (Int, Int, Int)) => s._3)("arriving", next)
 		next
}
	}
	def main(args: Array[String]) {
		val arrivingObservation = List(Some(1),Some(1),Some(1), None, None, None, None, None, None, None, None, None)
		val alg = ParticleFilter(initial,nextUniverse,100)
		alg.start()
		for(time <- 1 to 10)
		{
			val evidence = {
				arrivingObservation(time) match {
					case None -> List()
					case Some(n) -> List(NamedEvidence("score",Observation(n)))
				}
			}
			alg.advanceTime(evidence)
			print("Chapter " + time + ":")
			println("exptected learning = " + alg.currentExpectation("score",(i : Int)=>i))
		}
	}
}