package edt.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Sections are a kind of text element that belong to a document (and are thus a top section) or to a "vanilla" section.
 * They can contain one or more sections and paragraphs.
 */

public class Section extends Element{

	private String _title = null;

	/** The section's list of subsections */
	private List<Section> _sections = new ArrayList<Section>();

	/** The paragraph's list of subsections */
	private List<Paragraph> _paragraphs = new ArrayList<Paragraph>();

	/**
	 * Section's constructor.
	 *
	 * @param title
	 */
	public Section(String title){

		_title = title;
	}

	public List<Section> getSectionArray(){
		
		return _sections;
	}

	public List<Paragraph> getParagraphArray(){
		
		return _paragraphs;
	}

	/**
	 * @return the section's title.
	 */
	public String getTitle(){

		return _title;
	}

	/**
	 * Sets the document's title.
	 *
	 * @param fileName 
	 */
	public void setTitle(String title){

		_title = title;
	}

	/**
	 * Adds a section to the end of the list of subsections.
	 *
	 * @param section
	 */
	public void addSection(Section section){

		this._sections.add(section);
	}

	/**
	 * Adds a section before a reference section to the list of subsections.
	 *
	 * @param referenceId
	 * @param section 
	 */
	public void addSection(int referenceId, Section section){

		this._sections.add(referenceId, section);
	}

	/**
	 * @param position - the section's position within the section list that holds it.
	 * @return the desired section.
	 */
	public Section getSection(int position){
		
		if (position >= this.getSectionArraySize() || position < 0)
			return null;

		return this._sections.get(position);
	}

	/**
	 * @return the section array's size.
	 */
	public int getSectionArraySize(){

		return (this._sections.size());
	}


	/**
	 * Adds a paragraph to the list of paragraphs.
	 *
	 * @param paragraph 
	 */
	public void addParagraph(Paragraph paragraph){

		this._paragraphs.add(paragraph);
	}

	/**
	 * Adds a paragraph before a reference paragraph to the list of paragraphs.
	 *
	 * @param referenceId
	 * @param paragraph 
	 */
	public void addParagraph(int referenceId, Paragraph paragraph){

		this._paragraphs.add(referenceId, paragraph);
	}

	/**
	 * @param position - the paragraph's position within the paragraph list that holds it.
	 * @return the desired paragraph.
	 */
	public Paragraph getParagraph(int position){

		if (position >= this.getParagraphArraySize() || position < 0)
			return null;

		return this._paragraphs.get(position);
	}


	/**
	 * @return the paragraph array's size.
	 */
	public int getParagraphArraySize(){

		return (this._paragraphs.size());
	}



	/*
	 * Function that counts the number of bytes of each section's title and 
	 * each paragraph's content recursively.
	 *
	 * @param byteCounter - variable of int type that stores the number of bytes counted through
	 *                      successive calls of the function.
	 * @return the bytes counted that far.
	 */

	public int sectionRec(int byteCounter){

		if (this.getParagraphArraySize() > 0){

			int j = 0;
			while (j < (this.getParagraphArraySize())){

				byteCounter += (this.getParagraph(j).getContent().length());
				j++;
			}
		}

		if (this.getSectionArraySize() > 0){

			int i = 0;
			while (i < (this.getSectionArraySize())){

				byteCounter += (this.getSection(i).getTitle().length());
				byteCounter = this.getSection(i).sectionRec(byteCounter);
				i++;
			}
		}

		return byteCounter;
	}

	/*
	 * Removes every child paragraph and subsection (recursively) of a given section that has a unique id from the _elements hashmap.
	 * 
	 * @param document - the document currently opened, passed by one of Remove* textui commands, needed to access the hashmap.
	 * @return the same document.
	 */
	
	public void sectionRecRemover(Document document){

		if (this.getParagraphArraySize() > 0){

			int j = 0;
			while (j < (this.getParagraphArraySize())){

				if (this._paragraphs.get(j).getId() != ""){
					document.removeElement(this._paragraphs.get(j).getId());
				}
				j++;
			}
		}

		if (this.getSectionArraySize() > 0){

			int i = 0;
			while (i < (this.getSectionArraySize())){

				if (this._sections.get(i).getId() != ""){
					document.removeElement(this._sections.get(i).getId());
				}

				this.getSection(i).sectionRecRemover(document);
				i++;
			}
		}

	}

	@Override
	@SuppressWarnings("nls")
	public String toString(){
		return "section";
	}


	/**
	 * @see Element#accept(Visitor v)
	 */
	@Override
	public void accept(Visitor visitor){
		visitor.visitSection(this);
	}

}
