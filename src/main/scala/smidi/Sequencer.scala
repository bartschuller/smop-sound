package smidi

import javax.sound.{midi => jmidi}

case class Sequencer(js: jmidi.Sequencer) {
  def setSequence(s: Sequence): Unit = js.setSequence(s.js)
  def open(): Unit = js.open()
  def close(): Unit = js.close()
  def start(): Unit = js.start()
  def stop(): Unit = js.stop()
  def isRunning: Boolean = js.isRunning
}
