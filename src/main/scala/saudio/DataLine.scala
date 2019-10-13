package saudio

import javax.sound.sampled

trait DataLine extends Line {
  override def underlying: sampled.DataLine
  def available: Int = underlying.available()
}

object DataLine {
  case class Info(underlying: sampled.DataLine.Info) extends Line.Info {

  }

  object Info {
    def apply(lineClass: LineClass, format: AudioFormat): Info = {
      val clazz = lineClass match {
        case TargetDataLine => classOf[sampled.TargetDataLine]
        case _ => sys.error(s"Don't know what to do with $lineClass")
      }
      Info(new sampled.DataLine.Info(clazz, format.jaf))
    }
  }
}