/** @version $Id: RemoveParagraph.java,v 1.6 2015/11/27 03:12:51 ist163464 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Paragraph;
import edt.core.Document;
/**
 * §2.2.11.
 */
public class RemoveParagraph extends SectionCommand {
	public RemoveParagraph(Document document, Section section) {
		super(MenuEntry.REMOVE_PARAGRAPH, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {

		int localId = IO.readInteger(Message.requestParagraphId());

		Paragraph paragraph = _receiver.getParagraph(localId);

		if (paragraph == null){
			IO.println(Message.noSuchParagraph(localId));
			return;
		} else{
			if (paragraph.getId() != ""){
				_receiver2.removeElement(paragraph.getId());
			}
			_receiver.getParagraphArray().remove(localId);
		}

		_receiver2.setState(true);
	}

}
