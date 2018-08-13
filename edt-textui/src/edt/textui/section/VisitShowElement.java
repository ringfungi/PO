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
 * Concrete visitor that visits an element and stores each of its subelements' information (id/title for
 *sections, content for paragraphs) in a string.
 */

public class VisitShowElement implements Visitor{

	private String _listOfElements = new String();
	
	public void visitSection(Section section){

		int i = 0;

		while (i < section.getParagraphArraySize()){

			section.getParagraph(i).accept(this);
			i++;
		}

		Iterator<Section> listIterator = section.getSectionArray().iterator();

		while(listIterator.hasNext()){

			Section subSection = listIterator.next();
			
			_listOfElements += Message.sectionIndexEntry(subSection.getId(), subSection.getTitle());

			_listOfElements += "\n";
 
			subSection.accept(this);		
		}
	}

	public void visitParagraph(Paragraph paragraph){
		_listOfElements += paragraph.getContent();
		_listOfElements += "\n";
	}

	@Override
	@SuppressWarnings("nls")
	public String toString(){
		return _listOfElements;
	}

}
