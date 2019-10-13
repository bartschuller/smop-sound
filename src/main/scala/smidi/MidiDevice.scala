package smidi

import javax.sound.{midi => jmidi}

case class MidiDevice(jmd: jmidi.MidiDevice) {
  def maxReceivers: Int = jmd.getMaxReceivers
  def maxTransmitters: Int = jmd.getMaxTransmitters
  def open(): Unit = jmd.open()
  def isOpen: Boolean = jmd.isOpen
  def close(): Unit = jmd.close()
  def deviceInfo: MidiDevice.Info = MidiDevice.Info(jmd.getDeviceInfo)
  def receiver: Receiver = ScalaWrapsJavaReceiver(jmd.getReceiver)
  def transmitter: Transmitter = Transmitter(jmd.getTransmitter)
}

object MidiDevice {
  case class Info(jmdi: jmidi.MidiDevice.Info) {
    def description: String = jmdi.getDescription
    def name: String = jmdi.getName
    def vendor: String = jmdi.getVendor
    def version: String = jmdi.getVersion

    override def toString: String = jmdi.toString

    lazy val dev: MidiDevice = MidiDevice(jmidi.MidiSystem.getMidiDevice(jmdi))
  }
}