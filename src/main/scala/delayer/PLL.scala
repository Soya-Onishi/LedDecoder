package delayer

import chisel3._

class pll extends BlackBox {
  val io = IO (new Bundle {
    val inclk0 = Input(Clock())
    val areset = Input(Bool())

    val c0 = Output(Bool())
    val c1 = Output(Bool())
    val locked = Output(Bool())
  })
}
