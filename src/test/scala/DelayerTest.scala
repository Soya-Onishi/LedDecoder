import chisel3._
import chisel3.util._
import chisel3.iotesters._
import org.scalatest.{Matchers, FlatSpec}
import delayer.Top

class DelayerPeekPokeTest(c: Top) extends PeekPokeTester(c) {
  (1 to 100).foreach {
    _ =>
      val led25 = peek(c.io.led25)
      val led50 = peek(c.io.led50)

      println(s"$led25, $led50")
      step(1)
  }
}

class DelayerTest extends FlatSpec with Matchers {
  it should "success" in {
    iotesters.Driver(() => new Top(0)){
      c =>
        new DelayerPeekPokeTest(c)
    } should be (true)
  }
}
