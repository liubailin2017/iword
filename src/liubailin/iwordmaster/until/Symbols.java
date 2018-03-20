package liubailin.iwordmaster.until;

import java.util.List;

/**
 * 
 *  主要用来对json进进解析
 *  */
public class Symbols {
	
	public static class SybolsItem {
		
		public static class PartItem{
			public String getPart() {
				return part;
			}
			public void setPart(String part) {
				this.part = part;
			}
			public List<String> getMeans() {
				return means;
			}
			public void setMeans(List<String> means) {
				this.means = means;
			}
			private String part;
			private List<String> means;
		}
		
		private String ph_en;
		private String ph_am;
		private String ph_other;
		private String ph_en_mp3;
		private String ph_am_mp3;
		private String ph_tts_mp3;
		private List<PartItem> partList;
		
		public String getPh_en() {
			return ph_en;
		}
		public void setPh_en(String ph_en) {
			this.ph_en = ph_en;
		}
		public String getPh_am() {
			return ph_am;
		}
		public void setPh_am(String ph_am) {
			this.ph_am = ph_am;
		}
		public String getPh_other() {
			return ph_other;
		}
		public void setPh_other(String ph_other) {
			this.ph_other = ph_other;
		}
		public String getPh_en_mp3() {
			return ph_en_mp3;
		}
		public void setPh_en_mp3(String ph_en_mp3) {
			this.ph_en_mp3 = ph_en_mp3;
		}
		public String getPh_am_mp3() {
			return ph_am_mp3;
		}
		public void setPh_am_mp3(String ph_am_mp3) {
			this.ph_am_mp3 = ph_am_mp3;
		}
		public String getPh_tts_mp3() {
			return ph_tts_mp3;
		}
		public void setPh_tts_mp3(String ph_tts_mp3) {
			this.ph_tts_mp3 = ph_tts_mp3;
		}
		public List<PartItem> getPartList() {
			return partList;
		}
		public void setPartList(List<PartItem> partList) {
			this.partList = partList;
		}
	}
	
	private List<SybolsItem> symbolList;

	public List<SybolsItem> getSymbolList() {
		return symbolList;
	}

	public void setSymbolList(List<SybolsItem> symbolList) {
		this.symbolList = symbolList;
	}
}
