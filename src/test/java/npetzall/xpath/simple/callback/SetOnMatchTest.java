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

public class SetOnMatchTest {

    @Test
    public void match() {
        XPath xPath = XPath.parse("/user/name");
        Map<String, String> results = new HashMap<>();
        XMLElementSourceDouble xmlElementSourceDouble = new XMLElementSourceDouble();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.setOnMatch("set", "hello"),results);
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
        assertThat(results.containsKey("set")).isTrue();
        assertThat(results.get("set")).isEqualTo("hello");
        assertThat(xmlElementSourceDouble.getRemovedListeners().size()).isEqualTo(1);
    }

    @Test
    public void doesntMatch() {
        XPath xPath = XPath.parse("/user/name");
        Map<String, String> results = new HashMap<>();
        XMLElementSourceDouble xmlElementSourceDouble = new XMLElementSourceDouble();
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, Callbacks.setOnMatch("set", "hello"),results);
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("user"))
                        .asStartElement()
        );
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(xmlElementSourceDouble)
                        .elementName(new QName("elvis"))
                        .asStartElement()
        );
        assertThat(results.containsKey("set")).isFalse();
        assertThat(xmlElementSourceDouble.getRemovedListeners().size()).isEqualTo(0);
    }

}
