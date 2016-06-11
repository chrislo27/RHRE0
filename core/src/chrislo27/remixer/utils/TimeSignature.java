package chrislo27.remixer.utils;

public enum TimeSignature {

	FOURFOUR(4, 4), THREEFOUR(3, 4), THREEEIGHT(3, 8);

	public final int notes;
	public final int division;

	TimeSignature(int notes, int division) {
		this.notes = notes;
		this.division = division;
	}

}
