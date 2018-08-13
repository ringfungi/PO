/** @version $Id: InsertParagraph.java,v 1.7 2015/11/27 03:12:51 ist163464 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Visitor;
import edt.core.Section;
import edt.core.Paragraph;
import edt.core.Document;
/**
 * §2.2.8.
 */
public class InsertParagraph extends SectionCommand {
	public InsertParagraph(Document document, Section section) {
		super(MenuEntry.INSERT_PARAGRAPH, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {

		int referenceId = IO.readInteger(Message.requestParagraphId());
		String content = IO.readString(Message.requestParagraphContent());
		Paragraph newParagraph = new Paragraph(content);		

		if (_receiver.getParagraph(referenceId) == null){
			_receiver.addParagraph(newParagraph);
		} else{
			_receiver.addParagraph(referenceId, newParagraph);
		}

		_receiver2.setState(true);
		
	}

}
