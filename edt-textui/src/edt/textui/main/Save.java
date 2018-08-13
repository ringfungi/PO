/** @version $Id: Save.java,v 1.10 2015/12/01 03:02:43 ist163464 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.core.Editor;
import edt.core.Document;

/**
 * Save to file under current name (if unnamed, query for name).
 */
public class Save extends Command<Editor> {
	public Save(Editor receiver) {
		super(false, MenuEntry.SAVE, receiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {

		String fileName;
		Document document = _receiver.getDocument();

		//If the document wasn't changed since the last save, nothing is done.
		if (!document.getState()){
			return;
		}
	
		//If the document already has an associated file, then get it;
		//otherwise, prompt the user to enter a file name.
		if (document.getFile() != null){
			fileName = document.getFile();
		} else{
			fileName = IO.readString(Message.newSaveAs());
		}
		
		//The file name is asssociated with the document only if the user actually
		//inputs a string, in which case the setState variable is reset to false.
		if (fileName.length() > 0){
			document.setFile(fileName);
			document.setState(false);
		}

		_receiver.writeFile(fileName);

	}
}
