package edt.core;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.*;
import java.io.*;

/**
 * Documents can be opened by an editor and have an associated file, a set of authors,
 * a map of elements (sections/paragraphs). They're also themselves a section.
 */

public class Document extends Section{

	/** Document's associated file. */
	private String _file = null;

	/** Indicates whether the document has been changed since the last save. */
	private boolean _stateChanged;
	
	/** Document's set of authors. */
	private TreeSet<Author> _authors = new TreeSet<Author>();

	/** Document's map of elements' unique id storage. */
	private Map<String, Element> _elements = new HashMap<String, Element>();

	/** Iterator for the set of authors. Non-serializable. */
	transient private Iterator<Author> iterator = null;

	/**
	 * Document's constructor.
	 *
	 * @param title - the document's title, passed to the Section's constructor and sets the setState variable to true,
	 *		  in order to allow the "Save" action to ask for a filename after the document's creation.
	 */
	public Document(String title){
		super(title);
		setState(true);
	}

	/**
	 * Document's constructor. Passes a null title to the Section's constructor, also sets setState to true.
	 */

	public Document(){
		super(null);
		setState(true);
	}

	/**
	 * Parses the imported file.
	 *
	 * @param fileName 
	 * @param document
	 */
	public void fileParser(String fileName, Document document) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		
		//Parses the document's title.
		String line = reader.readLine(); 
		document.setTitle(line);
		
		//Parses the authors
		line = reader.readLine();
		int nameEmailSwitch = 0;
		String tempString = null;
		for (String splitter : line.split("[/\\|]")){
			if (nameEmailSwitch%2 == 0){
				tempString = splitter;
				nameEmailSwitch++;
			} else{
				Author author = new Author(tempString, splitter);
				document.addAuthor(author);
				nameEmailSwitch++;
			}
		}
		
		//Parses the sections and paragraphs
		while ((line = reader.readLine()) != null){
			String[] fields = line.split("\\|");
			//try{
				registerFromFields(fields, document);
			//} catch (UnknownDataException e){ }
		}

		reader.close();
	}

	/**
	 * Registers the sections and paragraphs.
	 *
	 * @param fields 
	 * @param document
	 */
	public void registerFromFields(String[] fields, Document document){

		//Regular expression pattern to match a section.
		Pattern patSection = Pattern.compile("^(SECTION)");

		//Regular expression pattern to match a paragraph.
		Pattern patParagraph = Pattern.compile("^(PARAGRAPH)");

		if (patSection.matcher(fields[0]).matches()) {
                	Section section = registerSection(fields);			
			document.addSection(section);
                } else if (patParagraph.matcher(fields[0]).matches()) {
			if (document.getSectionArraySize() == 0){
				Paragraph paragraph = registerParagraph(fields);
				document.addParagraph(paragraph);
			} else{
				Section currentSection = document.getSection(document.getSectionArraySize()-1);
                        	Paragraph paragraph = registerParagraph(fields);
				currentSection.addParagraph(paragraph);
			}
                } else {
                        //throw new UnknownDataException(fields[0]);
                }
	}
	
	public void setState(boolean newState){

		_stateChanged = newState;
	}

	public boolean getState(){

		return _stateChanged;
	}
	
	/**
	 * Registers the section's id and title.
	 *
	 * @param fields
	 * @return the newly registered section.
	 */
	public Section registerSection(String... fields){
		
		String id = (fields[1]);
		Section section = new Section(fields[2]);
		if (id.length() > 0){
			_elements.put(id, section);
		}
		section.setId(id);
		return section;
	}

	/**
	 * Registers the paragraphs's content.
	 *
	 * @param fields
	 * @return the newly registered paragraph.
	 */
	public Paragraph registerParagraph(String... fields){

		Paragraph paragraph = new Paragraph(fields[1]);
		return paragraph;
	}

	/**
	 * @return the document's file.
	 */
	public String getFile(){

		return _file;
	}

	/**
	 * Sets the document's filename.
	 *
	 * @param fileName 
	 */
	public void setFile(String fileName){

		_file = fileName;
		_stateChanged = true;
	}

	/**
	 * Adds an author to the document's set of authors.
	 *
	 * @param fileName 
	 */
	public void addAuthor(Author author){

		this._authors.add(author);
		_stateChanged = true;
	}

	/**
	 * @return the next author in the authors set. 
	 */
	public Author getAuthor(){

		if (iterator == null){
			iterator = _authors.iterator();
		}	

		if (iterator.hasNext()){
			return (iterator.next());
		} else{
			return null;
		}
	}

	/**
	 * @return restarts the iterator so it can trasverse the authors list from the beginning. 
	 */
	public void rewindIterator(){

		this.iterator = null;
	}

	/**
	 * @return whether the document has a certain author associated to it or not. 
	 */
	public boolean hasAuthor(Author author){

		iterator = _authors.iterator();
		
		while (iterator.hasNext()){
			if (iterator.next().equals(author)){
				return true;
			}
		}

		return false;
	}

	/**
	 * @return the number of top sections in the document. 
	 */
	public int countTopSections(){

		return (this.getSectionArraySize());
	}

	/**
	 * @return the document's size in bytes. 
	 */
	public int getDocumentSize(){
		
		int byteCounter = 0;

		byteCounter += (this.getTitle().length());

		byteCounter = this.sectionRec(byteCounter);
		
		return byteCounter;
			
	}

	/**
	 * @return the number keys in the _elements map, i.e., the number
	 * 	   sections and paragraphs with an unique id. 
	 */
	public int countHashMapKeys(){
		
		int counter = 0;	

		for (String key : _elements.keySet()){
			counter++;
		}

		return counter;
	}

	/**
	 * Iterates through the set of keys (ids) from the _elements map to compare it with a given key.
	 * If a match is found, the old <K, V> association is replaced with a new one.
	 *
	 * @param key - the key to be searched on the hashmap.
	 * @param element - the element that might be added to the hashmap.
	 * @return true, if the key was found within the hashmap and its associated value was replaced;
	 * 	   false, if it wasn't.
	 */


	public boolean searchKeyPossiblyReplaceValue(String key, Element element){
		
		for (Map.Entry<String, Element> entry : _elements.entrySet()){
			String testKey = entry.getKey();

			if (testKey.equals(key)){
				entry.getValue().setId("");
				_elements.put(key, element);
				element.setId(key);
				return true;
			}
		}
		
		return false;
		
	}

	public void putElement(String id, Element element){
		
		_elements.put(id, element);
	}

	public void removeElement(String id){
		
		_elements.remove(id);
	}

	public Element getElement(String id){
		
		return _elements.get(id);
	}



	@Override
	@SuppressWarnings("nls")
	public String toString(){
		return "document";
	}

}
