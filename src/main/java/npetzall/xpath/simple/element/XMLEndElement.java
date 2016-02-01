package npetzall.xpath.simple.element;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XMLElementSource;

import javax.xml.namespace.QName;

public class XMLEndElement extends XMLBaseElement implements XMLElement {
    XMLEndElement(XMLElementSource xmlElementSource, QName elementName) {
        super(xmlElementSource, elementName);
    }


    @Override
    public boolean isStartElement() {
        return false;
    }

    @Override
    public boolean isEndElement() {
        return true;
    }

    @Override
    public boolean hasText() {
        return false;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public boolean hasAttributeWithName(QName name) {
        return false;
    }

    @Override
    public String getValueOfAttributeWithName(QName name) {
        return null;
    }
}
