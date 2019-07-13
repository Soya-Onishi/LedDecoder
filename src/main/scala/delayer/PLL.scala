package delayer

import chisel3._

class PLL extends BlackBox {
  val io = IO (new Bundle {
    val clock = Input(Clock())
    val reset = Input(Bool())

    val outClock0 = Output(Bool())
    val outClock1 = Output(Bool())
    val lock = Output(Bool())
  })
}
