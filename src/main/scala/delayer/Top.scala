
package delayer

import chisel3._
import chisel3.core.withClock

class Top extends Module {
  val io = IO(new Bundle {
    val led50 = Output(Vec(2, UInt(7.W)))
    val led25 = Output(Vec(2, UInt(7.W)))
  })

  val pll = Module(new PLL)
  val ledDecs = Array.fill(4)(Module(new LedDecoder))

  val clock50 = (pll.io.outClock0 && pll.io.lock).asClock()
  withClock(clock50){
    val delayer = Module(new Delayer)
    ledDecs(0).io.in := delayer.io.count(3, 0)
    ledDecs(1).io.in := delayer.io.count(7, 4)
  }

  val clock25 = (pll.io.outClock1 && pll.io.lock).asClock()
  withClock(clock25) {
    val delayer = Module(new Delayer)
    ledDecs(2).io.in := delayer.io.count(3, 0)
    ledDecs(3).io.in := delayer.io.count(7, 4)
  }

  io.led50(0) := ledDecs(0).io.out
  io.led50(1) := ledDecs(1).io.out
  io.led25(0) := ledDecs(2).io.out
  io.led25(1) := ledDecs(3).io.out
}
