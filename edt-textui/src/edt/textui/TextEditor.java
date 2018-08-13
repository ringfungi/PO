/** @version $Id: TextEditor.java,v 1.8 2015/11/16 02:37:36 ist163464 Exp $ */
package edt.textui;

import static ist.po.ui.Dialog.IO;

import java.io.IOException;

import edt.core.Editor;
import edt.core.Document;

/**
 * Class that starts the application's textual interface.
 */
public class TextEditor {
	public static void main(String[] args) throws IOException{

		Document myDocument = new Document(null);
		Editor myEditor = new Editor(myDocument);
		String datafile = System.getProperty("import"); //$NON-NLS-1$
		if (datafile != null) {

			myDocument.fileParser(datafile, myDocument);
		}
		edt.textui.main.MenuBuilder.menuFor(myEditor, myDocument);
		IO.closeDown();
	}
}
