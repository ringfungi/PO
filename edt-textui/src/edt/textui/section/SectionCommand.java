/** @version $Id: SectionCommand.java,v 1.6 2015/11/30 03:04:09 ist163464 Exp $ */
package edt.textui.section;

import ist.po.ui.Command;

import edt.core.Section;
import edt.core.Document;
/**
 * Superclass of all section-context commands.
 */
public abstract class SectionCommand extends Command<Section> {
  
  	/** Declaration of the second receiver: the document-context one */
	protected Document _receiver2;
	
	public SectionCommand(String title, Section section, Document document) {
		super(title, section);

		/** Initialization of the second receiver */
		_receiver2 = document;
	}

}
