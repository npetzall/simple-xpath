package npetzall.xpath.simple.element;

import npetzall.xpath.simple.api.XMLElement;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;

import static org.assertj.core.api.Assertions.assertThat;

public class XMLElementBuilderTest {

    private XMLStartElement createStartElementWithOnlyName() {
        XMLElementBuilder builder = XMLElementBuilder.builder(new XMLElementSourceDouble());
        builder.elementName(new QName("hello"));
        return builder.asStartElement();
    }

    private XMLStartElement creatStartElementWithEmptyText() {
        XMLElementBuilder builder = XMLElementBuilder.builder(new XMLElementSourceDouble());
        builder.elementName(new QName("hello"));
        builder.text("");
        return builder.asStartElement();
    }

    private XMLStartElement createStartElementWithNameTextAndAttribute() {
        XMLElementBuilder builder = XMLElementBuilder.builder(new XMLElementSourceDouble());
        builder.elementName(new QName("hello"));
        builder.text("npetzall");
        builder.addAttribute(new  QName("id"), "123");
        builder.addAttribute(new  QName("name"), "nisse");
        return builder.asStartElement();
    }

    private XMLStartElement createStartElementWithNameTextAndAttributeWithNamespace() {
        XMLElementBuilder builder = XMLElementBuilder.builder(new XMLElementSourceDouble());
        builder.elementName(new QName("ns","hello"));
        builder.text("npetzall");
        builder.addAttribute(new  QName("ns","id"), "123");
        builder.addAttribute(new  QName("ns","name"), "nisse");
        return builder.asStartElement();
    }

    @Test
    public void startElementIsStartElementAndNotEndElement() {
        XMLElement xmlElement = createStartElementWithNameTextAndAttribute();
        assertThat(xmlElement.isStartElement()).isTrue();
        assertThat(xmlElement.isEndElement()).isFalse();
    }

    @Test
    public void startElementGetName() {
        XMLElement xmlElement = createStartElementWithNameTextAndAttribute();
        assertThat(xmlElement.getElementName()).isEqualTo(new QName("hello"));
    }

    @Test
    public void starElementGetText() {
        XMLElement xmlElement = createStartElementWithNameTextAndAttribute();
        assertThat(xmlElement.hasText()).isTrue();
        assertThat(xmlElement.getText()).isEqualTo("npetzall");
    }

    @Test
    public void startElementWithEmptyTextShouldReturnFalseOnHasTextAndNullOnGetText() {
        XMLElement xmlElement = creatStartElementWithEmptyText();
        assertThat(xmlElement.hasText()).isFalse();
        assertThat(xmlElement.getText()).isNull();
    }


    @Test
    public void startElementMissingTextIsNull() {
        XMLElement xmlElement = createStartElementWithOnlyName();
        assertThat(xmlElement.hasText()).isFalse();
        assertThat(xmlElement.getText()).isNull();
    }

    @Test
    public void startElementGetAttributes() {
        XMLElement xmlElement = createStartElementWithNameTextAndAttribute();
        assertThat(xmlElement.hasAttributeWithName(new QName("id"))).isTrue();
        assertThat(xmlElement.getValueOfAttributeWithName(new QName("id"))).isEqualTo("123");
        assertThat(xmlElement.hasAttributeWithName(new QName("name"))).isTrue();
        assertThat(xmlElement.getValueOfAttributeWithName(new QName("name"))).isEqualTo("nisse");
    }

    @Test
    public void startElementGetAttributesWithNamespace() {
        XMLElement xmlElement = createStartElementWithNameTextAndAttributeWithNamespace();
        assertThat(xmlElement.hasAttributeWithName(new QName("id"))).isFalse();
        assertThat(xmlElement.getValueOfAttributeWithName(new QName("id"))).isNull();
        assertThat(xmlElement.hasAttributeWithName(new QName("name"))).isFalse();
        assertThat(xmlElement.getValueOfAttributeWithName(new QName("name"))).isNull();
        assertThat(xmlElement.hasAttributeWithName(new QName("ns","id"))).isTrue();
        assertThat(xmlElement.getValueOfAttributeWithName(new QName("ns","id"))).isEqualTo("123");
        assertThat(xmlElement.hasAttributeWithName(new QName("ns","name"))).isTrue();
        assertThat(xmlElement.getValueOfAttributeWithName(new QName("ns","name"))).isEqualTo("nisse");
        assertThat(xmlElement.hasAttributeWithName(new QName("*","id"))).isTrue();
        assertThat(xmlElement.getValueOfAttributeWithName(new QName("*","id"))).isEqualTo("123");
        assertThat(xmlElement.hasAttributeWithName(new QName("*","name"))).isTrue();
        assertThat(xmlElement.getValueOfAttributeWithName(new QName("*","name"))).isEqualTo("nisse");
    }

    @Test
    public void startElementGetMissingAttributesIsHasAttributeFalseAndGetAttributeValueNull() {
        XMLElement xmlElement = createStartElementWithNameTextAndAttribute();
        assertThat(xmlElement.hasAttributeWithName(new QName("missing"))).isFalse();
        assertThat(xmlElement.getValueOfAttributeWithName(new QName("missing"))).isNull();
        assertThat(xmlElement.hasAttributeWithName(new QName("*","missing"))).isFalse();
        assertThat(xmlElement.getValueOfAttributeWithName(new QName("*","missing"))).isNull();
    }

    @Test
    public void startElementGetAttributesUsingWildcardNamespace() {
        XMLElement xmlElement = createStartElementWithNameTextAndAttribute();
        assertThat(xmlElement.hasAttributeWithName(new QName("id"))).isTrue();
        assertThat(xmlElement.getValueOfAttributeWithName(new QName("id"))).isEqualTo("123");
        assertThat(xmlElement.hasAttributeWithName(new QName("*","name"))).isTrue();
        assertThat(xmlElement.getValueOfAttributeWithName(new QName("*","name"))).isEqualTo("nisse");
    }

    @Test
    public void endElement() {
        XMLElementBuilder builder = XMLElementBuilder.builder(new XMLElementSourceDouble());
        builder.elementName(new QName("hello"));
        XMLElement xmlElement = builder.asEndElement();
        assertThat(xmlElement.isStartElement()).isFalse();
        assertThat(xmlElement.isEndElement()).isTrue();
        assertThat(xmlElement.getElementName()).isEqualTo(new QName("hello"));
        assertThat(xmlElement.hasText()).isFalse();
        assertThat(xmlElement.getText()).isNull();
        assertThat(xmlElement.hasAttributeWithName(new QName("name"))).isFalse();
        assertThat(xmlElement.getValueOfAttributeWithName(new QName("name"))).isNull();
    }
}
