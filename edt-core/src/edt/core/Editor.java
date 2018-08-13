package edt.core;

import java.io.*;

/**
 * An editor has only one document opened at a time.
 */

public class Editor{

	/** Current document associated with the editor */
	private Document _currDocument;

	/**
	 * Editor's constructor.
	 *
	 * @param document - document to be opened
	 */
	public Editor(Document document){
		_currDocument = document;
	}

	/**
	 * Editor's constructor.
	 */
	public Editor(){
	}

	/**
	 * @return the editor's current document.
	 */
	public Document getDocument(){
		return this._currDocument;
	}

	/**
	 * Creates a new document and sets it as the current document.
	 */
	public void newDoc(){
		_currDocument = new Document();
	}

	/**
	 * Reads a file from disk.
	 *
	 * @param fileName - the file's name.
	 */
	public void readFile(String fileName) throws IOException, FileNotFoundException, ClassNotFoundException{

		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
		
		Document document = (Document)in.readObject();
		
		_currDocument = document;
		in.close();
				
		_currDocument.setFile(fileName);
	
	}

	/**
	 * Writes a file to disk.
	 *
	 * @param fileName - the file's name.
	 */

	public void writeFile(String fileName) throws IOException, FileNotFoundException{
		
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		
		out.writeObject(_currDocument);

		out.close();
		
	}
} 
