
package delayer

import chisel3._

class Delayer(threshold: Int = 1000000, init: Int = 0) extends Module {
  val io = IO(new Bundle {
    val count = Output(UInt(8.W))
  })

  val clockCounter = RegInit(init.U(32.W))
  val counter = RegInit(init.U(8.W))

  io.count := counter

  when(clockCounter === threshold.U) {
    clockCounter := 0.U
    counter := counter + 1.U
  } otherwise {
    clockCounter := clockCounter + 1.U
  }
}
