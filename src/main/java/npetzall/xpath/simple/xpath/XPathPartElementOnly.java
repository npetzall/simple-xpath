package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.api.XMLElement;

import javax.xml.namespace.QName;

public class XPathPartElementOnly implements XPathPart {

    private final QName elementName;

    public XPathPartElementOnly(QName elementName) {
        this.elementName = elementName;
    }

    @Override
    public boolean matches(XMLElement xmlElement) {
        if ("*".equals(elementName.getNamespaceURI())) {
            return elementName.getLocalPart().equals(xmlElement.getElementName().getLocalPart());
        }
        return elementName.equals(xmlElement.getElementName());
    }
}
