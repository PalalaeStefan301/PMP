package Lab4 

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.algorithm.factored._
import com.cra.figaro.library.atomic.discrete._

object Ex3_5 {
	def main(args: Array[String]) {
        val die1 = FromRange(1, 7)
        val die2 = FromRange(1, 7)
        val total = Apply(die1, die2, (i1: Int, i2: Int) => { 
            if (i1== 6) 
                if(i1+i2>8)
                    true
                else
                    false 
        })

        println(VariableElimination.probability(total, true))
	} // result is 0.1111111111111111
}