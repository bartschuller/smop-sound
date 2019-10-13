package smidi
import javax.sound.{midi => jmidi}

case class MidiEvent(jme: jmidi.MidiEvent) {
}

object MidiEvent {
  def apply(msg: MidiMessage, tick: Long): MidiEvent =
    MidiEvent(new jmidi.MidiEvent(msg.toJava, tick))
}