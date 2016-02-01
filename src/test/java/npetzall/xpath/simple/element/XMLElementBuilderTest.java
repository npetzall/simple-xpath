package npetzall.xpath.simple.element;

import npetzall.xpath.simple.api.XMLElement;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;

import static org.assertj.core.api.Assertions.assertThat;

public class XMLElementBuilderTest {

    @Test
    public void canCreateBuilder() {
        XMLElementBuilder builder = XMLElementBuilder.builder(new XMLElementSourceDouble());
        assertThat(builder).isNotNull();
    }

    @Test
    public void canCreateStartElementWithElementName() {
        XMLElementBuilder builder = XMLElementBuilder.builder(new XMLElementSourceDouble());
        builder.elementName(new QName("hello"));
        XMLElement xmlElement = builder.asStartElement();
        assertThat(xmlElement.isStartElement()).isTrue();
        assertThat(xmlElement.isEndElement()).isFalse();
        assertThat(xmlElement.getElementName()).isEqualTo(new QName("hello"));
    }

    @Test
    public void canCreateStartElementWithElementNameAndText() {
        XMLElementBuilder builder = XMLElementBuilder.builder(new XMLElementSourceDouble());
        builder.elementName(new QName("hello"));
        builder.text("npetzall");
        XMLElement xmlElement = builder.asStartElement();
        assertThat(xmlElement.isStartElement()).isTrue();
        assertThat(xmlElement.isEndElement()).isFalse();
        assertThat(xmlElement.getElementName()).isEqualTo(new QName("hello"));
        assertThat(xmlElement.hasText()).isTrue();
        assertThat(xmlElement.getText()).isEqualTo("npetzall");
    }

    @Test
    public void canCreateEndElement() {
        XMLElementBuilder builder = XMLElementBuilder.builder(new XMLElementSourceDouble());
        builder.elementName(new QName("hello"));
        XMLElement xmlElement = builder.asEndElement();
        assertThat(xmlElement.isStartElement()).isFalse();
        assertThat(xmlElement.isEndElement()).isTrue();
        assertThat(xmlElement.getElementName()).isEqualTo(new QName("hello"));
    }
}
