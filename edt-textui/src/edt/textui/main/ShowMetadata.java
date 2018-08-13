/** @version $Id: ShowMetadata.java,v 1.7 2015/11/23 04:00:35 ist163464 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Document;
import edt.core.Editor;
import edt.core.Author;
/**
 * §2.1.2.
 */
public class ShowMetadata extends Command<Editor> {
	public ShowMetadata(Editor receiver) {
		super(false, MenuEntry.SHOW_METADATA, receiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {

		Document document = _receiver.getDocument();

		if (document.getTitle() == null)
			document.setTitle("");

		IO.println(Message.documentTitle(document.getTitle()));

		Author tempAuthor = document.getAuthor();

		while (tempAuthor != null){
			IO.println(Message.author(tempAuthor.getName(), tempAuthor.getEmail()));
			tempAuthor = document.getAuthor();
		}

		document.rewindIterator();
		
		IO.println(Message.documentSections(document.countTopSections()));

		IO.println(Message.documentBytes(document.getDocumentSize()));

		IO.println(Message.documentIdentifiers(document.countHashMapKeys()));
	}

}
