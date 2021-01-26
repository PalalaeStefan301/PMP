package Partial2

import com.cra.figaro.language.{Select, Apply, Constant, Element, Chain, Universe}
import com.cra.figaro.library.compound.{If, CPD, RichCPD, OneOf, *, ^^}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.algorithm.filtering.ParticleFilter

object Ex1 {

	class Start
	{
		val state = Flip(0.5)
	}
	class Innorat
	{
		val state = Flip(0.5)
		val umbrela = Flip(0.65)
		val fara_umbrela = Flip(0.35)
	}
	class Insorit
	{
		val state = Flip(0.6)
		val umbrela = Flip(0.15)
		val fara_umbrela = Flip(0.85)
	}
	class Ploios
	{
		val state = Flip(0.45)
		val umbrela = Flip(0.25)
		val fara_umbrela = Flip(0.75)
	}// aici sunt clasele cu observatiile ca membrii in clasa

	def nextUniverse(previous: Universe): Universe = {
      val next = Universe.createNew()
      val previousState = previous.get[Double]("weather")

      val newState = Chain(previousState, transition _)
      Apply(newState, (s: (Double, Double, Double)) => s._1)("ploios", next)
      Apply(newState, (s: (Double, Double, Double)) => s._2)("inorat", next)
      Apply(newState, (s: (Double, Double, Double)) => s._3)("insorit", next)
      next
    }

	def main(args: Array[String]) // Markov
	{
		val initial = Universe.createNew()
		Constant(1200.0)("weather", initial)
		val hours = 24
		val state = Array.fill(chapters)(object) // am incercat sa fac un array de obiecte dar nu stiam exact cum sa le pot dupa tranzita una din alta
		// aici ar trebuia sa fi legat clasele de Markov
		state(0) = Select(0.2 -> 'umbrela, 0.5 -> 'fara_umbrela)
		for { hour <- 1 until hours }
		{
			state(hour) = CPD(object.state(hour - 1),
				'umbrela -> Select(0.2 -> 'umbrela, 0.5 -> 'fara_umbrela),
				'fara_umbrela -> Select(0.2 -> 'umbrela, 0.5 -> 'fara_umbrela)
		}

		val alg = ParticleFilter(initial, nextUniverse, 24)
		alg.start() // am incercat aici cu universuri dar nu mai aveam timp


		Constant(1)("Start", univ)
		Constant(2)("Ploios", univ)
		Constant(3)("Inorat", univ)
		Constant(4)("Insorit", univ)

		println(VariableElimination.probability(passes(5), 'umbrela)) // a)

		passes(0).observe('umbrela)
		println(VariableElimination.probability(passes(6), 'umbrela)) // b)

		passes(1).observe('fara_umbrela)
		println(VariableElimination.probability(passes(9), 'fara_umbrela)) //c)
	}
}