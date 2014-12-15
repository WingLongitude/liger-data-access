package net.canadensys.dataportal.occurrence.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test ResourceInformationModel serialization since it contains cyclic references.
 * 
 * @author cgendreau
 * 
 */
public class ResourceMetadataModelTest {

	@Test
	public void ResourceMetadataModel() {

		ResourceMetadataModel resourceMetadataModel = new ResourceMetadataModel();
		resourceMetadataModel.setResource_name("test resource name");

		ContactModel resourceContactModel = new ContactModel();
		resourceContactModel.setName("Contact Name");
		resourceMetadataModel.addContact(resourceContactModel);

		ObjectMapper om = new ObjectMapper();
		try {
			// serialize as json
			String json = om.writeValueAsString(resourceMetadataModel);

			// read to object back from json string
			ResourceMetadataModel resourceMetadataModelFromJson = om.readValue(json, ResourceMetadataModel.class);
			ContactModel firstContactFromJson = resourceMetadataModelFromJson.getContacts().iterator().next();

			assertEquals("Contact Name", firstContactFromJson.getName());

			// check that the reference to 'parent' ResourceInformationModel is there
			// assertNotNull(firstContactFromJson.getResourceInformation());
			// assertEquals("test resource name", firstContactFromJson.getResourceInformation().getResource_name());
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
