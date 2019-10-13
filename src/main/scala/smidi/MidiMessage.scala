package smidi

import javax.sound.{midi => jmidi}
import scodec.bits._
import scodec.codecs.int24
import messages._

sealed trait MidiMessage {
  private[smidi] def toJava: jmidi.MidiMessage
}

case class NoteOn(channel: Int, key: Int, velocity: Int) extends MidiMessage {
  override private[smidi] def toJava = new jmidi.ShortMessage(NOTE_ON, channel, key, velocity)
}

case class NoteOff(channel: Int, key: Int, velocity: Int) extends MidiMessage {
  override private[smidi] def toJava = new jmidi.ShortMessage(NOTE_OFF, channel, key, velocity)
}

case class ProgramChange(channel: Int, preset: Int) extends MidiMessage {
  override private[smidi] def toJava = new jmidi.ShortMessage(PROGRAM_CHANGE, channel, preset, 0)
}

case class TempoChange(t: Int) extends MidiMessage {
  override private[smidi] def toJava = {
    val data = hex"03" ++ int24.encode(t).require.bytes
    new jmidi.MetaMessage(SET_TEMPO, data.toArray, data.length.toInt)
  }
}

trait SysExPayload {
  def toSysEx: SysEx

}

case class SysEx(message: ByteVector) extends MidiMessage {
  override private[smidi] def toJava = {
    val jsysex = new jmidi.SysexMessage()
    jsysex.setMessage(message.toArray, message.length.toInt)
    jsysex
  }

  def payload: Either[String, SysExPayload] = {
    // https://www.midi.org/specifications-old/item/manufacturer-id-numbers
    val manufacturerByte0 = message(1)
    manufacturerByte0 match {
      case 0 =>
        val manufacturerBytes12 = message.slice(2,4).toInt(signed = false, ordering = ByteOrdering.BigEndian)
        manufacturerBytes12 match {
          case _ => Left(s"Unknown manufacturer, extended code $manufacturerBytes12")
        }
      case _ => Left(s"Unknown manufacturer, short code $manufacturerByte0")
    }
  }
}
