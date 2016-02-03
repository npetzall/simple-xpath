package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.element.XMLElementBuilder;
import npetzall.xpath.simple.element.XMLElementSourceDouble;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

public class XPathMatcherTest {

    @Test
    public void createXPathMatcher() {
        XPath xPath = XPath.parse("/root/one/two");
        XPathMatcher xpathMatcher = new XPathMatcher(xPath, null);
        assertThat(xpathMatcher).isNotNull();
    }

    @Test
    public void xPathMatcherMatchesSingleXPathElement() {
        XPath xPath = XPath.parse("/root");
        final AtomicBoolean matchFound = new AtomicBoolean(false);
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, xmlElement -> {
            matchFound.set("root".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        });
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("root"))
                        .asStartElement());
        assertThat(matchFound.get()).isTrue();
    }

    @Test
    public void xPathMatcherDoesntMatchesSingleXPathElement() {
        XPath xPath = XPath.parse("/other");
        final AtomicBoolean matchFound = new AtomicBoolean(false);
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, xmlElement -> {
            matchFound.set("root".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        });
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("root"))
                        .asStartElement());
        assertThat(matchFound.get()).isFalse();
    }
}
