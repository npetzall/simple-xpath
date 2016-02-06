package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.element.XMLElementBuilder;
import npetzall.xpath.simple.element.XMLElementSourceDouble;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

public class XPathMatcherTest {

    @Test
    public void createXPathMatcher() {
        XPath xPath = XPath.parse("/root/one/two");
        XPathMatcher xpathMatcher = new XPathMatcher(xPath, null, new HashMap<>());
        assertThat(xpathMatcher).isNotNull();
    }

    @Test
    public void xPathMatcherMatchesSingleXPathElement() {
        XPath xPath = XPath.parse("/root");
        final AtomicBoolean matchFound = new AtomicBoolean(false);
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, (xmlElement, parameters) -> {
            matchFound.set("root".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        }, new HashMap<>());
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
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, (xmlElement, parameters) -> {
            matchFound.set("root".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        }, new HashMap<>());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("root"))
                        .asStartElement());
        assertThat(matchFound.get()).isFalse();
    }

    @Test
    public void matchesThreeElements() {
        XPath xPath = XPath.parse("/one/two/three");
        final AtomicBoolean matchFound = new AtomicBoolean(false);
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, (xmlElement, parameters) -> {
            matchFound.set("three".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        }, new HashMap<>());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("two"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("three"))
                        .asStartElement());
        assertThat(matchFound.get()).isTrue();
    }

    @Test
    public void doesntMatcheOnFirstElements() {
        XPath xPath = XPath.parse("/two/one/three");
        final AtomicBoolean matchFound = new AtomicBoolean(false);
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, (xmlElement, parameters) -> {
            matchFound.set("three".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        }, new HashMap<>());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("four"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("three"))
                        .asStartElement());
        assertThat(matchFound.get()).isFalse();
    }

    @Test
    public void doesntMatcheOnSecondElements() {
        XPath xPath = XPath.parse("/one/two/three");
        final AtomicBoolean matchFound = new AtomicBoolean(false);
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, (xmlElement, parameters) -> {
            matchFound.set("three".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        }, new HashMap<>());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("four"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("three"))
                        .asStartElement());
        assertThat(matchFound.get()).isFalse();
    }

    @Test
    public void doesntMatcheOnThreeElements() {
        XPath xPath = XPath.parse("/one/two/three");
        final AtomicBoolean matchFound = new AtomicBoolean(false);
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, (xmlElement, parameters) -> {
            matchFound.set("three".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        }, new HashMap<>());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("two"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("four"))
                        .asStartElement());
        assertThat(matchFound.get()).isFalse();
    }

    @Test
    public void matchesThreeElementsAfterExistingOneElement() {
        XPath xPath = XPath.parse("/one/two/three");
        final AtomicBoolean matchFound = new AtomicBoolean(false);
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, (xmlElement, parameters) -> {
            matchFound.set("three".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        }, new HashMap<>());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onEndElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asEndElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("two"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("three"))
                        .asStartElement());
        assertThat(matchFound.get()).isTrue();
    }

    @Test
    public void doesntMatchesThreeElementsAfterExistingOneElement() {
        XPath xPath = XPath.parse("/one/two/three");
        final AtomicBoolean matchFound = new AtomicBoolean(false);
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, (xmlElement, parameters) -> {
            matchFound.set("three".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        }, new HashMap<>());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onEndElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asEndElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("two"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("four"))
                        .asStartElement());
        assertThat(matchFound.get()).isFalse();
    }

    @Test
    public void doesntMatchesThreeElementsAfterSwitchingRootElement() {
        XPath xPath = XPath.parse("/one/two/three");
        final AtomicBoolean matchFound = new AtomicBoolean(false);
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, (xmlElement, parameters) -> {
            matchFound.set("three".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        }, new HashMap<>());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onEndElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asEndElement());
        xPathMatcher.onEndElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asEndElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("four"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("two"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("three"))
                        .asStartElement());
        assertThat(matchFound.get()).isFalse();
    }

    @Test
    public void matchesThreeElementsAfterSwitchingRootElement() {
        XPath xPath = XPath.parse("/one/two/three");
        final AtomicBoolean matchFound = new AtomicBoolean(false);
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, (xmlElement, parameters) -> {
            matchFound.set("three".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        }, new HashMap<>());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onEndElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asEndElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("four"))
                        .asStartElement());
        xPathMatcher.onEndElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("four"))
                        .asEndElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("two"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("three"))
                        .asStartElement());
        assertThat(matchFound.get()).isTrue();
    }

    @Test
    public void matchesThreeElementsOnSiblingElement() {
        XPath xPath = XPath.parse("/one/two/three");
        final AtomicBoolean matchFound = new AtomicBoolean(false);
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, (xmlElement, parameters) -> {
            matchFound.set("three".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        }, new HashMap<>());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onEndElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asEndElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("four"))
                        .asStartElement());
        xPathMatcher.onEndElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("four"))
                        .asEndElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("two"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("four"))
                        .asStartElement());
        xPathMatcher.onEndElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("four"))
                        .asEndElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("three"))
                        .asStartElement());
        assertThat(matchFound.get()).isTrue();
    }

    @Test
    public void elementsExistsBeneathXPath() {
        XPath xPath = XPath.parse("/one/two/three");
        final AtomicBoolean matchFound = new AtomicBoolean(false);
        XPathMatcher xPathMatcher = new XPathMatcher(xPath, (xmlElement, parameters) -> {
            matchFound.set("three".equals(xmlElement.getElementName().getLocalPart()));
            return true;
        }, new HashMap<>());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("one"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("two"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("three"))
                        .asStartElement());
        xPathMatcher.onStartElement(
                XMLElementBuilder.builder(new XMLElementSourceDouble())
                        .elementName(new QName("four"))
                        .asStartElement());
        assertThat(matchFound.get()).isTrue();
    }

}
