
import chisel3._
import delayer.Top

object Main extends App {
  Driver.execute(args, () => new Top)
}
