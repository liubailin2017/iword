package liubailin.iwordmaster.until;

import java.util.List;
/**
 * ±¾µØ´Êµä bean
 * @author liubailin
 *
 */
public class LocalSimple {
	private String hwV;
	public String getHwV() {
		return hwV;
	}
	public void setHwV(String hwV) {
		this.hwV = hwV;
	}
	private String pronL = "US";
	private String pronV;
	public static class Sens {
		private String pos;
		private String  defSenD;
		public String getPos() {
			return pos;
		}
		public void setPos(String pos) {
			this.pos = pos;
		}
		public String getDefSenD() {
			return defSenD;
		}
		public void setDefSenD(String defSenD) {
			this.defSenD = defSenD;
		}
		
	}
	private List<Sens> SensList;
	public String getPronL() {
		return pronL;
	}
	public void setPronL(String pronL) {
		this.pronL = pronL;
	}
	public String getPronV() {
		return pronV;
	}
	public void setPronV(String pronV) {
		this.pronV = pronV;
	}
	public List<Sens> getSensList() {
		return SensList;
	}
	public void setSensList(List<Sens> sensList) {
		SensList = sensList;
	}
	
	
}
