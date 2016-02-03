package npetzall.xpath.simple.io;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XMLElementListener;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.assertj.core.api.StrictAssertions.fail;

public class XMLElementStreamTest {

    @Test
    public void xmlElementsAreCreatedAndSentToListener() {
        InputStream inputStream = new ByteArrayInputStream(XMLResources.getSimpleXMLWithoutNamespaces().getBytes(StandardCharsets.UTF_8));
        List<XMLElementListener> xmlElementListenerList = new LinkedList<>();
        final AtomicInteger startElementCounter = new AtomicInteger(0);
        final AtomicInteger endElementCounter = new AtomicInteger(0);
        xmlElementListenerList.add(new XMLElementListener() {
            public void onStartElement(XMLElement xmlElement) {
                startElementCounter.incrementAndGet();
            }

            public void onEndElement(XMLElement xmlElement) {
                endElementCounter.incrementAndGet();
            }
        });
        new XMLElementStream(inputStream, xmlElementListenerList);
        assertThat(startElementCounter.intValue()).isEqualTo(endElementCounter.intValue());
        assertThat(startElementCounter.intValue()).isEqualTo(XMLResources.getSimpleXMLWithoutNamespacesStartElementCount());
    }

    @Test
    public void xmlElementTextsShouldBeNullOrHello() {
        InputStream inputStream = new ByteArrayInputStream(XMLResources.getSimpleXMLWithTextWithspaceAndAttribute().getBytes(StandardCharsets.UTF_8));
        List<XMLElementListener> xmlElementListenerList = new LinkedList<>();
        xmlElementListenerList.add(new XMLElementListener() {
            public void onStartElement(XMLElement xmlElement) {
                if (xmlElement.getText() != null && !"hello".equalsIgnoreCase(xmlElement.getText())) {
                    fail("XMLElement text was \""+ xmlElement.getText() +"\"");
                }
            }

            public void onEndElement(XMLElement xmlElement) { }
        });
        new XMLElementStream(inputStream, xmlElementListenerList);
    }

    @Test(expectedExceptions = XMLElementStreamException.class)
    public void inputStreamIsNull() {
        List<XMLElementListener> xmlElementListenerList = new LinkedList<>();
        xmlElementListenerList.add(new XMLElementListener() {
            public void onStartElement(XMLElement xmlElement) { }

            public void onEndElement(XMLElement xmlElement) { }
        });
        new XMLElementStream(null, xmlElementListenerList);
    }

    @Test(expectedExceptions = XMLElementStreamException.class)
    public void xmlElementListnerListIsNull () {
        InputStream inputStream = new ByteArrayInputStream(XMLResources.getSimpleXMLWithoutNamespaces().getBytes(StandardCharsets.UTF_8));
        new XMLElementStream(inputStream, null);
    }

    @Test(expectedExceptions = XMLElementStreamException.class)
    public void xmlOnlyContainsXMLDeclaration() {
        InputStream inputStream = new ByteArrayInputStream(XMLResources.getXMLWithOnlyXMLDeclartion().getBytes(StandardCharsets.UTF_8));
        List<XMLElementListener> xmlElementListenerList = new LinkedList<>();
        xmlElementListenerList.add(new XMLElementListener() {
            public void onStartElement(XMLElement xmlElement) { }

            public void onEndElement(XMLElement xmlElement) { }
        });
        new XMLElementStream(inputStream, xmlElementListenerList);
    }

    @Test(expectedExceptions = XMLElementStreamException.class)
    public void xmlIsInvalid() {
        InputStream inputStream = new ByteArrayInputStream(XMLResources.getInvalidXML().getBytes(StandardCharsets.UTF_8));
        List<XMLElementListener> xmlElementListenerList = new LinkedList<>();
        xmlElementListenerList.add(new XMLElementListener() {
            public void onStartElement(XMLElement xmlElement) { }

            public void onEndElement(XMLElement xmlElement) { }
        });
        new XMLElementStream(inputStream, xmlElementListenerList);
    }
}
