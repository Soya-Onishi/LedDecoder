
package delayer

import chisel3._
import chisel3.core.withClockAndReset
import chisel3.experimental.RawModule

class Top(threshold: Int = 1000000) extends Module {
  val io = IO(new Bundle {
    val led50 = Output(Vec(2, UInt(7.W)))
    val led25 = Output(Vec(2, UInt(7.W)))
  })

  val pll = Module(new pll)
  pll.io.inclk0 := clock
  pll.io.areset := reset

  val ledDecs = Array.fill(4)(Module(new LedDecoder))

  val clock50 = (pll.io.c0 && pll.io.locked).asClock()
  withClockAndReset(clock50, reset){
    val delayer = Module(new Delayer(threshold = threshold))
    ledDecs(0).io.in := delayer.io.count(3, 0)
    ledDecs(1).io.in := delayer.io.count(7, 4)
  }

  val clock25 = (pll.io.c1 && pll.io.locked).asClock()
  withClockAndReset(clock25, reset) {
    val delayer = Module(new Delayer(threshold = threshold))
    ledDecs(2).io.in := delayer.io.count(3, 0)
    ledDecs(3).io.in := delayer.io.count(7, 4)
  }

  io.led50(0) := ledDecs(0).io.out
  io.led50(1) := ledDecs(1).io.out
  io.led25(0) := ledDecs(2).io.out
  io.led25(1) := ledDecs(3).io.out
}
