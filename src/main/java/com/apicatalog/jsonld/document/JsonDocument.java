package com.apicatalog.jsonld.document;

import java.io.Reader;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.stream.JsonParser;

import com.apicatalog.jsonld.api.JsonLdError;
import com.apicatalog.jsonld.api.JsonLdErrorCode;
import com.apicatalog.jsonld.json.JsonUtils;

public final class JsonDocument implements Document {

    private JsonStructure structure;

    protected JsonDocument(final JsonStructure structue) {
        this.structure = structue;
    }
    
    public static final Document parse(final Reader reader)  throws JsonLdError {
        
        try (final JsonParser parser = Json.createParser(reader)) {

            if (!parser.hasNext()) {
                throw new JsonLdError(JsonLdErrorCode.LOADING_DOCUMENT_FAILED);
            }

            parser.next();

            JsonValue root = parser.getValue();

            if (JsonUtils.isArray(root)) {
                return new JsonDocument(root.asJsonArray());
            }

            if (JsonUtils.isObject(root)) {
                return new JsonDocument(root.asJsonObject());
            }

            throw new JsonLdError(JsonLdErrorCode.LOADING_DOCUMENT_FAILED);
            
        } catch (JsonException e) {
            throw new JsonLdError(JsonLdErrorCode.LOADING_DOCUMENT_FAILED, e);
        }
    }
    
    
    @Override
    public JsonStructure getJsonStructure() throws JsonLdError {
        return structure;
    }

    @Override
    public byte[] getRawPayload() throws JsonLdError {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isJsonStructure() {
        return true;
    }
    
    @Override
    public boolean isRawPayload() {
        return false;
    }

}
