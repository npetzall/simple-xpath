package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.element.XMLElementBuilder;
import npetzall.xpath.simple.element.XMLElementSourceDouble;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;

import static org.assertj.core.api.Assertions.assertThat;

public class XPathPartElementOnlyTest {

    @Test
    public void canCreate() {
        XPathPart xPathPart = new XPathPartElementOnly(new QName("*","root"));
        assertThat(xPathPart).isNotNull();
    }

    @Test
    public void canBeMatchedAgainsAnXMLElement() {
        XPathPart xPathPart = new XPathPartElementOnly(new QName("root"));
        XMLElement xmlElement = XMLElementBuilder
                .builder(new XMLElementSourceDouble())
                .elementName(new QName("","root"))
                .asStartElement();
        assertThat(xPathPart.matches(xmlElement)).isTrue();
    }

    @Test
    public void wontMatchWhenXMLElementIsntCorrect() {
        XPathPart xPathPart = new XPathPartElementOnly(new QName("*","root"));
        XMLElement xmlElement = XMLElementBuilder
                .builder(new XMLElementSourceDouble())
                .elementName(new QName("","hello"))
                .asStartElement();
        assertThat(xPathPart.matches(xmlElement)).isFalse();
    }

    @Test
    public void canBeMatchedAgainsAnXMLElementWithNamespaceUsingWildcard() {
        XPathPart xPathPart = new XPathPartElementOnly(new QName("*","root"));
        XMLElement xmlElement = XMLElementBuilder
                .builder(new XMLElementSourceDouble())
                .elementName(new QName("ns","root"))
                .asStartElement();
        assertThat(xPathPart.matches(xmlElement)).isTrue();
    }
}
