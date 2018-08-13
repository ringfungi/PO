/** @version $Id: RemoveSection.java,v 1.8 2015/11/30 00:47:03 ist163464 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Visitor;
import edt.core.Section;
import edt.core.Document;
/**
 * §2.2.7.
 */
public class RemoveSection extends SectionCommand {
	public RemoveSection(Document document, Section section) {
		super(MenuEntry.REMOVE_SECTION, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {

		int localId = IO.readInteger(Message.requestSectionId());

		Section subSection = _receiver.getSection(localId);

		if (subSection == null){
			IO.println(Message.noSuchSection(localId));
			return;
		} else{
			subSection.sectionRecRemover(_receiver2);
			if (subSection.getId() != ""){
				_receiver2.removeElement(subSection.getId());
			}
			_receiver.getSectionArray().remove(localId);
		}

		_receiver2.setState(true);
	}
 
}
