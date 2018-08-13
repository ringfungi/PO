/** @version $Id: MenuBuilder.java,v 1.7 2015/11/23 04:00:35 ist163464 Exp $ */
package edt.textui.main;

import ist.po.ui.Command;
import ist.po.ui.Menu;

import edt.core.Editor;
import edt.core.Document;

public abstract class MenuBuilder {
  public static void menuFor(Editor editor, Document document) {
    Menu menu = new Menu(MenuEntry.TITLE,
        new Command<?>[] { //
            new New(editor), //
            new Open(editor), //
            new Save(editor), //
            new ShowMetadata(editor), //
            new AddAuthor(editor), //
            new ShowIndex(editor), //
            new ShowTextElement(document), //
            new Edit(editor), //
    });
    menu.open();
  }
}
