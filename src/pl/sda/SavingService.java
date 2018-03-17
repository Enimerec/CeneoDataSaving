package pl.sda;

import pl.sda.entity.Products;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * author:
 * Mateusz
 * Marczak
 **/
public class SavingService {

    public void saveToXml(Products products) throws JAXBException {
        File file = new File("C:\\Users\\Mateusz\\Desktop\\file.xml");

        JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        jaxbMarshaller.marshal(products, file);
    }
}
