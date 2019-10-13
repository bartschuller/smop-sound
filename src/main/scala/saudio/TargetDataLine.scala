package saudio

import javax.sound.sampled
import scodec.bits.ByteVector

case class TargetDataLine(underlying: sampled.TargetDataLine) extends DataLine {
  def open(format: AudioFormat): Unit = underlying.open(format.jaf)
  def open(format: AudioFormat, bufferSize: Int): Unit = underlying.open(format.jaf, bufferSize)
  def start(): Unit = underlying.start()
  def stop(): Unit = underlying.stop()
  def read(len: Int): ByteVector = {
    val byteArray = new Array[Byte](len)
    val resultLength = underlying.read(byteArray, 0, len)
    ByteVector.view(byteArray, 0, resultLength)
  }
}

object TargetDataLine extends LineClass