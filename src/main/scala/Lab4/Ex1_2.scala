package Lab4 

import com.cra.figaro.language._
import com.cra.figaro.library.compound._
import com.cra.figaro.algorithm.factored._

object Ex1_2 {
    val sunny_Today = Flip(0.5)
    val wrong_Side = Flip(0.5)
    val greeting_Today = If(wrong_Side, Constant ("Oh no, not again"), If(sunny_Today,
        Select(0.5 -> "Hello, world!", 0.5 -> "Nice weather"),
        Select(0.5 -> "Hello, world!", 0.5 -> "Oh no, not again")))
    val sunny_Tomorrow = If(sunny_Today, Flip(0.5), Flip(0.5))
    val greeting_Tomorrow = If(sunny_Tomorrow,
        Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universe!"),
        Select(0.2 -> "Hello, world!", 0.8 -> "Oh no, not again"))
    def main(args: Array[String]) {
        println("Today's greeting is \"Hello, world!\" " + "with probability " + VariableElimination.probability(greeting_Today, "Hello, world!") + ".")

        greeting_Today.observe("Hello, world!")
        println("If today's greeting is \"Hello, world!\", today's " + "weather is sunny with probability " + VariableElimination.probability(sunny_Today, true) + ".")
        println("If today's greeting is \"Hello, world!\", " + "tomorrow's greeting will be \"Hello, world!\" " +"with probability " + VariableElimination.probability(greeting_Tomorrow, "Hello, world!") + ".")        
    }
} 