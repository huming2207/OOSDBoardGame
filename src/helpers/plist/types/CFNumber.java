package helpers.plist.types;

import helpers.exceptions.CFTypeMismatchException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class CFNumber implements CFType
{

    @Override
    public Element addElement(Object data, Document document) throws CFTypeMismatchException
    {
        if(!(data instanceof Number))  throw new CFTypeMismatchException("Type mismatch!");

        Number numberObject = (Number)data;
        String numberString = numberObject.toString();

        Element element;

        // Just a retarded way to test if it's a real number (float/double type) or integer...
        if(numberString.contains(".")) {
            element = document.createElement("real");
        } else {
            element = document.createElement("integer");
        }

        element.appendChild(document.createTextNode(numberString));

        return element;
    }

    @Override
    public String getElementTag()
    {
        return null;
    }
}
