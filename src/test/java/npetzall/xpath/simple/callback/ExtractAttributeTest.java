package npetzall.xpath.simple.callback;

import npetzall.xpath.simple.element.XMLElementBuilder;
import npetzall.xpath.simple.element.XMLElementSourceDouble;
import npetzall.xpath.simple.xpath.XPath;
import npetzall.xpath.simple.xpath.XPathMatcher;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ExtractAttributeTest {

    @Test
    public void extractAttributeToMap() {
        XPath xPath = XPath.parse("/user/name");
        Map<String, String> results = new HashMap<>();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.extractAttributeLast(new QName("id"), "id"), results);
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("user"))
                        .asStartElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("name"))
                        .text("npetzall")
                        .addAttribute(new QName("id"), "123")
                        .asStartElement()
        );
        assertThat(results.containsKey("id")).isTrue();
        assertThat(results.get("id")).isEqualTo("123");
    }

    @Test
    public void willExtractAttributeLastToMap() {
        XPath xPath = XPath.parse("/user/name");
        Map<String, String> results = new HashMap<>();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.extractAttributeLast(new QName("id"), "id"), results);
        XMLElementSourceDouble xmlElementSourceDouble = new XMLElementSourceDouble();
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("user"))
                        .asStartElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
                        .text("npetzall")
                        .addAttribute(new QName("id"), "123")
                        .asStartElement()
        );
        xPathMatcher.onEndElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
                        .asEndElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
                        .text("nisse")
                        .addAttribute(new QName("id"), "321")
                        .asStartElement()
        );
        assertThat(results.containsKey("id")).isTrue();
        assertThat(results.get("id")).isEqualTo("321");
        assertThat(xmlElementSourceDouble.getRemovedListeners().size()).isEqualTo(0);
    }

    @Test
    public void willExtractAttributeOnlyOnceToMap() {
        XPath xPath = XPath.parse("/user/name");
        Map<String, String> results = new HashMap<>();
        XMLElementSourceDouble xmlElementSourceDouble = new XMLElementSourceDouble();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.extractAttributeFirst(new QName("id"), "id"), results);
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("user"))
                        .addAttribute(new QName("id"), "123")
                        .asStartElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
                        .addAttribute(new QName("id"), "123")
                        .text("npetzall")
                        .asStartElement()
        );
        assertThat(results.containsKey("id")).isTrue();
        assertThat(results.get("id")).isEqualTo("123");
        assertThat(xmlElementSourceDouble.getRemovedListeners().size()).isEqualTo(1);
    }

    @Test
    public void willNotExtractAttributeWhenNotExisting() {
        XPath xPath = XPath.parse("/user/name");
        Map<String, String> results = new HashMap<>();
        XMLElementSourceDouble xmlElementSourceDouble = new XMLElementSourceDouble();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.extractAttributeLast(new QName("id"), "id"),results);
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("user"))
                        .asStartElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
                        .addAttribute(new QName("ud"), "123")
                        .text("npetzall")
                        .asStartElement()
        );
        assertThat(results.containsKey("id")).isFalse();
        assertThat(xmlElementSourceDouble.getRemovedListeners().size()).isEqualTo(0);
    }

    @Test
    public void willNotExtractAttributeOnlyOnceWhenNotExisting() {
        XPath xPath = XPath.parse("/user/name");
        Map<String, String> results = new HashMap<>();
        XMLElementSourceDouble xmlElementSourceDouble = new XMLElementSourceDouble();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.extractAttributeFirst(new QName("id"), "id"), results);
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("user"))
                        .asStartElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
                        .addAttribute(new QName("ud"), "123")
                        .text("npetzall")
                        .asStartElement()
        );
        assertThat(results.containsKey("id")).isFalse();
        assertThat(xmlElementSourceDouble.getRemovedListeners().size()).isEqualTo(0);
    }

}
