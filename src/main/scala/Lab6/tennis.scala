/*package Lab6

import com.cra.figaro.language._
import com.cra.figaro.library.atomic.discrete
import com.cra.figaro.language.Chain
import com.cra.figaro.library.compound._
import com.cra.figaro.library.compound.{RichCPD, OneOf, *}
import com.cra.figaro.language.{Flip, Constant, Apply}
import com.cra.figaro.algorithm.factored.VariableElimination

object tennis
{
def tennis(
    probP1ServeWin: Double, probP1Winner: Double, probP1Error: Double, //prob1Winner - sansa ca player 1 sa castige, prob1Error - sansa ca player 1 sa rateze
    probP2ServeWin: Double, probP2Winner: Double, probP2Error: Double): // same doar ca player 2
 Element[Boolean] = {
def rally(firstShot: Boolean, player1: Boolean): Element[Boolean] = {
    val pWinner = // sansa ca playerul actual sa castige
        if (firstShot && player1) probP1ServeWin // daca player 1 incepe runda
        else if (firstShot && !player1) probP2ServeWin // atunci e player 2
        else if (player1) probP1Winner // atunci player 1 castiga, pt ca player2, adica !player1 a ratat
        else probP2Winner // altfel player2 castiga
    val pError = if (player1) probP1Error else probP2Error // pError retine cine a ratat
    val winner = Flip(pWinner)
    val error = Flip(pError)
    If(winner, Constant(player1),
        If(error, Constant(!player1),
            rally(false, !player1))) // daca player1 e winer si player 2 a ratat(pError) atunci player2 nu are firstshot
}

 def game(
    p1Serves: Boolean, p1Points: Element[Int], // avem nevoie doar de p1Serves pt ca daca e !p1Serves inseamna ca player2 serveste
    p2Points: Element[Int]): Element[Boolean] = {
    val p1WinsPoint = rally(true, p1Serves) // punctele in cazul in care player1 serveste, ne folosim de rally()
    val newP1Points =
    Apply(p1WinsPoint, p1Points, (wins: Boolean, points: Int) => // sansa ca player1 sa castiga, atunci p1Points++ 
    if (wins) points + 1 else points)
    val newP2Points =
    Apply(p1WinsPoint, p2Points, (wins: Boolean, points: Int) => // sansa ca player1 sa castiga, atunci p2Points++
    if (wins) points else points + 1)
    val p1WinsGame =
    Apply(newP1Points, newP2Points, (p1: Int, p2: Int) => // sansa ca player1 sa castige tinand cont de punctele noi alea player2
    p1 >= 4 && p1 - p2 >= 2)
    val p2WinsGame =
    Apply(newP2Points, newP1Points, (p2: Int, p1: Int) => // sansa ca player2 sa castige tinand cont de punctele noi alea player1
    p2 >= 4 && p2 - p1 >= 2)
    val gameOver = p1WinsGame || p2WinsGame
    If(gameOver, p1WinsGame, game(p1Serves, newP1Points, newP2Points))
    }

 def play(
    p1Serves: Boolean, p1Sets: Element[Int], p2Sets: Element[Int], // p1Serves stim daca el a servit, si in rest avem seturile ambiilor playeri
    p1Games: Element[Int], p2Games: Element[Int]): Element[Boolean] = { // retinem meciurile castigate de fiecare
    val p1WinsGame = game(p1Serves, Constant(0), Constant(0))
    val newP1Games = // adaugam in p1WinsGames meciurile castiga de player1, daca player1 are 6 castiguri i se scrie 0 in vectorul de castiguri, adica are un set castigat
    Apply(p1WinsGame, p1Games, p2Games,
        (wins: Boolean, p1: Int, p2: Int) =>
            if (wins) {
                if (p1 >= 5) 0 else p1 + 1
                } else {
                    if (p2 >= 5) 0 else p1
                })

val newP2Games = // la fel ca si mai sus doar ca aplicat player2
    Apply(p1WinsGame, p1Games, p2Games,
        (wins: Boolean, p1: Int, p2: Int) =>
            if (wins) {
                if (p1 >= 5) 0 else p2
            } else {
                if (p2 >= 5) 0 else p2 + 1
            })
 val newP1Sets = // adaugam in vectorul de seturi seturile castigate de player1
    Apply(p1WinsGame, p1Games, p1Sets,
        (wins: Boolean, games: Int, sets: Int) =>
            if (wins && games == 5) sets + 1 else sets)
 val newP2Sets = // adaugam in vectorul de seturi seturile castigate de player2
     Apply(p1WinsGame, p2Games, p2Sets,
        (wins: Boolean, games: Int, sets: Int) =>
            if (!wins && games == 5) sets + 1 else sets)
 val matchOver = // in matchOver vedem cand se termina meciul, atunci cand unul din playeri a ajuns la 2 seturi castigate
    Apply(newP1Sets, newP2Sets, (p1: Int, p2: Int) =>
        p1 >= 2 || p2 >= 2)
If(matchOver,
    Apply(newP1Sets, (sets: Int) => sets >= 2),
    play(!p1Serves, newP1Sets, newP2Sets, newP1Games, newP2Games))
    }
    play(true, Constant(0), Constant(0), Constant(0), Constant(0))
 }

def main(args: Array[String]) {
    val x = tennis(0.5,0.8,0.2,0.5,0.2,0.8)
	val algorithm = Importance(x)
	algorithm.start()		
	println(algorithm.probability(x,true))
	}

}