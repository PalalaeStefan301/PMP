package Partial1

import com.cra.figaro.algorithm.factored._
import com.cra.figaro.language._
import com.cra.figaro.library.compound._
object Ex1 {
    Universe.createNew()
    // ex1)
    private val not_waking_up = Flip(0.4) // adunam din tabel probabilitatile de la fiecare caz in care ne-am trezit la timp
    private val late_bus = Flip(0.3) // adunam din tabel probabilitatile de la fiecare caz in care autobuzul a intarziat
    private val late_at_work = CPD(not_waking_up, late_bus,
        (false, false) -> Flip(0.1),
        (false, true) -> Flip(0.8),
        (true, false) -> Flip(0.7),
        (true, true) -> Flip(0.9))
    private val in_time_at_work = CPD(not_waking_up, late_bus,
        (false, false) -> Flip(0.9),
        (false, true) -> Flip(0.2),
        (true, false) -> Flip(0.3),
        (true, true) -> Flip(0.1))
    // ex 2)
    private val not_set_alarm = Flip(0.1)
    private val set_alarm = CPD(not_set_alarm,
        false -> Flip(0.1)
        true -> Flip(0.1)
    //mai sus avem toate prob din tabel exprimate in figaro 
    def main(args: Array[String]) {
        in_time_at_work.observe(true,false) //a)
        set_alarm.observe(true) //b
        late_at_work.observe(true,false) //c
        val alg = VariableElimination(not_set_alarm,not_waking_up,in_time_at_work)
        // pt 3) ma gandesc ca ar trebui interpretata si idea ca exista o sansa de 10% sa uiti de alarma, deci ar veni ca atunci cand nu ai te-ai trezit prob sa fie de 0.01
    }
 }