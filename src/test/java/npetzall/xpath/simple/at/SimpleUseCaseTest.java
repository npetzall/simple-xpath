package npetzall.xpath.simple.at;

import npetzall.xpath.simple.api.XPathProcessorFactory;
import npetzall.xpath.simple.io.XMLResources;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleUseCaseTest {

    @Test
    public void simpleUsageNoNameSpace() {
        XPathProcessorFactory xPathProcessorFactory = XPathProcessorFactory
                .builder()
                .addExtractAttributeFirst("/settings/section/category",new QName("id"), "first_att")
                .addExtractAttributeLast("/settings/section/category",new QName("id"), "last_att")
                .addExtractTextFirst("/settings/section/category/group/setting/default", "first_text")
                .addExtractTextLast("/settings/section/category/group/setting/default", "last_text")
                .addSetOnMatch("/settings/section/category/group/setting","selector","setting")
                .build();

        InputStream inputStream = new ByteArrayInputStream(XMLResources.getAdvanceXMLWithoutNamespace().getBytes(StandardCharsets.UTF_8));
        Map<String,String> data = xPathProcessorFactory.newProcessor().process(inputStream);
        assertThat(data).isNotNull();
        assertThat(data.containsKey("first_att")).isTrue();
        assertThat(data.get("first_att")).isEqualTo("videoacceleration");
        assertThat(data.containsKey("last_att")).isTrue();
        assertThat(data.get("last_att")).isEqualTo("audiooutput");
        assertThat(data.containsKey("first_text")).isTrue();
        assertThat(data.get("first_text")).isEqualTo("true");
        assertThat(data.containsKey("last_text")).isTrue();
        assertThat(data.get("last_text")).isEqualTo("101");
        assertThat(data.containsKey("selector")).isTrue();
        assertThat(data.get("selector")).isEqualTo("setting");
    }

    @Test
    public void simpleUsageWithNamespaceNotFoundXPathMissingNamespace() {
        XPathProcessorFactory xPathProcessorFactory = XPathProcessorFactory
                .builder()
                .addExtractAttributeFirst("/settings/section/category",new QName("id"), "first_att")
                .addExtractAttributeLast("/settings/section/category",new QName("id"), "last_att")
                .addExtractTextFirst("/settings/section/category/group/setting/default", "first_text")
                .addExtractTextLast("/settings/section/category/group/setting/default", "last_text")
                .addSetOnMatch("/settings/section/category/group/setting","selector","setting")
                .build();

        InputStream inputStream = new ByteArrayInputStream(XMLResources.getAdvanceXMLWithNamespace().getBytes(StandardCharsets.UTF_8));
        Map<String,String> data = xPathProcessorFactory.newProcessor().process(inputStream);
        assertThat(data).isNotNull();
        assertThat(data.size()).isEqualTo(0);
        assertThat(data.containsKey("first_att")).isFalse();
        assertThat(data.containsKey("last_att")).isFalse();
        assertThat(data.containsKey("first_text")).isFalse();
        assertThat(data.containsKey("last_text")).isFalse();
        assertThat(data.containsKey("selector")).isFalse();
    }

    @Test
    public void simpleUsageWithNamespaceUsingWildcard() {
        XPathProcessorFactory xPathProcessorFactory = XPathProcessorFactory
                .builder()
                .addExtractAttributeFirst("/*:settings/*:section/*:category",new QName("id"), "first_att")
                .addExtractAttributeLast("/*:settings/*:section/*:category",new QName("id"), "last_att")
                .addExtractTextFirst("/*:settings/*:section/*:category/*:group/*:setting/*:default", "first_text")
                .addExtractTextLast("/*:settings/*:section/*:category/*:group/*:setting/*:default", "last_text")
                .addSetOnMatch("/*:settings/*:section/*:category/*:group/*:setting","selector","setting")
                .build();

        InputStream inputStream = new ByteArrayInputStream(XMLResources.getAdvanceXMLWithNamespace().getBytes(StandardCharsets.UTF_8));
        Map<String,String> data = xPathProcessorFactory.newProcessor().process(inputStream);
        assertThat(data).isNotNull();
        assertThat(data.containsKey("first_att")).isTrue();
        assertThat(data.get("first_att")).isEqualTo("videoacceleration");
        assertThat(data.containsKey("last_att")).isTrue();
        assertThat(data.get("last_att")).isEqualTo("audiooutput");
        assertThat(data.containsKey("first_text")).isTrue();
        assertThat(data.get("first_text")).isEqualTo("true");
        assertThat(data.containsKey("last_text")).isTrue();
        assertThat(data.get("last_text")).isEqualTo("101");
        assertThat(data.containsKey("selector")).isTrue();
        assertThat(data.get("selector")).isEqualTo("setting");
    }

    @Test
    public void simpleUsageWithNamespace() {
        XPathProcessorFactory xPathProcessorFactory = XPathProcessorFactory
                .builder()
                .addPrefixAndNamespace("k","http://kodi/system/settings")
                .addExtractAttributeFirst("/k:settings/k:section/k:category",new QName("id"), "first_att")
                .addExtractAttributeLast("/k:settings/k:section/k:category",new QName("id"), "last_att")
                .addExtractTextFirst("/k:settings/k:section/k:category/k:group/k:setting/k:default", "first_text")
                .addExtractTextLast("/k:settings/k:section/k:category/k:group/k:setting/k:default", "last_text")
                .addSetOnMatch("/k:settings/k:section/k:category/k:group/k:setting","selector","setting")
                .build();

        InputStream inputStream = new ByteArrayInputStream(XMLResources.getAdvanceXMLWithNamespace().getBytes(StandardCharsets.UTF_8));
        Map<String,String> data = xPathProcessorFactory.newProcessor().process(inputStream);
        assertThat(data).isNotNull();
        assertThat(data.containsKey("first_att")).isTrue();
        assertThat(data.get("first_att")).isEqualTo("videoacceleration");
        assertThat(data.containsKey("last_att")).isTrue();
        assertThat(data.get("last_att")).isEqualTo("audiooutput");
        assertThat(data.containsKey("first_text")).isTrue();
        assertThat(data.get("first_text")).isEqualTo("true");
        assertThat(data.containsKey("last_text")).isTrue();
        assertThat(data.get("last_text")).isEqualTo("101");
        assertThat(data.containsKey("selector")).isTrue();
        assertThat(data.get("selector")).isEqualTo("setting");
    }

}
