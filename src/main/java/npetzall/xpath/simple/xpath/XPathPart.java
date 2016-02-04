package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.api.XMLElement;

import javax.xml.namespace.QName;

public class XPathPart {

    private final QName elementName;

    public XPathPart(QName elementName) {
        this.elementName = elementName;
    }

    public boolean matches(XMLElement xmlElement) {
        if ("*".equals(elementName.getNamespaceURI())) {
            return elementName.getLocalPart().equals(xmlElement.getElementName().getLocalPart());
        }
        return elementName.equals(xmlElement.getElementName());
    }
}
