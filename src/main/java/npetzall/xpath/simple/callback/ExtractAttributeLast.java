package npetzall.xpath.simple.callback;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XPathMatcherCallBack;

import javax.xml.namespace.QName;
import java.util.Map;

public class ExtractAttributeLast implements XPathMatcherCallBack {

    private final QName attributeName;
    private final String key;

    ExtractAttributeLast(QName attributeName, String key) {
        this.attributeName = attributeName;
        this.key = key;
    }

    @Override
    public boolean matchFound(XMLElement xmlElement, Map<String,String> parameters) {
        if (xmlElement.hasAttributeWithName(attributeName)) {
            parameters.put(key,xmlElement.getValueOfAttributeWithName(attributeName));
        }
        return false;
    }
}
