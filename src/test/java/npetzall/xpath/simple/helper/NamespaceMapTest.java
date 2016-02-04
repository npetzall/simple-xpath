package npetzall.xpath.simple.helper;

import org.testng.annotations.Test;

import java.util.Map;

import static npetzall.xpath.simple.helper.NamespaceMap.namespace;
import static org.assertj.core.api.Assertions.assertThat;

public class NamespaceMapTest {

    @Test
    public void canBeUsed() {
        Map<String,String> sut = namespace("ns1","one").add("ns2","two");
        assertThat(sut.get("ns1")).isEqualTo("one");
        assertThat(sut.get("ns2")).isEqualTo("two");
    }
}
