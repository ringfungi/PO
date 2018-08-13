/** @version $Id: InsertSection.java,v 1.8 2015/11/27 03:12:51 ist163464 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Visitor;
import edt.core.Section;
import edt.core.Document;
/**
 * §2.2.5.
 */
public class InsertSection extends SectionCommand {
	public InsertSection(Document document, Section section) {
		super(MenuEntry.INSERT_SECTION, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {

		int referenceId = IO.readInteger(Message.requestSectionId());
		String title = IO.readString(Message.requestSectionTitle());

		
		Section subSection = _receiver.getSection(referenceId);
		Section newSection = new Section(title);

		if (subSection == null){
			_receiver.addSection(newSection);//mete no final
		} else{
			_receiver.addSection(referenceId, newSection);//mete antes da secção de referência
		}

		_receiver2.setState(true);

	}
}
