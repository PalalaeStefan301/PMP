package Lab3

import com.cra.figaro.algorithm.factored._
import com.cra.figaro.language._
import com.cra.figaro.library.compound._
object Covid {
 Universe.createNew()
 private val tuse = Flip(0.06)
 private val febra = Flip(0.04)
 private val covid = Flip(0.10)
 private val ai_covid = CPD(covid, febra,tuse,
 (true, false,false) -> Flip(0.9),
 (true, true,true) -> Flip(0.1),
 (true,false,true)->Flip(0.04),
 (true,true,false)->Flip(0.06))
 private val fara_simptome = CPD(covid,febra,tuse,
 (true,false,false) -> Flip(0.1),
 (false,false,false)->Flip(0.9))
 def main(args: Array[String]) {
 ai_covid.observe(true)
 fara_simptome.observe(true)
 }
 }
 //nu imi dau seama cum sa afisez