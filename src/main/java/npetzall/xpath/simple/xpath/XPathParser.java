package npetzall.xpath.simple.xpath;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class XPathParser {
    public List<XPathPart> parse(String xPath) {
        return parse(xPath, Collections.emptyMap());
    }

    public List<XPathPart> parse(String xPath, Map<String,String> prefixNamespaceMap) {
        if (xPath.startsWith("//")) {
            throw new  XPathNotSupportedYetException("Abstract xpath not supported yet");
        }
        String[] parts = xPath.split("/");
        ArrayList<XPathPart> xPathParts = new ArrayList<>(parts.length-1);
        for(int i=1; i<parts.length; i++) {
            if (parts[i].contains(":")) {
                xPathParts.add(createXPathPartWithNamespace(parts[i], prefixNamespaceMap));
            } else {
                xPathParts.add(new XPathPartElementOnly(new QName(parts[i])));
            }
        }
        return xPathParts;
    }

    private XPathPart createXPathPartWithNamespace(String part, Map<String, String> prefixNamespaceMap) {
        String[] tokens = part.split(":");
        if ("*".equals(tokens[0])) {
            return new XPathPartElementOnly(new QName("*", tokens[1]));
        } else {
            return new XPathPartElementOnly(new QName(prefixNamespaceMap.get(tokens[0]), tokens[1]));
        }
    }
}
