package smidi

import javax.sound.{midi => jmidi}
import scodec.bits.ByteVector
import messages._

case class JavaWrapsScalaReceiver(receiver: Receiver) extends jmidi.Receiver {
  override def send(message: jmidi.MidiMessage, timeStamp: Long): Unit = {
    val status = message.getStatus
    status match {
      case jmidi.MetaMessage.META =>
        sys.error("We don't handle META messages yet (they don't occur in hardware)")
      case SYSTEM_EXCLUSIVE | SPECIAL_SYSTEM_EXCLUSIVE =>
        receiver.send(SysEx(ByteVector(message.getMessage)), timeStamp)
      case _ =>
        val shortMessage = message.asInstanceOf[jmidi.ShortMessage]
        val midiMessage = status match {
          case NOTE_ON =>
            NoteOn(shortMessage.getChannel, shortMessage.getData1, shortMessage.getData2)
          case NOTE_OFF =>
            NoteOff(shortMessage.getChannel, shortMessage.getData1, shortMessage.getData2)
        }
        receiver.send(midiMessage, timeStamp)
    }
  }

  override def close(): Unit = receiver.close()
}
