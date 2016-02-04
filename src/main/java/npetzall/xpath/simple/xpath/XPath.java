package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.api.XMLElement;

import java.util.List;
import java.util.Map;

public class XPath {

    private static final XPathParser xPathParser = new XPathParser();

    private List<XPathPart> xPathPartList;

    private XPath(List<XPathPart> xPathPartList) {
        this.xPathPartList = xPathPartList;
    }

    public static XPath parse(String xPathString) {
        return new XPath(xPathParser.parse(xPathString));
    }

    public static XPath parse(String xPathString, Map<String,String> prefixNamespaceMap) {
        return new XPath(xPathParser.parse(xPathString,prefixNamespaceMap));
    }

    public boolean matches(int depth, XMLElement xmlElement) {
        return xPathPartList.get(depth).matches(xmlElement);
    }

    public int maxDepth() {
        return xPathPartList.size() - 1;
    }
}
