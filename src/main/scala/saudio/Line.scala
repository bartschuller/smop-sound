package saudio

import javax.sound.sampled
import javax.sound.sampled.LineListener

trait Line {
  def underlying: sampled.Line
  def addLineListener(ll: LineListener): Unit = underlying.addLineListener(ll)
  def close(): Unit = underlying.close()
  def lineInfo: Line.Info = Line.Info(underlying.getLineInfo)
  def isOpen: Boolean = underlying.isOpen
  def open(): Unit = underlying.open()
  def removeLineListener(ll: LineListener): Unit = underlying.removeLineListener(ll)
}

object Line {
  trait Info {
    def underlying: sampled.Line.Info
    override def toString: String = underlying.toString
  }
  object Info {
    def apply(sli: sampled.Line.Info): Info = {
      sli match {
        case dli: sampled.DataLine.Info => DataLine.Info(dli)
        case pi: sampled.Port.Info => Port.Info(pi)
        case _ => sys.error(s"Can't handle Line.Info $sli class ${sli.getClass}")
      }
    }
  }
}

trait LineClass