package smidi

import javax.sound.{midi => jmidi}

case class ScalaWrapsJavaReceiver(jrec: jmidi.Receiver) extends Receiver {
  override def send(message: MidiMessage, timeStamp: Long): Unit = {
    jrec.send(message.toJava, timeStamp)
  }

  override def close(): Unit = jrec.close()
}
