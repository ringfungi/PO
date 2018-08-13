/** @version $Id: EditParagraph.java,v 1.6 2015/11/27 03:12:51 ist163464 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Paragraph;
import edt.core.Document;
/**
 * §2.2.10.
 */
public class EditParagraph extends SectionCommand {
	public EditParagraph(Document document,Section section) {
		super(MenuEntry.EDIT_PARAGRAPH, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {

		int localId = IO.readInteger(Message.requestParagraphId());
		String content = IO.readString(Message.requestParagraphContent());

		Paragraph paragraphToEdit = _receiver.getParagraph(localId);

		if (paragraphToEdit == null){
			IO.println(Message.noSuchParagraph(localId));
			return;
		} else{
			paragraphToEdit.setContent(content);
		}

		_receiver2.setState(true);

	}

}
