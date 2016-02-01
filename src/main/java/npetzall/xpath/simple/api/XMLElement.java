package npetzall.xpath.simple.api;

import javax.xml.namespace.QName;

public interface XMLElement {

    boolean isStartElement();
    boolean isEndElement();

    QName getElementName();

    boolean hasText();
    String getText();

    boolean hasAttributeWithName(QName name);
    String getValueOfAttributeWithName(QName name);

    XMLElementSource getSource();
}
