package npetzall.xpath.simple.io;

import npetzall.xpath.simple.api.XMLElementListener;
import npetzall.xpath.simple.api.XMLElementSource;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.List;

public class XMLElementStream implements XMLElementSource{

    private final InputStream sourceStream;
    private final XMLStreamReader xmlStreamReader;
    private final List<XMLElementListener> xmlElementListeners;

    public XMLElementStream(InputStream inputStream, List<XMLElementListener> xmlElementListeners) {
        if(inputStream == null || xmlElementListeners.isEmpty()) {
            throw new XMLElementStreamException("InputStream is null: " + (inputStream != null) + " XMLElementList is empty: " + (xmlElementListeners.isEmpty()));
        }
        sourceStream = inputStream;
        try {
            xmlStreamReader = XMLInputFactory.newFactory().createXMLStreamReader(inputStream);
        } catch (XMLStreamException e) {
            throw new XMLElementStreamException("Failed to create XMLStreamReader", e);
        }
        this.xmlElementListeners = xmlElementListeners;
        try {
            xmlStreamReader.nextTag();
        } catch (XMLStreamException e) {
            throw new XMLElementStreamException("Unable to find start", e);
        }
    }

    public void process() {
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
        return false;
    }

    private void createStartElement() {

    }

    private void createEndElement() {

    }

    private void nextTagOrEOF() {

    }

    @Override
    public void removeXMLElementListener(XMLElementListener xmlElementListener) {
        xmlElementListeners.remove(xmlElementListener);
    }
}
