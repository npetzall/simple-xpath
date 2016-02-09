package npetzall.xpath.simple.xpath;

import npetzall.xpath.simple.api.XMLElementListener;
import npetzall.xpath.simple.io.XMLElementStream;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class XPathProcessor {

    private final HashMap<String,String> parameters = new HashMap<>();
    private final List<XMLElementListener> xPathMatchers;

    public XPathProcessor(List<XPathMatcherBuilder> xPathMatcherBuilders) {
        xPathMatchers = xPathMatcherBuilders.stream().map(xPathMatcherBuilder -> xPathMatcherBuilder.build(parameters)).collect(Collectors.toList());
    }

    public Map<String, String> process(InputStream inputStream) {
        XMLElementStream xmlElementStream =  new XMLElementStream(inputStream, xPathMatchers);
        xmlElementStream.process();
        return parameters;
    }
}
