package helpers.plist.types;

import helpers.exceptions.CFTypeMismatchException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sun.misc.BASE64Encoder;


public class CFData implements CFType
{
    @Override
    public Element addElement(Object data, Document document) throws CFTypeMismatchException
    {
        if(data instanceof Byte[]) throw new CFTypeMismatchException("Type mismatch!");

        String base64Text = new BASE64Encoder().encode((byte[])data);

        Element element = document.createElement("data");
        element.appendChild(document.createTextNode(base64Text));

        return element;
    }

    @Override
    public String getElementTag()
    {
        return null;
    }
}
