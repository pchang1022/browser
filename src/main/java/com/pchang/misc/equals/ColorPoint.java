package com.pchang.misc.equals;

public class ColorPoint extends Point {

	private final Color color;

	public ColorPoint(int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}

	// Broken - violates symmetry!
	// @Override
	@Override
	public boolean equals1(Object o) {
		if (!(o instanceof ColorPoint)) return false;
		return super.equals(o) && ((ColorPoint) o).color == color;
	}

	// Broken - violates transitivity!
	// @Override
	public boolean equals2(Object o) {
		if (!(o instanceof Point)) return false;
		// If o is a normal Point, do a color-blind comparison
		if (!(o instanceof ColorPoint)) {
			System.out.println("o is NOT a ColorPoint - doing a color blind comparison!");
			return o.equals(this);
		}

		// o is a ColorPoint; do a full comparison
		System.out.println("o is a ColorPoint - doing a color comparison as well!");
		boolean what = super.equals(o) && ((ColorPoint) o).color == color;
		// return super.equals(o) && ((ColorPoint) o).color == color;
		return what;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ColorPoint [color=");
		builder.append(color);
		builder.append("]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		ColorPoint other = (ColorPoint) obj;
		if (color != other.color) return false;
		return true;
	}

}
