/** @version $Id: AddAuthor.java,v 1.6 2015/11/27 03:12:51 ist163464 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Editor;
import edt.core.Document;
import edt.core.Author;

/**
 * §2.1.3.
 */
public class AddAuthor extends Command<Editor> {
	public AddAuthor(Editor receiver) {
		super(false, MenuEntry.ADD_AUTHOR, receiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		
		Document document = _receiver.getDocument();
		String name = IO.readString(Message.requestAuthorName());
		String email = IO.readString(Message.requestEmail());

		Author author = new Author(name, email);
		if (document.hasAuthor(author)){
			IO.println(Message.duplicateAuthor(name));
		} else{
			document.addAuthor(author);
			document.setState(true);
		}

		document.rewindIterator();
	
	}
}
