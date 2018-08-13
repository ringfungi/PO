/** @version $Id: MenuBuilder.java,v 1.7 2015/11/16 02:37:36 ist163464 Exp $ */
package edt.textui.section;

import ist.po.ui.Command;
import ist.po.ui.Menu;

import edt.core.Editor;
import edt.core.Document;
import edt.core.Section;
import edt.core.Paragraph;
import edt.core.Element;

/**
 * Menu builder for search operations.
 */
public class MenuBuilder {
  public static void menuFor(Document document, Section section) {
    Menu menu = new Menu(MenuEntry.TITLE,
        new Command<?>[] { //
            new ChangeTitle(document, section), //
            new ListSections(document, section), //
            new ShowContent(document, section), //
            new SelectSection(document,section), //
            new InsertSection(document, section), //
            new NameSection(document, section), //
            new RemoveSection(document, section), //
            new InsertParagraph(document, section), //
            new NameParagraph(document, section), //
            new EditParagraph(document, section), //
            new RemoveParagraph(document, section), //
    });
    menu.open();
  }
}
