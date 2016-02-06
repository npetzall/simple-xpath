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

public class ExtractTextTest {

    @Test
    public void extractTextToMap() {
        XPath xPath = XPath.parse("/user/name");
        Map<String, String> results = new HashMap<>();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.extractText("name", results));
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                .elementName(new QName("user"))
                .asStartElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                .elementName(new QName("name"))
                .text("npetzall")
                .asStartElement()
        );
        assertThat(results.containsKey("name")).isTrue();
        assertThat(results.get("name")).isEqualTo("npetzall");
    }

    @Test
    public void willExtractTextLastToMap() {
        XPath xPath = XPath.parse("/user/name");
        Map<String, String> results = new HashMap<>();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.extractText("name", results));
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
                        .asStartElement()
        );
        assertThat(results.containsKey("name")).isTrue();
        assertThat(results.get("name")).isEqualTo("nisse");
        assertThat(xmlElementSourceDouble.getRemovedListeners().size()).isEqualTo(0);
    }

    @Test
    public void willExtractTextOnlyOnceToMap() {
        XPath xPath = XPath.parse("/user/name");
        Map<String, String> results = new HashMap<>();
        XMLElementSourceDouble xmlElementSourceDouble = new XMLElementSourceDouble();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.extractTextOnlyOnce("name", results));
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("user"))
                        .asStartElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
                        .text("npetzall")
                        .asStartElement()
        );
        assertThat(xmlElementSourceDouble.getRemovedListeners().size()).isEqualTo(1);
        assertThat(results.containsKey("name")).isTrue();
        assertThat(results.get("name")).isEqualTo("npetzall");
    }

    @Test
    public void willNotExtractTextWhenNoText() {
        XPath xPath = XPath.parse("/user/name");
        Map<String, String> results = new HashMap<>();
        XMLElementSourceDouble xmlElementSourceDouble = new XMLElementSourceDouble();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.extractText("name", results));
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("user"))
                        .asStartElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
                        .asStartElement()
        );
        assertThat(xmlElementSourceDouble.getRemovedListeners().size()).isEqualTo(0);
        assertThat(results.containsKey("name")).isFalse();
    }

    @Test
    public void willNotExtractTextOnlyOnceWhenNoText() {
        XPath xPath = XPath.parse("/user/name");
        Map<String, String> results = new HashMap<>();
        XMLElementSourceDouble xmlElementSourceDouble = new XMLElementSourceDouble();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.extractTextOnlyOnce("name", results));
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("user"))
                        .asStartElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
                        .asStartElement()
        );
        assertThat(xmlElementSourceDouble.getRemovedListeners().size()).isEqualTo(0);
        assertThat(results.containsKey("name")).isFalse();
    }

}
