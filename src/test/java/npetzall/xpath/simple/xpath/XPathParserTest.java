package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.element.XMLElementBuilder;
import npetzall.xpath.simple.element.XMLElementSourceDouble;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import java.util.List;

import static npetzall.xpath.simple.helper.NamespaceMap.namespace;
import static org.assertj.core.api.Assertions.assertThat;

public class XPathParserTest {

    @Test
    public void parseSinglePathElement() {
        List<XPathPart> xPathParts = new XPathParser().parse("/root");
        assertThat(xPathParts.size()).isEqualTo(1);
        assertThat(xPathParts.get(0).matches(XMLElementBuilder.builder(new XMLElementSourceDouble())
                .elementName(new QName("root"))
        .asStartElement())).isTrue();
    }

    @Test
    public void parseTwoPathElements() {
        List<XPathPart> xPathParts = new XPathParser().parse("/root/one");
        assertThat(xPathParts.size()).isEqualTo(2);
        assertThat(xPathParts.get(0).matches(XMLElementBuilder.builder(new XMLElementSourceDouble())
                .elementName(new QName("root"))
                .asStartElement())).isTrue();
        assertThat(xPathParts.get(1).matches(XMLElementBuilder.builder(new XMLElementSourceDouble())
                .elementName(new QName("one"))
                .asStartElement())).isTrue();
    }

    @Test
    public void parseTwoElementsWithNamespaces() {
        List<XPathPart> xPathParts = new XPathParser().parse("/ns0:root/ns1:one", namespace("ns0","zero").add("ns1","one"));
        assertThat(xPathParts.get(0).matches(XMLElementBuilder.builder(new XMLElementSourceDouble())
                .elementName(new QName("zero","root"))
                .asStartElement())).isTrue();
        assertThat(xPathParts.get(1).matches(XMLElementBuilder.builder(new XMLElementSourceDouble())
                .elementName(new QName("one","one"))
                .asStartElement())).isTrue();
    }

    @Test
    public void parseTwoElementsWithWildcards() {
        List<XPathPart> xPathParts = new XPathParser().parse("/*:root/*:one");
        assertThat(xPathParts.get(0).matches(XMLElementBuilder.builder(new XMLElementSourceDouble())
                .elementName(new QName("zero", "root"))
                .asStartElement())).isTrue();
        assertThat(xPathParts.get(1).matches(XMLElementBuilder.builder(new XMLElementSourceDouble())
                .elementName(new QName("one", "one"))
                .asStartElement())).isTrue();
    }

    @Test(expectedExceptions = XPathNotSupportedYetException.class)
    public void abstractPathsNotAllowedYet() {
        new XPathParser().parse("//one");
    }
}
