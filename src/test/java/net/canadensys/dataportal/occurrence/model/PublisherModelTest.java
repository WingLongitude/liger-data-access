package net.canadensys.dataportal.occurrence.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test PublisherInformationModel serialization since it contains cyclic references.
 *  
 * @author Pedro Guimarães
 * 
 */
public class PublisherModelTest {

	@Test
	public void PublisherModel() {
		
		PublisherModel publisherModel = new PublisherModel();
		publisherModel.setName("test Publisher name");

		ContactModel publisherContactModel = new ContactModel();
		publisherContactModel.setName("Contact Name");
		publisherModel.addContact(publisherContactModel);
		
		DwcaResourceModel resourceModel = new DwcaResourceModel();
		resourceModel.setName("Resource 1");
		publisherModel.addResource(resourceModel);
		
		ObjectMapper om = new ObjectMapper();
		try {
			// serialize as json
			String json = om.writeValueAsString(publisherModel);

			// read to object back from json string
			PublisherModel publisherInformationModelFromJson = om.readValue(json, PublisherModel.class);
			ContactModel firstContactFromJson = publisherInformationModelFromJson.getContacts().iterator().next();
			DwcaResourceModel resourceFromJson = publisherInformationModelFromJson.getResources().iterator().next();
					
			assertEquals("Contact Name", firstContactFromJson.getName());
			assertEquals("Resource 1", resourceFromJson.getName());

			// check that the reference to 'parent' PublisherInformationModel is there
			assertNotNull(resourceFromJson.getPublisher());
			assertEquals("test Publisher name", resourceFromJson.getPublisher().getName());
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}