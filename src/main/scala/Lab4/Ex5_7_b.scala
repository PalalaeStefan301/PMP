package Lab4 

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.algorithm.factored._
import com.cra.figaro.library.atomic.discrete._
//import com.cra.figaro.language.Element[;]

object Ex5_7_b {

	def main(args: Array[String]) {
        //val spinner = Element(4, 6, 8, 12, 20)
        val spinner = FromRange(1,6) // 1-> "4"  2->"6"  3->"8"  4->"12"  5->"20"
        val die1 = FromRange(1, 5)
        val die2 = FromRange(1, 7)
        val die3 = FromRange(1,9)
        val die4 = FromRange(1,13)
        val die5 = FromRange(1,21)
        val total1 = Apply(die1, (i1: Int) => {
            if(i1==7)
                true
            else
                false
        })
        val total2 = Apply(die2, (i2: Int) => {
            if(i2==7)
                true
            else
                false
        })
        val total3 = Apply(die3, (i3: Int) => {
            if(i3==7)
                true
            else
                false
        })
        val total4 = Apply(die4, (i4: Int) => {
            if(i4==7)
                true
            else
                false
        })
        val total5 = Apply(die5, (i5: Int) => {
            if(i5==7)
                true
            else
                false
        })
        // nu mai stiu
	}
}