/** @version $Id: Edit.java,v 1.8 2015/11/27 03:12:51 ist163464 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Document;
import edt.core.Section;
import edt.core.Editor;

/**
 * §2.3.1.
 */
public class Edit extends Command<Editor> {
	public Edit(Editor receiver) {
		super(false, MenuEntry.OPEN_DOCUMENT_EDITOR, receiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		Document document = _receiver.getDocument();

		edt.textui.section.MenuBuilder.menuFor(document, document);
  	}
}
