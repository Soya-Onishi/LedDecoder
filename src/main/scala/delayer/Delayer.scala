
package delayer

import chisel3._

class Delayer(threshold: Int = 1000000, width: Int = 8, init: Int = 0) extends Module {
  val io = IO(new Bundle {
    val count = Output(UInt(width.W))
  })

  val clockCounter = RegInit(init.U(width.W))
  val counter = RegInit(init.U(width.W))

  io.count := counter

  when(clockCounter === threshold.U) {
    clockCounter := 0.U
    counter := counter + 1.U
  } otherwise {
    clockCounter := clockCounter + 1.U
  }
}
