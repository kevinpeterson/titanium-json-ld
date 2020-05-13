package com.apicatalog.jsonld.document;

import java.util.Arrays;
import java.util.Collection;

import com.apicatalog.json.JsonElement;
import com.apicatalog.json.JsonParser;
import com.apicatalog.jsonld.JsonLdError;
import com.apicatalog.jsonld.JsonLdErrorCode;
import com.apicatalog.jsonld.JsonLdRecord;

public class JsonDocument implements Document {

	private final String json;
	
	public JsonDocument(final String json) {
		this.json = json;
	}
	
	@Override
	public Collection<JsonLdRecord> parse(final JsonParser parser) throws JsonLdError {

		JsonElement root = parser.parse(json);
		
		if (root.isJsonArray()) {
			
		}
		
		if (root.isJsonObject()) {
			return Arrays.asList(JsonLdRecord.of(root.getAsJsonObject()));			
		}
		
		throw new JsonLdError(JsonLdErrorCode.LOADING_DOCUMENT_FAILED);
	}
	
}