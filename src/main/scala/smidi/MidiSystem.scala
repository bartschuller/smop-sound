package smidi

import java.io.File

import javax.sound.{midi => jmidi}

object MidiSystem {
  case class InOut(in: Option[MidiDevice], out: Option[MidiDevice])
  def midiDeviceInfo: Seq[MidiDevice.Info] = jmidi.MidiSystem.getMidiDeviceInfo.map(MidiDevice.Info)
  def midiDevice(info: MidiDevice.Info): MidiDevice = info.dev
  def midiDevices(withName: String): InOut = {
    val devinfos = midiDeviceInfo.filter(_.name == withName)
    InOut(devinfos.find(_.dev.maxTransmitters != 0).map(_.dev), devinfos.find(_.dev.maxReceivers != 0).map(_.dev))
  }
  def sequencer(connected: Boolean): Sequencer = Sequencer(jmidi.MidiSystem.getSequencer(connected))
  def sequencer: Sequencer = sequencer(true)
  def write(seq: Sequence, typ: Int, file: String) = jmidi.MidiSystem.write(seq.js, typ, new File(file))
}
