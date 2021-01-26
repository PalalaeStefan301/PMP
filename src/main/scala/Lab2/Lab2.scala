/*package Lab2 

import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._

object Ex1 {
	def main(args: Array[String]) {
		
		abstract class Persoana
		class Student(var nume: String, var prenume: String, var an: Int, var listaMaterii: Array[(String, Integer)]) extends Persoana{
				def addMaterie(materie: String, nota: Int): Unit={
					listaMaterii :+ (materie,0)
				}
                def setNota(materie: String, nota: Integer): Unit={
					// first attempt
                    //listaMaterii.foreach { elem => if (elem._1 == materie) elem._2 = 0}
					listaMaterii = listaMaterii.filter(_ != (materie,0))
					listaMaterii :+ (materie,nota)
                }
				def getNota(materie: String): Unit={
					listaMaterii.foreach { elem => if (elem._1 == materie) return elem._2 }
				}
		}
		class Profesor(nume: String, prenume: String, materie: String) extends Persoana
		{

		}

		/*def toString_details(persoana : Persoana): Unit =
		{
			if(persoana.isInstanceOf[Profesor])
			{
				var prof = persoana.isInstanceOf[Profesor]
				println(prof->nume)
			}
		} */
	}
}