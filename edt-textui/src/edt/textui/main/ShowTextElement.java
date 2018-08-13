/** @version $Id: ShowTextElement.java,v 1.9 2015/11/30 19:29:33 ist163464 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.textui.section.VisitShowElement;
import edt.core.Visitor;
import edt.core.Element;
import edt.core.Section;
import edt.core.Paragraph;
import edt.core.Document;
import edt.core.Editor;

/**
 * §2.1.5.
 */
public class ShowTextElement extends Command<Document> {
	public ShowTextElement(Document receiver) {
		super(MenuEntry.SHOW_TEXT_ELEMENT, receiver);
}

	@Override
	public final void execute() throws DialogException, IOException {

		String id = IO.readString(Message.requestElementId());
		
		Element element = _receiver.getElement(id);
	
		if (element == null){
			IO.println(Message.noSuchTextElement(id));
			return;
		}

		Visitor showElement = new VisitShowElement();	
		
		element.accept(showElement);

		if (element.toString() == "section"){
			Section section = (Section) element;

		IO.println(Message.sectionIndexEntry(element.getId(), section.getTitle()));
	}

	if (showElement.toString().length() > 0){		
		String s = showElement.toString().substring(0, showElement.toString().length() - 1);
		IO.println(s);
	}
	
  }
}
