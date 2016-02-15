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

public class CountElementTest {

    @Test
    public void countOne() {
        XPath xPath = XPath.parse("/users/name");
        Map<String, String> results = new HashMap<>();
        XMLElementSourceDouble xmlElementSourceDouble = new XMLElementSourceDouble();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.countElements("userCount"), results);
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("users"))
                        .asStartElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
                        .asStartElement()
        );
        assertThat(Integer.parseInt(results.get("userCount"))).isEqualTo(1);
    }

    @Test
    public void countTwo() {
        XPath xPath = XPath.parse("/users/name");
        Map<String, String> results = new HashMap<>();
        XMLElementSourceDouble xmlElementSourceDouble = new XMLElementSourceDouble();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.countElements("userCount"), results);
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("users"))
                        .asStartElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
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
                        .asStartElement()
        );
        assertThat(Integer.parseInt(results.get("userCount"))).isEqualTo(2);
    }

    @Test
    public void countTwoWithElementsBetween() {
        XPath xPath = XPath.parse("/users/name");
        Map<String, String> results = new HashMap<>();
        XMLElementSourceDouble xmlElementSourceDouble = new XMLElementSourceDouble();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.countElements("userCount"), results);
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("users"))
                        .asStartElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
                        .asStartElement()
        );
        xPathMatcher.onEndElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
                        .asEndElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("uid"))
                        .asStartElement()
        );
        xPathMatcher.onEndElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("uid"))
                        .asEndElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("name"))
                        .asStartElement()
        );
        assertThat(Integer.parseInt(results.get("userCount"))).isEqualTo(2);
    }

}
