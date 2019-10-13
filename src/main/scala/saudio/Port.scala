package saudio

import javax.sound.sampled

case class Port(underlying: sampled.Port) extends Line {
}

object Port {
  case class Info(underlying: sampled.Port.Info) extends Line.Info {
    def isSource: Boolean = underlying.isSource
    override def toString: String = underlying.toString
  }
}