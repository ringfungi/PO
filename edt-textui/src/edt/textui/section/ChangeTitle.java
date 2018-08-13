/** @version $Id: ChangeTitle.java,v 1.8 2015/11/27 03:12:51 ist163464 Exp $ */
package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Section;
import edt.core.Document;
/**
 * §2.2.1.
 */
public class ChangeTitle extends SectionCommand {
	public ChangeTitle(Document document, Section section) {
		super(MenuEntry.CHANGE_TITLE, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {

		String title = IO.readString(Message.requestSectionTitle());
		
		_receiver.setTitle(title);
		_receiver2.setState(true);

	}
}
