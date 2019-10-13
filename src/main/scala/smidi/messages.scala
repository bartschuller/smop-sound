package smidi

import javax.sound.{midi => jmidi}

object messages {
  final val NOTE_ON = jmidi.ShortMessage.NOTE_ON
  final val NOTE_OFF = jmidi.ShortMessage.NOTE_OFF
  final val PROGRAM_CHANGE = jmidi.ShortMessage.PROGRAM_CHANGE
  final val SYSTEM_EXCLUSIVE = jmidi.SysexMessage.SYSTEM_EXCLUSIVE
  final val SPECIAL_SYSTEM_EXCLUSIVE = jmidi.SysexMessage.SPECIAL_SYSTEM_EXCLUSIVE
  // meta messages (midi files only)
  final val SET_TEMPO = 0x51
}
