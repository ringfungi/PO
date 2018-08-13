package edt.core;

/**
 * A paragraph is a kind of text element that holds content in the form of text and belongs to a section.
 */

public class Paragraph extends Element{
	
	/** The paragraph's content (text)*/
	private String _content;
	
	/*
	 * Paragraph's constructor.
	 *
	 * @param content
	 */
	public Paragraph(String content){

		_content = content;
	}

	/**
	 *@return the paragraph's content.
	*/

	public String getContent(){

		return _content;
	}

	/*
	 * Edit's a paragraph content.
	 *
	 * @param content
	 */
	public void setContent(String content){

		_content = content;
	}

	@Override
	@SuppressWarnings("nls")
	public String toString(){
		return this.getContent();
	}

	/**
	 * @see Element#accept(Visitor v)
	 */
	@Override
	public void accept(Visitor visitor){

		visitor.visitParagraph(this);	
	}
}
