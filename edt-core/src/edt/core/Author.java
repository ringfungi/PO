package edt.core;
import java.util.TreeSet;
import java.io.*;

/**
 * Authors have a name and an e-mail address and are associated with
 * a document.
 */

public class Author implements Comparable<Author>, Serializable{

	/** Author's name. */
	private String _name;

	/** Author's e-mail. */
	private String _email;
  	
	/**
	 * Author's constructor.
	 *
	 * @param name
	 * @param email
	 */
	public Author(String name, String email){

		_name = name;
		_email = email;
	}
	
	/**  	
	 * @return the name of the author.
	 */
	public String getName(){

		return _name;
	}

	/**  	
	 * @return the e-mail address of the author.
	 */  
	public String getEmail(){

		return _email;
	}

	/**
	 * Allows the authors set to be alphabetically ordered each time an author
	 * is added to the set.
	 * @param other
	 * @return the natural order of the elements.
	 */	
   	@Override
	public int compareTo(Author other){

		if (other.getName() == null){
			return 1;
		}else{
			return this.getName().compareTo(other.getName());
		}
	}

	@Override
	public boolean equals(Object o){
		
		if (o instanceof Author){
			Author other = (Author) o;
			return _name.equals(other.getName())/* && _email.equals(other.getEmail())*/;
		}

		return false;
	}

	
	/**
	* String representation of an author. Wasn't used.
	*/
	@Override
	@SuppressWarnings("nls")
	public String toString(){
		return "Author: " + _name + " " + "Email: " + _email;
	}
}
