package npetzall.xpath.simple.element;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XMLElementSource;

import javax.xml.namespace.QName;

public class XMLElementBuilder {

    private final XMLElementSource xmlElementSource;

    private QName elementName;
    private String text = null;

    private XMLElementBuilder(XMLElementSource xmlElementSource) {
        this.xmlElementSource = xmlElementSource;
    }

    public static XMLElementBuilder builder(XMLElementSource xmlElementSource) {
        return new XMLElementBuilder(xmlElementSource);
    }

    public XMLElementBuilder elementName(QName elementName) {
        this.elementName = elementName;
        return this;
    }

    public XMLElementBuilder text(String text) {
        this.text = text;
        return this;
    }

    public XMLStartElement asStartElement() {
        return new XMLStartElement(xmlElementSource, elementName, text);
    }

    public XMLEndElement asEndElement() {
        return new XMLEndElement(xmlElementSource, elementName);
    }
}
