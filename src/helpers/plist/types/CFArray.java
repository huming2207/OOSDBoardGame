package helpers.plist.types;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

public class CFArray implements CFType
{

    @Override
    @SuppressWarnings("unchecked") // No idea for now, just let Java shut up...
    public Element addElement(Object data, Document document)
    {
        Element element = document.createElement("array");

        List<CFType> childrenList = (List<CFType>) data;

        for(CFType childData : childrenList) {

        }

        return element;
    }

    @Override
    public String getElementTag()
    {
        return null;
    }
}