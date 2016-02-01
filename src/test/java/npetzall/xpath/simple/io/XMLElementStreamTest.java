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

public class XMLElementStreamTest {

    @Test
    public void createXMLElementStreamFromInputStreamAndElementListenerList() {
        InputStream inputStream = new ByteArrayInputStream(XMLResources.getSimpleXMLWithoutNamespaces().getBytes(StandardCharsets.UTF_8));
        List<XMLElementListener> xmlElementListenerList = new LinkedList<>();
        xmlElementListenerList.add(new XMLElementListener() {
            public void onStartElement(XMLElement xmlElement) {

            }

            public void onEndElement(XMLElement xmlElement) {

            }
        });
        XMLElementStream xmlElementStream = new XMLElementStream(inputStream, xmlElementListenerList);
        assertThat(xmlElementStream).isNotNull();
    }

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
        assertThat(startElementCounter.intValue()).isEqualTo(endElementCounter.intValue());
        assertThat(startElementCounter.intValue()).isEqualTo(XMLResources.getSimpleXMLWithoutNamespacesStartElementCount());
    }
}
