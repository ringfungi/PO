package edt.core;

/**
 * The visitor interface needed to implement the Visitor pattern. It defines the signature for two methods, one that visits
 * sections and another that visits paragraphs.
 */

public interface Visitor{

	public void visitSection(Section section);
	public void visitParagraph(Paragraph paragraph);

}
