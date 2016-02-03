package npetzall.xpath.simple.element;

import npetzall.xpath.simple.api.XMLElementListener;
import npetzall.xpath.simple.api.XMLElementSource;

import java.util.ArrayList;
import java.util.List;

public class XMLElementSourceDouble implements XMLElementSource {

    List<XMLElementListener> removedListeners = new ArrayList<>();

    @Override
    public void removeXMLElementListener(XMLElementListener xmlElementListener) {
        removedListeners.add(xmlElementListener);
    }

    public List<XMLElementListener> getRemovedListeners() {
        return removedListeners;
    }
}
