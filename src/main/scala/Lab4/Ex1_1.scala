package Lab4 

import com.cra.figaro.language._
import com.cra.figaro.library.compound._
import com.cra.figaro.algorithm.factored._

object Ex1_1 {
    val wrong_Side = Flip(0.5)
    val greeting_Today = If(wrong_Side, Constant ("Oh no, not again"), 
        Select(0.5 -> "Hello, world!", 0.5 -> "Oh no, not again"))

    def main(args: Array[String]) {
        println("Today's greeting is \"Hello, world!\" " + "with probability " + VariableElimination.probability(greeting_Today, "Hello, world!") + ".")

    }
} 