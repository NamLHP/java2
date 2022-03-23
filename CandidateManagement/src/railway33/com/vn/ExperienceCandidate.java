package railway33.com.vn;

public class ExperienceCandidate extends Candidate{
	
	private int expInYear;
	private String proSkill;
	public int getExpInYear() {
		return expInYear;
	}
	public void setExpInYear(int expInYear) {
		this.expInYear = expInYear;
	}
	public String getProSkill() {
		return proSkill;
	}
	public void setProSkill(String proSkill) {
		this.proSkill = proSkill;
	}
	@Override
	public String toString() {
		return "ExperienceCandidate [" + super.toString() + ", expInYear=" + expInYear + ", proSkill=" + proSkill + "]";
	}
	
}
