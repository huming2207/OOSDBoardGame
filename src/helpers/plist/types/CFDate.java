package helpers.plist.types;

import helpers.exceptions.CFTypeMismatchException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class CFDate implements CFType
{

    @Override
    public Element addElement(Object data, Document document) throws CFTypeMismatchException
    {
        if(!(data instanceof Date))  throw new CFTypeMismatchException("Type mismatch!");

        // Set to Apple UTC-style ISO8601 time string (with "Z" at the end)
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        dateFormat.setTimeZone(timeZone);

        // Convert date object to ISO string
        String isoTimeString = dateFormat.format((Date)data);

        Element element = document.createElement("data");
        element.appendChild(document.createTextNode(isoTimeString));

        return element;
    }

    @Override
    public String getElementTag()
    {
        return null;
    }
}
