package Lab4 

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.algorithm.factored._
import com.cra.figaro.library.atomic.discrete._

object Ex4_6 {
	def main(args: Array[String]) {
        val die1 = FromRange(1, 7)
        val die2 = FromRange(1, 7)
        val first = Apply(die1, die2, (i1: Int, i2: Int) => { 
            if (i1== i2) 
                1
            else
                0
        })

        val second = Apply(die1, die2, (i1: Int, i2: Int) => { 
            if (i1== i2) 
                1
            else
                0
        })

        val third = Apply(die1, die2, (i1: Int, i2: Int) => { 
            if (i1== i2) 
                1
            else
                0
        })

        val total = Apply(first,second,third,(i1: Int , i2: Int, i3: Int) => i1+i2+i3)

        println(VariableElimination.probability(total, 3))
	} // result is 0.16666666666666666
}