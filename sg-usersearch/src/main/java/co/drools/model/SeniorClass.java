package co.drools.model;

import java.util.List;

public class SeniorClass {
	
	List<Students> stud;
	String highScorerMath;
	String highScorerChemistry;
	String highScorerPhysics;
	String highScorerEnglish;
	int studentPassCount=0;
	
	public String getHighScorerMath() {
		return highScorerMath;
	}

	public void setHighScorerMath(String highScorerMath) {
		this.highScorerMath = highScorerMath;
	}

	public String getHighScorerChemistry() {
		return highScorerChemistry;
	}

	public void setHighScorerChemistry(String highScorerChemistry) {
		this.highScorerChemistry = highScorerChemistry;
	}

	public String getHighScorerPhysics() {
		return highScorerPhysics;
	}

	public void setHighScorerPhysics(String highScorerPhysics) {
		this.highScorerPhysics = highScorerPhysics;
	}

	public String getHighScorerEnglish() {
		return highScorerEnglish;
	}

	public void setHighScorerEnglish(String highScorerEnglish) {
		this.highScorerEnglish = highScorerEnglish;
	}

	public int getStudentPassCount() {
		return studentPassCount;
	}

	public void setStudentPassCount(int studentPassCount) {
		this.studentPassCount = studentPassCount;
	}

	public List<Students> getStud() {
		return stud;
	}

	public void setStud(List<Students> stud) {
		this.stud = stud;
	}


}
