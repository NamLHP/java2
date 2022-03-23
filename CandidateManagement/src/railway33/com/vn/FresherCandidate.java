package railway33.com.vn;

public class FresherCandidate extends Candidate{
	private GraduationRank graduationRank;

	public GraduationRank getGraduationRank() {
		return graduationRank;
	}

	public void setGraduationRank(GraduationRank graduationRank) {
		this.graduationRank = graduationRank;
	}

	@Override
	public String toString() {
		return "FresherCandidate [" + super.toString() + ", graduationRank=" + graduationRank + "]";
	}
	
	
}
