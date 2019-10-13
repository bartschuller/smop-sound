package saudio

import javax.sound.sampled

case class AudioFormat(jaf: sampled.AudioFormat) {
  override def toString: String = jaf.toString
}

object AudioFormat {
  def apply(sampleRate: Float,
            sampleBits: Int,
            channels: Int,
            signed: Boolean,
            bigEndian: Boolean): AudioFormat = {
    AudioFormat(
      new sampled.AudioFormat(
                              sampleRate,
                              sampleBits,
                              channels,
                              signed,
                              bigEndian))
  }
}
