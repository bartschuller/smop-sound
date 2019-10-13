package smidi

import javax.sound.{midi => jmidi}

case class Transmitter(jtrans: jmidi.Transmitter) {
  def setReceiver(receiver: Receiver): Unit = jtrans.setReceiver(JavaWrapsScalaReceiver(receiver))
}
