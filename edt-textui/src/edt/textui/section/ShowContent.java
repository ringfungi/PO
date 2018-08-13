/** @version $Id: ShowContent.java,v 1.9 2015/11/27 20:36:11 ist163464 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Visitor;
import edt.core.Section;
import edt.core.Document;
/**
 * §2.2.3.
 */
public class ShowContent extends SectionCommand {
	public ShowContent(Document document, Section section) {
		super(MenuEntry.SHOW_CONTENT, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {

		if (_receiver.toString() == "section"){
			IO.println(Message.sectionIndexEntry(_receiver.getId(), _receiver.getTitle()));
		}else {
			if (_receiver.getTitle() != null)
				IO.println("{" + _receiver.getTitle() + "}");
			else
				IO.println("{" + "}");
		}

		if (_receiver.getSectionArraySize() < 0 || _receiver.getParagraphArraySize() < 0)
			return;

		Visitor listElements = new VisitShowElement();

		_receiver.accept(listElements);

		if (listElements.toString().length() > 0){		
			String s = listElements.toString().substring(0, listElements.toString().length() - 1);
			IO.println(s);
		}

	}
}
