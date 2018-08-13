/** @version $Id: NameParagraph.java,v 1.7 2015/11/27 22:01:35 ist163464 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Paragraph;
import edt.core.Document;
/**
 * §2.2.9.
 */
public class NameParagraph extends SectionCommand {
	public NameParagraph(Document document, Section section) {
		super(MenuEntry.NAME_PARAGRAPH, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {

		int localId = IO.readInteger(Message.requestParagraphId());
		String id = IO.readString(Message.requestUniqueId());

		Paragraph paragraphToName = _receiver.getParagraph(localId);

		if (paragraphToName == null){
			IO.println(Message.noSuchParagraph(localId));
			return;
		}

		if (!(_receiver2.searchKeyPossiblyReplaceValue(id, paragraphToName))){
			if (paragraphToName.getId().length() > 0)
				IO.println(Message.paragraphNameChanged());	

			String oldId = paragraphToName.getId();
			_receiver2.removeElement(oldId);
			_receiver2.putElement(id, paragraphToName);
			paragraphToName.setId(id);
		}

		_receiver2.setState(true);
	}
}
