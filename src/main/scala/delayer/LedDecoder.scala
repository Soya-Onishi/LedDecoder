
package delayer

import chisel3._
import chisel3.util.MuxLookup

class LedDecoder extends Module {
  val io = IO(new Bundle {
    val in = Input(UInt(4.W))
    val out = Output(UInt(7.W))
  })

  io.out := MuxLookup(
    io.in, "b111_1111".U,
    Array(
      0.U  -> "b100_0000".U,
      1.U  -> "b111_1001".U,
      2.U  -> "b010_0100".U,
      3.U  -> "b011_0000".U,
      4.U  -> "b001_1001".U,
      5.U  -> "b001_0010".U,
      6.U  -> "b000_0010".U,
      7.U  -> "b111_1000".U,
      8.U  -> "b000_0000".U,
      9.U  -> "b001_1000".U,
      10.U -> "b000_1000".U,
      11.U -> "b000_0011".U,
      12.U -> "b100_0110".U,
      13.U -> "b010_0001".U,
      14.U -> "b000_0110".U,
      15.U -> "b000_1110".U
    )
  )
}
