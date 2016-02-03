package npetzall.xpath.simple.io;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XMLElementListener;
import npetzall.xpath.simple.api.XMLElementSource;
import npetzall.xpath.simple.element.XMLElementBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class XMLElementStream implements XMLElementSource , AutoCloseable {

    private static final Logger logger = LoggerFactory.getLogger(XMLElementStream.class);

    private final InputStream sourceStream;
    private final XMLStreamReader xmlStreamReader;
    private final List<XMLElementListener> xmlElementListeners;
    private final XMLElementBuilder xmlElementBuilder = XMLElementBuilder.builder(this);

    public XMLElementStream(InputStream inputStream, List<XMLElementListener> xmlElementListeners) {
        sourceStream = inputStream;
        this.xmlElementListeners = xmlElementListeners;
        checkSourceStream();
        checkXMLElementListeners();
        try {
            xmlStreamReader = XMLInputFactory.newFactory().createXMLStreamReader(inputStream);
        } catch (XMLStreamException e) {
            throw new XMLElementStreamException("Failed to create XMLStreamReader", e);
        }
        try {
            xmlStreamReader.nextTag();
        } catch (XMLStreamException e) {
            throw new XMLElementStreamException("Unable to find start", e);
        }
        if (hasListeners()) {
            process();
        }
        close();
    }

    private void checkSourceStream() {
        if (sourceStream == null) {
            throw new XMLElementStreamException("InputStream is null");
        }
    }

    private void checkXMLElementListeners() {
        if (xmlElementListeners == null) {
            throw new XMLElementStreamException("XMLElementListenerList is null");
        }
    }

    private void process() {
        do {
            if(xmlStreamReader.isStartElement()) {
                createStartElement();
            } else if (xmlStreamReader.isEndElement()) {
                createEndElement();
            } else {
                nextTagOrEOF();
            }
        }
        while(continueToProcess());
    }

    private boolean continueToProcess() {
        try {
            return xmlStreamReader.hasNext() && hasListeners();
        } catch (XMLStreamException e) {
            logger.error("During check of stop condition, XMLStreamReader.hasNext() failed so stopping", e);
            return false;
        }
    }

    private boolean hasListeners() {
        return !xmlElementListeners.isEmpty();
    }

    private void createStartElement() {
        xmlElementBuilder.elementName(xmlStreamReader.getName());
        populateAttributesInElementBuilder();
        try {
            if (xmlStreamReader.hasNext()) {
                xmlStreamReader.next();
                if (xmlStreamReader.isWhiteSpace()) {
                    nextTagOrEOF();
                } else if (xmlStreamReader.isCharacters()) {
                    xmlElementBuilder.text(xmlStreamReader.getText());
                    nextTagOrEOF();
                }
                onStartElement(xmlElementBuilder.asStartElement());
            } else {
                onStartElement(xmlElementBuilder.asStartElement());
            }
        } catch (XMLStreamException e) {
            onStartElement(xmlElementBuilder.asStartElement());
            logger.error("Failed to read XMLStreamReader, StartElement has been propagated", e);
        }
    }

    private void onStartElement(XMLElement xmlElement) {
        for(int i = xmlElementListeners.size()-1; i>=0; i--) {
            xmlElementListeners.get(i).onStartElement(xmlElement);
        }
    }

    private void populateAttributesInElementBuilder() {
        for(int i = 0; i < xmlStreamReader.getAttributeCount(); i++) {
            xmlElementBuilder.addAttribute(xmlStreamReader.getAttributeName(i), xmlStreamReader.getAttributeValue(i));
        }
    }

    private void createEndElement() {
        xmlElementBuilder.elementName(xmlStreamReader.getName());
        onEndElement(xmlElementBuilder.asEndElement());
        try {
            if (xmlStreamReader.hasNext()) {
                nextTagOrEOF();
            }
        } catch (XMLStreamException e) {
            logger.error("Failed to read XMLStreamReader, EndElement has been propagated", e);
        }
    }

    private void onEndElement(XMLElement xmlElement) {
        for(int i = xmlElementListeners.size()-1; i>=0; i--) {
            xmlElementListeners.get(i).onEndElement(xmlElement);
        }
    }

    private void nextTagOrEOF() {
        try {
            while (xmlStreamReader.hasNext()) {
                xmlStreamReader.next();
                if (xmlStreamReader.isStartElement() || xmlStreamReader.isEndElement()) {
                    return;
                }
            }
        } catch (XMLStreamException xmlE) {
            throw new XMLElementStreamException("Fetching nextTagOrEOF caused an exception", xmlE);
        }
    }

    @Override
    public void removeXMLElementListener(XMLElementListener xmlElementListener) {
        xmlElementListeners.remove(xmlElementListener);
    }

    @Override
    public void close() {
        try {
            xmlStreamReader.close();
        } catch (XMLStreamException e) {
            logger.debug("Failed to close XMLStreamReader", e);
        }

        try {
            sourceStream.close();
        } catch (IOException e) {
            logger.debug("Failed to close InputStream", e);
        }
    }
}
