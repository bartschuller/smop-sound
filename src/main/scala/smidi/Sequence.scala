package smidi
import javax.sound.{midi => jmidi}

case class Sequence(js: jmidi.Sequence) {
  def createTrack = Track(js.createTrack())
}

object Sequence {
  def apply(divisionType: Float, resolution: Int, numTracks: Int = 0): Sequence =
    Sequence(new jmidi.Sequence(divisionType, resolution, numTracks))
  val PPQ: Float = jmidi.Sequence.PPQ
  val SMPTE_24: Float = jmidi.Sequence.SMPTE_24
  val SMPTE_25: Float = jmidi.Sequence.SMPTE_25
  val SMPTE_30: Float = jmidi.Sequence.SMPTE_30
  val SMPTE_30DROP: Float = jmidi.Sequence.SMPTE_30DROP
}