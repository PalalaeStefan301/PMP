/*
package RestMariri

import scala.collection._
import com.cra.figaro.language._
import com.cra.figaro.library.atomic.continuous.Beta
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.experimental.normalproposals.Normal
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound._
import com.cra.figaro.library.atomic.discrete._
import com.cra.figaro.algorithm.factored._


object restanta {
    val chapters = 10
    // markov model
    abstract class State {
      var systemState = Array.fill(chapters)(Constant(false))
      var value = Array.fill(chapters)(Constant('A))
    }
    class initialState() extends State {
      state(0) = Select(0.721 -> 'A, 0.202 -> 'B, 0.067 -> 'C, 0.1 -> 'D)
      value(0) = CPD(state(0),
                            'A -> Flip(0.25),
                            'B -> Flip(0.25),
                            'C -> Flip(0.25),
                            'D -> Flip(0.25))
    }

    // next state from current state:
    class NextState(current: State, iteration: Int) extends State {

      state(iteration) = CPD(state(chapter - 1),
        'A -> Select(0.721->'A,0.202 ->'B, 0.067 -> 'C, 0.1 -> 'D),
        'B -> Select(0->'A,0.581 ->'B, 0.407->'C,0.012->'D),
        'C -> Select(0->'A,0 ->'B, 0.75->'C, 0.25 -> 'D),
        'D -> Select(0->'A,0 ->'B,0->'C, 1.0 -> 'D))

      value(iteration) = CPD(state(iteration),
        'A -> Flip(0.25),
        'B -> Flip(0.25),
        'C -> Flip(0.25),
        'D -> Flip(0.25))
    }
    def main(args: Array[String]) {	        
        
    }

  }
  */
  //aici am scris cat mai mult cod fara sa il mai testez din lipsa de timp, este comentat codul pt a putea rula ex1 si ex2