/*package Lab11

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._

object Ex1 {
	def main(args: Array[String]) {
		val becoming_president = Flip(1/40000000)
		val left_handed_person = Flip(0.1)
        val went_to_harvard = Flip(0.15)
		val be_president = CPD(becoming_president,left_handed_person,went_to_harvard
			(true,true,true)->Flip(0.5),
			(false,true,true)->false,
            (false,true,false)->false
			(true,false,true)->false,
            (true,false,false)->false,
			(false,false,true)->false,
            (false,false,false)->false)
		

		be_president.observe(true)
 		val alg = VariableElimination(becoming_president,left_handed_person,went_to_harvard)
         println(Importance.probability(be_president))
	}
}