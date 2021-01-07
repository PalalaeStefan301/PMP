package Lab12

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._

object Ex5 {
	def main(args: Array[String]) {
        val scheme = DisjointScheme(
        0.1 -> (() => ProposalScheme(z1)),
        0.1 -> (() => ProposalScheme(z2)),
        0.8 -> (() => ProposalScheme(x, y))
 )
    }
} //4×10^(-4)