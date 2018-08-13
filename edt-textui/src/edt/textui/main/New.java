/** @version $Id: New.java,v 1.6 2015/11/16 02:37:36 ist163464 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Editor;
import edt.core.Document;
/**
 * Open a new document.
 */
public class New extends Command<Editor> {
	public New(Editor receiver) {
		super(false, MenuEntry.NEW, receiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		
		_receiver.newDoc();
	}

}
