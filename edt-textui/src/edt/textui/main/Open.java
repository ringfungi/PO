/** @version $Id: Open.java,v 1.5 2015/11/16 02:37:36 ist163464 Exp $ */
package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;
import java.io.FileNotFoundException;

import edt.core.Editor;
/**
 * Open existing document.
 */
public class Open extends Command<Editor> {
	public Open(Editor receiver) {
		super(false, MenuEntry.OPEN, receiver);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		
		String fileName = IO.readString(Message.openFile());
		try{
			_receiver.readFile(fileName);
		}
		catch(FileNotFoundException e){
			IO.println(Message.fileNotFound());
		}
		catch(ClassNotFoundException e){}
	}

}
