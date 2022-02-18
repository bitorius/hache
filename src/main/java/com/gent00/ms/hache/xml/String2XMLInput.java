package com.gent00.ms.hache.xml;
import com.gent00.ms.hache.dto.XMLInput;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class String2XMLInput implements Converter<String, XMLInput> {

    @Override
    public XMLInput convert(String source) {
        byte[] valueDecoded = Base64.decodeBase64(source);
        String decodedString = new String(valueDecoded);
        return new XMLInput(decodedString, "SomeDigest");
    }
}
