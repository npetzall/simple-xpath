package npetzall.xpath.simple.xpath;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XPathTest {

    @Test
    public void createXPathWithNoNamespace() {
        XPath xpath = XPath.parse("/root/one/two");
        assertThat(xpath).isNotNull();
    }

}
