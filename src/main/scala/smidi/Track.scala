package smidi
import javax.sound.{midi => jmidi}

case class Track(jt: jmidi.Track) {
  def add(me: MidiEvent): Boolean = jt.add(me.jme)
}
