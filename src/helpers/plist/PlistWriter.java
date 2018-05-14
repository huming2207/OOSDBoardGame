package helpers.plist;

import helpers.exceptions.CFTypeMismatchException;
import helpers.plist.types.CFType;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Writes all the stuff from
 *
 * @author Ming Hu
 */
public class PlistWriter
{
    private Document document;
    private Element rootElement;
    private CFType nextType;

    private static PlistWriter writerInstance = new PlistWriter();

    public static PlistWriter getInstance()
    {
        return writerInstance;
    }

    private PlistWriter()
    {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder documentBuilder = docBuilderFactory.newDocumentBuilder();
            this.document = documentBuilder.newDocument();

            // Set <!DOCTYPE>
            DOMImplementation domImplementation = this.document.getImplementation();
            DocumentType documentType = domImplementation.createDocumentType("plist",
                    "-//Apple//DTD PLIST 1.0//EN", "http://www.apple.com/DTDs/PropertyList-1.0.dtd");

            // Set root element
            this.rootElement = this.document.createElement("plist");
            Attr attribute = document.createAttribute("version");
            attribute.setValue("1.0");
            this.rootElement.setAttributeNode(attribute);

            this.document.appendChild(documentType);
            this.document.appendChild(this.rootElement);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void setNextType(CFType type)
    {
        this.nextType = type;
    }

    public CFType getNextType()
    {
        return nextType;
    }

    public void add(Object data) throws CFTypeMismatchException
    {
        this.nextType.addElement(data, this.document);
    }


}
