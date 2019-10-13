package saudio

import java.io.File

import javax.sound.sampled

object AudioSystem {
  final val NOT_SPECIFIED = sampled.AudioSystem.NOT_SPECIFIED
  type AudioInputStream = sampled.AudioInputStream
  def mixerInfo: Seq[Mixer.Info]          = sampled.AudioSystem.getMixerInfo.map(Mixer.Info)
  def mixer(mixerInfo: Mixer.Info): Mixer = mixerInfo.mixer
  def isLineSupported(info: Line.Info)    = sampled.AudioSystem.isLineSupported(info.underlying)
  def write(stream: AudioInputStream, fileType: sampled.AudioFileFormat.Type, file: File): Int =
    sampled.AudioSystem.write(stream, fileType, file)
  def line(info: Line.Info): Line = {
    sampled.AudioSystem.getLine(info.underlying) match {
      case jtdl: sampled.TargetDataLine => TargetDataLine(jtdl)
      case foo                          => sys.error(s"line: can't handle $foo yet")
    }
  }
}
