import smidi.{MidiEvent, MidiSystem, NoteOff, NoteOn, Sequence}

object PlaySequencer {
  def note(pitch: Int, start: Long, dur: Int): (MidiEvent, MidiEvent) =
    (MidiEvent(NoteOn(0, pitch, 100), start), MidiEvent(NoteOff(0, pitch, 0), start+dur))
  def main(args: Array[String]): Unit = {
    val sequencer = MidiSystem.sequencer
    val sequence = Sequence(Sequence.PPQ, 96)
    val track = sequence.createTrack

    // ordering of events while adding to track doesn't seem to matter
    (0 to 50).foreach { i =>
      val p = (i*5) % 24 + 60
      val (n1, n2) = note(p, i*96/4, 96/2)
      val (n3, n4) = note(p, (50-i)*96/4, 96/2)
      track.add(n2)
      track.add(n3)
      track.add(n1)
      track.add(n4)
    }
    sequencer.setSequence(sequence)
    sequencer.open()
    sequencer.start()
    while (sequencer.isRunning)
      Thread.sleep(1000L)
    sequencer.stop()
    sequencer.close()
  }
}
