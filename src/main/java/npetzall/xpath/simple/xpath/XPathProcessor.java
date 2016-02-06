package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.api.XMLElementListener;
import npetzall.xpath.simple.api.XPathMatcherCallBack;
import npetzall.xpath.simple.io.XMLElementStream;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XPathProcessor {

    private final HashMap<String,String> parameters = new HashMap<>();
    private final List<XMLElementListener> xPathMatchers = new ArrayList<>();

    public XPathProcessor(Map<XPath, XPathMatcherCallBack> xPathAndXPathMatcherCallback) {
        xPathAndXPathMatcherCallback.forEach((xPath,xPathMatcherCallBak) ->
            xPathMatchers.add(new XPathMatcher(xPath,xPathMatcherCallBak,parameters))
        );
    }

    public Map<String, String> process(InputStream inputStream) {
        new XMLElementStream(inputStream, xPathMatchers);
        return parameters;
    }
}
