package RestMariri

import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.sampling.Importance
import com.cra.figaro.language.{Apply}
import com.cra.figaro.library.atomic.discrete.Binomial
import com.cra.figaro.library.compound._

object ex1 {
    def main(args: Array[String]) {	
      val day_quality = Binomial(7, 0.6)
      def getQuality(i: Int): String =
        if (i > 5) "prea multă ninsoare" 
        else if (i <= 2) "prea putina ninsoare" 
        else "normala"
      val weekQuality = Apply(day_quality, getQuality)
      println("valoarea saptamanii sa fie normala este de : " +VariableElimination.probability(weekQuality, "normala"))
      /*val algorithm1 = Importance(10000, weekQuality)
        algorithm1.start() 
        println(algorithm1.probability(weekQuality, 'normala))
        */
        // nu imi dau seama exact la C)
      // 0.745 este rezultatul
    }
}