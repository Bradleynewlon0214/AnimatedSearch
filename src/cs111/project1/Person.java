package cs111.project1;

public interface Person extends Comparable<Person> {
	public String getFamilyName();
	public void setFamilyName(String family_name);
	
	public String getGivenName();
	public void setGivenName(String given_name);
	
	@Override
	public boolean equals(Object object);
	
	@Override
	public int compareTo(Person other);
	
	@Override
	public String toString();
}
