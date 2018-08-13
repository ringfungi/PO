/** @version $Id: SelectSection.java,v 1.9 2015/11/30 03:04:09 ist163464 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Document;
/**
 * §2.2.4.
 */
public class SelectSection extends SectionCommand {
	public SelectSection(Document document, Section section) {
		super(MenuEntry.SELECT_SECTION, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException  {

		int localId = IO.readInteger(Message.requestSectionId());

		Section section = _receiver.getSection(localId);

		if (section == null){
			IO.println(Message.noSuchSection(localId));
			return;
		}
			
		IO.println(Message.newActiveSection(localId));

		edt.textui.section.MenuBuilder.menuFor(_receiver2, section);
	}
}
