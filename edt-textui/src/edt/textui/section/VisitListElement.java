package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;

import edt.core.Element;
import edt.core.Section;
import edt.core.Paragraph;
import edt.core.Document;
import edt.core.Visitor;

/**
 * Concrete visitor that visits an element (in this case, only the method that visits sections is implemented) and successively
 * concatenates a string that stores the id/title of each subsection that's visited.
*/

public class VisitListElement implements Visitor{

	private String _listOfSections = new String();

	public void visitSection(Section section){
		
		int i = 0;
		while(i < section.getSectionArraySize()){

			Section subSection = section.getSection(i);

			_listOfSections += Message.sectionIndexEntry(subSection.getId(), subSection.getTitle());

			_listOfSections += "\n";

			i++;	
			subSection.accept(this);

		}
	}

	public void visitParagraph(Paragraph paragraph){
		
		return;
	}

	@Override
	@SuppressWarnings("nls")
	public String toString(){
		return _listOfSections;
	}

}
