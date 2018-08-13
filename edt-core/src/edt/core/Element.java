package edt.core;
import java.io.*;

/**
 * An element functions as an "interface" for both sections and paragraphs, which are visitable. Their unique id is stored in
 * a string outside of the _elements map for easier access. 
 */

public abstract class Element implements Serializable{
	
	/** The element's unique id by which it's globally identified. */
	private String _uniqueId = "";

	public void setId(String uniqueId){

		_uniqueId = uniqueId;
	}
	
	public String getId(){

		return _uniqueId;
	}
	
	/**
	 *@param - the visitor used to visit the element.  
	 */
	public abstract void accept(Visitor v);

}
