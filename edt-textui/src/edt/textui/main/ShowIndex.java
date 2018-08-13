/** @version $Id: ShowIndex.java,v 1.7 2015/11/26 03:42:50 ist163464 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Document;
import edt.core.Editor;

/**
 * §2.1.4.
 */
public class ShowIndex extends Command<Editor> {
	public ShowIndex(Editor receiver) {
		super(false, MenuEntry.SHOW_INDEX, receiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {

		Document document = _receiver.getDocument();
		
		if (document.getTitle() == null){
			IO.println("{}");
			return;
		}
			
		IO.println("{" + document.getTitle() + "}");

		int i = 0;
		while (i < (document.getSectionArraySize())){
			String tempTitle = document.getSection(i).getTitle();
			String tempName = document.getSection(i).getId();
			IO.println(Message.sectionIndexEntry(tempName, tempTitle));
			i++;
		}
	}
}
