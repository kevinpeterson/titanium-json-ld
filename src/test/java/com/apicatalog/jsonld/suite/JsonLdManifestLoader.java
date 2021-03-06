package com.apicatalog.jsonld.suite;

import java.net.URI;
import java.util.stream.Stream;

import javax.json.JsonObject;
import javax.json.JsonValue;

import org.junit.Assert;

import com.apicatalog.jsonld.api.JsonLdError;
import com.apicatalog.jsonld.document.RemoteDocument;
import com.apicatalog.jsonld.loader.LoadDocumentOptions;
import com.apicatalog.jsonld.suite.loader.ZipResourceLoader;


public final class JsonLdManifestLoader {

    public static final String JSON_LD_API_BASE = "zip://json-ld-test-suite-20200518.zip/com/github/w3c/json-ld-api/tests/";
    public static final String JSON_LD_FRAMING_BASE = "zip://json-ld-framing-test-suite-20200609.zip/com/github/w3c/json-ld-framing/tests/";
        
    private final String manifestName;
    private final String manifestBase;
    
    private JsonLdManifestLoader(final String manifestBase, final String manifestName) {
        this.manifestBase = manifestBase;
        this.manifestName = manifestName;
    }
    
    public static JsonLdManifestLoader load(final String manifestBase, final String manifestName) {
        return new JsonLdManifestLoader(manifestBase, manifestName);
    }
    
    public Stream<JsonLdTestCase> stream() throws JsonLdError {
     
        RemoteDocument manifest = new ZipResourceLoader(true).loadDocument(URI.create(manifestBase + manifestName), new LoadDocumentOptions());

        Assert.assertNotNull(manifest);
        Assert.assertNotNull(manifest.getDocument());

        final JsonObject manifestObject = (JsonObject)manifest.getDocument().getJsonStructure();
        
        String baseUri = manifestObject.getString("baseIri");
        
        return manifestObject
                .getJsonArray("sequence")
                    .stream()
                        .map(JsonValue::asJsonObject)
                        .map(o -> JsonLdTestCase.of(o, manifestName, manifestBase, baseUri));            
    }
}
