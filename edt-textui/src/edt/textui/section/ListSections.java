/** @version $Id: ListSections.java,v 1.9 2015/11/30 19:29:33 ist163464 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import java.util.*;
import edt.core.Visitor;
import edt.core.Section;
import edt.core.Document;
/**
 * §2.2.2.
 */
public class ListSections extends SectionCommand {
	public ListSections(Document document, Section section) {
		super(MenuEntry.LIST_SECTIONS, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		
		Visitor listSections = new VisitListElement();
	
		_receiver.accept(listSections);
	
		if (listSections.toString().length() > 0){		
			String s = listSections.toString().substring(0, listSections.toString().length() - 1);
			IO.println(s);
		}

	}
}
