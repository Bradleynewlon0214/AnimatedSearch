//2001-111-1

/* 
 * Name: Bradley Newlon
 * Assignment: Project 1 
 * Lab: CS 111 Section 1
 * Date: 2/28/20
 * 
 */

package cs111.project1;

public class MyPerson implements Person{

	private String givenName;
	private String familyName;
	
	public MyPerson(String givenName, String familyName) {
		this.givenName = givenName;
		this.familyName = familyName;
	}
	
	@Override
	public String getFamilyName() {
		return familyName;
	}

	@Override
	public void setFamilyName(String family_name) {
		this.familyName  = family_name;
	}

	@Override
	public String getGivenName() {
		return givenName;
	}

	@Override
	public void setGivenName(String given_name) {
		this.givenName = given_name;
	}

	@Override
	/**
	 * int compareTo - returns the int value of comparing this current objects given name to the given person's
	 */
	public int compareTo(Person other) {
		return this.getGivenName().compareToIgnoreCase(other.getGivenName());
	}
	
	@Override
	/**
	 * boolean equals - returns true if the given person's family name and given name equals the current object's
	 */
	public boolean equals(Object person) {
		Person p = (Person) person;
		if(this.getFamilyName().equals(p.getFamilyName()) && this.getGivenName().equals(p.getGivenName())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return givenName + " " + familyName;
		
	}
	

}
