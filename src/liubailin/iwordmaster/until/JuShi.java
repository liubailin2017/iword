package liubailin.iwordmaster.until;
/**
 * æ‰ ΩΩ·ππ
 * @author liubailin
 *
 */
public class JuShi {
	private String english;
	private String chinese;
	private String mp3;
	public String getEnglish() {
		return HighLight.green(english);
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getChinese() {
		return HighLight.green(chinese);
	}
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}
	public String getMp3() {
		return mp3;
	}
	public void setMp3(String mp3) {
		this.mp3 = mp3;
	}
	@Override
	public String toString() {
		return "JuShi [english=" + english + ", chinese=" + chinese + ", mp3=" + mp3 + "]";
	}

	
}
