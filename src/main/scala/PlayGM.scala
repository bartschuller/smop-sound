import smidi.MidiSystem.InOut
import smidi.{MidiSystem, NoteOff, NoteOn, ProgramChange}

object PlayGM {
  def main(args: Array[String]): Unit = {
    println(MidiSystem.midiDeviceInfo.mkString("\n"))
    val InOut(_, Some(outdev)) = MidiSystem.midiDevices("Gervill")

    outdev.open()
    val receiver = outdev.receiver

    receiver.send(ProgramChange(0, 12), -1)
    val msgOn = NoteOn(0, 60, 93)
    receiver.send(msgOn, -1)

    Thread.sleep(1000)

    val msgOff = NoteOff(0, 60, 0)

    receiver.send(msgOff, -1)


    outdev.close()

  }
}
