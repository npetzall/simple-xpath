package npetzall.xpath.simple.element;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XMLElementSource;

import javax.xml.namespace.QName;
import java.util.Map;

public class XMLStartElement extends XMLBaseElement implements XMLElement {

    private String text;
    private Map<QName,String> attributeMap = null;

    XMLStartElement(XMLElementSource xmlElementSource, QName elementName, String text, Map<QName,String> attributeMap) {
        super(xmlElementSource, elementName);
        this.text = text;
        this.attributeMap = attributeMap;
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
        return text != null;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public boolean hasAttributeWithName(QName name) {
        if ("*".equals(name.getNamespaceURI())) {
            return hasAttributeWithLocalPart(name.getLocalPart());
        }
        return attributeMap.containsKey(name);
    }

    private boolean hasAttributeWithLocalPart(String localPart) {
        for (QName name : attributeMap.keySet()) {
            if (name.getLocalPart().equals(localPart)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getValueOfAttributeWithName(QName name) {
        if ("*".equals(name.getNamespaceURI())) {
            return getValueOfAttributeWithLocalPart(name.getLocalPart());
        }
        return attributeMap.get(name);
    }

    private String getValueOfAttributeWithLocalPart(String localPart) {
        for (Map.Entry<QName,String> entry : attributeMap.entrySet()) {
            if (entry.getKey().getLocalPart().equals(localPart)) {
                return entry.getValue();
            }
        }
        return null;
    }

}
