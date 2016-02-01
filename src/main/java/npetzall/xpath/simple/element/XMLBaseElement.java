package npetzall.xpath.simple.element;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XMLElementSource;

import javax.xml.namespace.QName;

public abstract class XMLBaseElement implements XMLElement {

    protected final XMLElementSource xmlElementSource;
    protected final QName elementName;

    XMLBaseElement(XMLElementSource xmlElementSource, QName elementName) {
        this.xmlElementSource = xmlElementSource;
        this.elementName = elementName;
    }

    public XMLElementSource getSource() {
        return xmlElementSource;
    }

    public QName getElementName() {
        return elementName;
    }
}
