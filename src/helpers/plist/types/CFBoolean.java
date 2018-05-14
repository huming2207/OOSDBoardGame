package helpers.plist.types;

import helpers.exceptions.CFTypeMismatchException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CFBoolean implements CFType
{
    @Override
    public Element addElement(Object data, Document document) throws CFTypeMismatchException
    {
        if(!(data instanceof Boolean)) throw new CFTypeMismatchException("Type mismatch!");

        Boolean boolData = (Boolean)data;

        if(boolData) {
            return document.createElement("true");
        } else {
            return document.createElement("false");
        }
    }

    @Override
    public String getElementTag()
    {
        return null;
    }
}
