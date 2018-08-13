/** @version $Id: NameSection.java,v 1.10 2015/11/27 22:01:35 ist163464 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Visitor;
import edt.core.Section;
import edt.core.Document;
/**
 * §2.2.6.
 */
public class NameSection extends SectionCommand {
	public NameSection(Document document, Section section) {
		super(MenuEntry.NAME_SECTION, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {

		int localId = IO.readInteger(Message.requestSectionId());
		String id = IO.readString(Message.requestUniqueId());

		Section sectionToName = _receiver.getSection(localId);

		if (sectionToName == null){
			IO.println(Message.noSuchSection(localId));
			return;
		}

		if (!(_receiver2.searchKeyPossiblyReplaceValue(id, sectionToName))){
			if (sectionToName.getId().length() > 0)
				IO.println(Message.sectionNameChanged());

			String oldId = sectionToName.getId();
			_receiver2.removeElement(oldId);
			_receiver2.putElement(id, sectionToName);
			sectionToName.setId(id);
		}

		_receiver2.setState(true);

	}
}
