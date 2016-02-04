package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.element.XMLElementBuilder;
import npetzall.xpath.simple.element.XMLElementSourceDouble;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;

import static npetzall.xpath.simple.helper.NamespaceMap.namespace;
import static org.assertj.core.api.Assertions.assertThat;

public class XPathTest {

    @Test
    public void createXPathWithNoNamespace() {
        XPath xpath = XPath.parse("/root/one/two");
        assertThat(xpath).isNotNull();
    }

    @Test
    public void canMatchXMLElementAtDepth() {
        XPath xPath = XPath.parse("/root/one/two");
        boolean isAmatch = xPath.matches(0, XMLElementBuilder.builder(new XMLElementSourceDouble())
        .elementName(new QName("root"))
        .asStartElement());
        assertThat(isAmatch).isTrue();
    }

    @Test
    public void canMatchXMLElementWithNamespaceAtDepth() {
        XPath xPath = XPath.parse("/ns1:root/ns2:one/ns3:two",
                namespace("ns1","one")
                        .add("ns2","two")
                        .add("ns3","three"));
        boolean isAmatch = xPath.matches(2, XMLElementBuilder.builder(new XMLElementSourceDouble())
                .elementName(new QName("three","two"))
                .asStartElement());
        assertThat(isAmatch).isTrue();
    }

    @Test
    public void doesntMatchXMLelementAtDepth() {
        XPath xPath = XPath.parse("/root/one/two");
        boolean isAmatch = xPath.matches(0, XMLElementBuilder.builder(new XMLElementSourceDouble())
                .elementName(new QName("hello"))
                .asStartElement());
        assertThat(isAmatch).isFalse();
    }

    @Test
    public void canGetMaxDepthWhenOneElement() {
        XPath xPath = XPath.parse("/one");
        assertThat(xPath.maxDepth()).isEqualTo(0);
    }

    @Test
    public void canGetMaxDeptWhenFiveElements() {
        XPath xPath = XPath.parse("/one/two/three/four/five");
        assertThat(xPath.maxDepth()).isEqualTo(4);
    }

}
