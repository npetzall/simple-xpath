package npetzall.xpath.simple.element;

import npetzall.xpath.simple.api.XMLElementSource;

import javax.xml.namespace.QName;
import java.util.HashMap;

public class XMLElementBuilder {

    private final XMLElementSource xmlElementSource;

    private QName elementName;
    private String text = null;
    private HashMap<QName, String> attributeMap = new HashMap<>();

    private XMLElementBuilder(XMLElementSource xmlElementSource) {
        this.xmlElementSource = xmlElementSource;
    }

    public static XMLElementBuilder builder(XMLElementSource xmlElementSource) {
        return new XMLElementBuilder(xmlElementSource);
    }

    public XMLElementBuilder elementName(QName elementName) {
        this.elementName = elementName;
        clear();
        return this;
    }

    private void clear() {
        text = null;
        attributeMap.clear();
    }

    public XMLElementBuilder text(String text) {
        this.text = text != null && !text.trim().isEmpty() ? text : null;
        return this;
    }

    public XMLElementBuilder addAttribute(QName attributeName, String attributeValue) {
        attributeMap.put(attributeName,attributeValue);
        return this;
    }

    public XMLStartElement asStartElement() {
        return new XMLStartElement(xmlElementSource, elementName, text, attributeMap);
    }

    public XMLEndElement asEndElement() {
        return new XMLEndElement(xmlElementSource, elementName);
    }

}
