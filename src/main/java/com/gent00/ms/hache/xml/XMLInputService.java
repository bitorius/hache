package com.gent00.ms.hache.xml;

import com.gent00.ms.hache.dto.XMLInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XMLInputService {
    @Autowired
    XMLInputRepository xmlInputRepository;

    public XMLInput getXMLInputById(Long id)
    {
        return xmlInputRepository.findById(id).get();
    }
    public void saveOrUpdate(XMLInput xmlInput)
    {
        xmlInputRepository.save(xmlInput);
    }
}
