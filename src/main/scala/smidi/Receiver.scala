package smidi

trait Receiver {
  def send(message: MidiMessage, timeStamp: Long): Unit
  def close(): Unit
}
