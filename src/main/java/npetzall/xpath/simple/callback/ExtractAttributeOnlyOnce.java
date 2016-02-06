package npetzall.xpath.simple.callback;

import npetzall.xpath.simple.api.XMLElement;
import npetzall.xpath.simple.api.XPathMatcherCallBack;

import javax.xml.namespace.QName;
import java.util.Map;

public class ExtractAttributeOnlyOnce implements XPathMatcherCallBack {

    private final QName attributeName;
    private final String key;
    private final Map<String,String> map;

    ExtractAttributeOnlyOnce(QName attributeName, String key, Map<String,String> map) {
        this.attributeName = attributeName;
        this.key = key;
        this.map = map;
    }

    @Override
    public boolean matchFound(XMLElement xmlElement) {
        if (xmlElement.hasAttributeWithName(attributeName)) {
            map.put(key,xmlElement.getValueOfAttributeWithName(attributeName));
            return true;
        }
        return false;
    }
}
