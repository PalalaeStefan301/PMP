package Lab5

import com.cra.figaro.library.atomic.discrete
import com.cra.figaro.language.Chain
import com.cra.figaro.library.compound.{RichCPD, OneOf, *}
import com.cra.figaro.language.{Flip, Constant, Apply}
import com.cra.figaro.algorithm.factored.VariableElimination


object Ex5
{
    def main(args: Array[String]) 
    {
// To keep the code simple, I just make the cards an integer
        val cards = List(5, 4, 3, 2, 1)
// The discrete uniform distribution chooses uniformly from a fixed
// set of possibilities
        val player1Card = discrete.Uniform(cards:_*)  
        val player2Card =
            Chain(player1Card, (card: Int) =>                // Player 2 can get any card except the first player’s card
            discrete.Uniform(cards.filter(_ != card):_*))
        val player1Bet1 =
            RichCPD(
                player1Card,                                  // Player 1 is more likely to bet with a higher card,
                OneOf(5, 4, 3) -> Flip(0.9),                  // but will sometimes bet with a lower card to bluff
                OneOf(1, 2, 3) -> Flip(0.4)
            )
        val player2Bet =
            RichCPD(
                player2Card, player1Bet1,
                (OneOf(5, 4), *) -> Flip(0.9),
                (*, OneOf(false)) -> Flip(0.5),
                (*, *) -> Flip(0.1)
            )

// Player 1’s second bet is only relevant if she passed the
// first time and player 2 bet
        val player1Bet2 =
            Apply(
                player1Card, player1Bet1, player2Bet,
                (card: Int, bet11: Boolean, bet2: Boolean) =>
                !bet11 && bet2 && (card == 5 || card == 4)
            )
            
// This element represents the gain to player 1 from the game. I have
// made it an Element[Double] so I can query its mean.
        val player1Gain =
            Apply(
                player1Card, player2Card, player1Bet1, player2Bet, player1Bet2,
                (card1: Int, card2: Int, bet11: Boolean,
                bet2: Boolean, bet12: Boolean) =>
                    if (!bet11 && !bet2) 0.0
                    else if (bet11 && !bet2) 1.0
                    else if (!bet11 && bet2 && !bet12) -1.0
                    else if (card1 > card2) 2.0
                    else -2.0
                )

        player1Card.observe(1)
        player1Bet1.observe(true)
        player2Card.observe(1)
        player2Bet.observe(true)
        val sequence_1 = VariableElimination(player1Gain)
        sequence_1.start()
        sequence_1.stop()
        println("Chance of sequence 1 is: " + sequence_1.mean(player1Gain))
        // sequence 1 =  if i try to  get the same card for both of players result is NaN(unknown or indeterminate), which is good cause its not possible

        player1Card.observe(2)
        player1Bet1.observe(false)
        player2Card.observe(4)
        player2Bet.observe(false)
        val sequence_2 = VariableElimination(player1Gain)
        sequence_2.start()
        sequence_2.stop()
        println("Chance of sequence 2 is: " + sequence_2.mean(player1Gain))
        // sequence 2 =  player1 has card 2 and player 2 has card 4 an both of them are passing, chance are : 0.0

        player1Card.observe(1)
        player1Bet1.observe(true)
        player2Card.observe(5)
        player2Bet.observe(true)
        val sequence_3 = VariableElimination(player1Gain)
        sequence_3.start()
        sequence_3.stop()
        println("Chance of sequence 3 is: " + sequence_3.mean(player1Gain))
        // sequence 3 =  player1 has card 1 and player 2 has card 5 an both of them are betting, chance are : -2.0

    }
}