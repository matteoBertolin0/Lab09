package it.polito.tdp.borders.model;

public class Country implements Comparable{
	
	private String stateAbb;
	private int countryCode;
	private String countryName;
	
	public Country(String stateAbb, int countryCode, String countryName) {
		this.stateAbb = stateAbb;
		this.countryCode = countryCode;
		this.countryName = countryName;
	}

	public String getStateAbb() {
		return stateAbb;
	}

	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + countryCode;
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((stateAbb == null) ? 0 : stateAbb.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (countryCode != other.countryCode)
			return false;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (stateAbb == null) {
			if (other.stateAbb != null)
				return false;
		} else if (!stateAbb.equals(other.stateAbb))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return stateAbb + ", " + countryName;
	}

	@Override
	public int compareTo(Object o) {
		Country c = (Country) o;
		
		return -c.getStateAbb().compareTo(this.getStateAbb());
	}
	
	
	
	
}
