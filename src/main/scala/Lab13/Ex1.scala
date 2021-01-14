package Lab13

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.algorithm.factored.beliefpropagation
import com.cra.figaro.algorithm.decision

object Ex1 {
	abstract class State {
		val confident: Element[Boolean]
		def possession: Element[Boolean] =
			If(confident, Flip(0.7), Flip(0.3))
		val poss = Array.fill(4, 4)(If(confident, Flip(0.7), Flip(0.3))) // line added
	}
	class InitialState() extends State {
		val confident = Flip(0.4)
	}
	class NextState(current: State) extends State {
		val confident =
		If(current.confident, Flip(0.6), Flip(0.3))
	}
	def stateSequence(n: Int): List[State] = {
		if (n == 0) List(new InitialState())
		else {
			val last :: rest = stateSequence(n - 1)
			new NextState(last) :: last :: rest
		}
	}
	def timing(obsSeq: List[Boolean]): Double = {
		Universe.createNew()
		val stateSeq = stateSequence(obsSeq.length)
		for { i <- 0 until obsSeq.length } {
			stateSeq(i).possession.observe(obsSeq(obsSeq.length - 1 - i))
			print(algorithm.mostLikelyValue(stateSeq(i).poss) // line added
		}
		val alg = VariableElimination(stateSeq(0).confident)
		val time0 = System.currentTimeMillis()
		alg.start()
		val time1 = System.currentTimeMillis()
		(time1 - time0) / 1000.0
	}
	def main(args: Array[String]) {
		val steps = 1000
		val obsSeq = List.fill(steps)(scala.util.Random.nextBoolean())
		println(steps + ": " + timing(obsSeq))
	}
}
