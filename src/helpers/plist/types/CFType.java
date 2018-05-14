package helpers.plist.types;

import helpers.exceptions.CFTypeMismatchException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public interface CFType
{
    Element addElement(Object data, Document document) throws CFTypeMismatchException;
    String getElementTag();
}
