package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.api.XMLElement;

@FunctionalInterface
public interface XPathPart {

    boolean matches(XMLElement xmlElement);

}
