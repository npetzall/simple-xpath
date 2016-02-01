package npetzall.xpath.simple.element;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XMLElementSource;

import javax.xml.namespace.QName;

public class XMLStartElement extends XMLBaseElement implements XMLElement {

    private String text;

    XMLStartElement(XMLElementSource xmlElementSource, QName elementName, String text) {
        super(xmlElementSource, elementName);
        this.text = text;
    }

    @Override
    public boolean isStartElement() {
        return true;
    }

    @Override
    public boolean isEndElement() {
        return false;
    }

    @Override
    public boolean hasText() {
        return text != null && !text.isEmpty();
    }

    @Override
    public String getText() {
        return text;
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
