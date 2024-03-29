/*package Lab5

import com.cra.figaro.library.atomic.discrete
import com.cra.figaro.language.Chain
import com.cra.figaro.library.compound.{RichCPD, OneOf, *}
import com.cra.figaro.language.{Flip, Constant, Apply}
import com.cra.figaro.algorithm.factored.VariableElimination



object Ex4_changed
{
    def main(args: Array[String]) 
    {
// To keep the code simple, I just make the cards an integer
        val cards = List(5, 4, 3, 2, 1)
// The discrete uniform distribution chooses uniformly from a fixed
// set of possibilities
        val player1Card1 = discrete.Uniform(cards:_*)  
        val player1Card2 = Chain(player1Card1, (card: Int) =>                // Player 2 can get any card except the first player’s card
            discrete.Uniform(cards.filter(_ != card):_*))
        val player2Card1 =
            Chain(player1Card1, player1Card2, (card: Int), (card2: Int) =>                // Player 2 can get any card except the first player’s card
            discrete.Uniform(cards.filter(_ != card && _ != card2):_*))
        val player2Card2 =
            Chain(player1Card1,player1Card2,player2Card1, (card: Int),(card2: Int),(card3: Int) =>                // Player 2 can get any card except the first player’s card
            discrete.Uniform(cards.filter(_ != card && _ != card2 && _ != card3):_*))
        val player1Bet1 =
            RichCPD(
                player1Card1, player1Card2                                 // Player 1 is more likely to bet with a higher card,
                OneOf(5, 4, 3) -> Flip(0.9),                  // but will sometimes bet with a lower card to bluff
                OneOf(1, 2) -> Flip(0.1)
            )
        val player2Bet =
            RichCPD(
                player2Card1,player2Card2 player1Bet1,
                (OneOf(5, 4), *) -> Flip(0.9),
                (*, OneOf(false)) -> Flip(0.5),
                (*, *) -> Flip(0.1)
            )

// Player 1’s second bet is only relevant if she passed the
// first time and player 2 bet
        val player1Bet2 =
            Apply(
                player1Card1, player1Card2, player1Bet1, player2Bet,
                (card: Int, bet11: Boolean, bet2: Boolean) =>
                !bet11 && bet2 && (card == 5 || card == 4)
            )
            
// This element represents the gain to player 1 from the game. I have
// made it an Element[Double] so I can query its mean.
        val player1Gain =
            Apply(
                player1Card1, player1Card2, player2Card1,player2Card2, player1Bet1, player2Bet, player1Bet2,
                (card1: Int, card2: Int, bet11: Boolean,
                bet2: Boolean, bet12: Boolean) =>
                    if (!bet11 && !bet2) 0.0
                    else if (bet11 && !bet2) 1.0
                    else if (!bet11 && bet2 && !bet12) -1.0
                    else if (card1 > card2) 2.0
                    else -2.0
                )
        
        player1Card.observe(4)
        player1Bet1.observe(true)
        val alg1 = VariableElimination(player1Gain)
        alg1.start()
        alg1.stop()
        println("Expected gain for betting:" + alg1.mean(player1Gain))

        player1Bet1.observe(false)
        val alg2 = VariableElimination(player1Gain)
        alg2.start()
        alg2.stop()
        println("Expected gain for passing:" + alg2.mean(player1Gain))
        player1Card.unobserve()
        player1Bet1.unobserve()

        player2Card.observe(3)
        player1Bet1.observe(true)
        player2Bet.observe(true)
        val alg3 = VariableElimination(player1Gain)
        alg3.start()
        alg3.stop()
        println("Expected gain for betting:" + alg3.mean(player1Gain))

        player2Bet.observe(false)
        val alg4 = VariableElimination(player1Gain)
        alg4.start()
        alg4.stop()
        println("Expected gain for passing:" + alg4.mean(player1Gain))

    }
}