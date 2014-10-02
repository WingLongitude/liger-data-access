package net.canadensys.dataportal.occurrence.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
public class ResourceInformationModelTest {

	@Test
	public void ResourceInformationModel() {

		ResourceInformationModel resourceInformationModel = new ResourceInformationModel();
		resourceInformationModel.setResource_name("test resource name");

		ResourceContactModel resourceContactModel = new ResourceContactModel();
		resourceContactModel.setName("Contact Name");
		resourceInformationModel.addContact(resourceContactModel);

		ObjectMapper om = new ObjectMapper();
		try {
			// serialize as json
			String json = om.writeValueAsString(resourceInformationModel);

			// read to object back from json string
			ResourceInformationModel resourceInformationModelFromJson = om.readValue(json, ResourceInformationModel.class);
			ResourceContactModel firstContactFromJson = resourceInformationModelFromJson.getContacts().iterator().next();

			assertEquals("Contact Name", firstContactFromJson.getName());

			// check that the reference to 'parent' ResourceInformationModel is there
			assertNotNull(firstContactFromJson.getResourceInformation());
			assertEquals("test resource name", firstContactFromJson.getResourceInformation().getResource_name());
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
