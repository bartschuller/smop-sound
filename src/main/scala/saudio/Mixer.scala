package saudio

import javax.sound.sampled

case class Mixer(jm: sampled.Mixer) {
  def sourceLineInfo: Seq[Line.Info] = jm.getSourceLineInfo.map(Line.Info.apply)
  def targetLineInfo: Seq[Line.Info] = jm.getTargetLineInfo.map(Line.Info.apply)
}

object Mixer {
  case class Info(jmi: sampled.Mixer.Info) {
    def description: String = jmi.getDescription
    def name: String = jmi.getName
    def vendor: String = jmi.getVendor
    def version: String = jmi.getVersion
    override def toString: String = jmi.toString

    lazy val mixer = Mixer(sampled.AudioSystem.getMixer(jmi))
  }
}