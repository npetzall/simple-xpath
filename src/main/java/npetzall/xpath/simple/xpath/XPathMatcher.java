package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XMLElementListener;
import npetzall.xpath.simple.api.XPathMatcherCallBack;

import java.util.Map;

public class XPathMatcher implements XMLElementListener {

    private final XPath xPath;
    private final XPathMatcherCallBack xPathMatcherCallBack;
    private final Map<String, String> parameters;

    private int depth=0;
    private int matchedToDepth=0;

    public XPathMatcher(XPath xPath, XPathMatcherCallBack xPathMatcherCallBack, Map<String,String> parameters) {
        this.xPath = xPath;
        this.xPathMatcherCallBack = xPathMatcherCallBack;
        this.parameters = parameters;
    }

    @Override
    public void onStartElement(XMLElement xmlElement) {
        if (isMatching(xmlElement)) {
            matchedToDepth = depth;
            if (xPath.maxDepth() == matchedToDepth && xPathMatcherCallBack.matchFound(xmlElement, parameters)) {
                xmlElement.getSource().removeXMLElementListener(this);
            }
        }
        depth++;
    }

    private boolean isMatching(XMLElement xmlElement) {
        return (depth == 0 || depth == matchedToDepth + 1) && depth <= xPath.maxDepth() && xPath.matches(depth, xmlElement);
    }

    @Override
    public void onEndElement(XMLElement xmlElement) {
        depth--;
        if (depth <= matchedToDepth) {
            matchedToDepth--;
        }
    }
}
