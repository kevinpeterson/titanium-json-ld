package com.apicatalog.jsonld.api;

import java.net.URI;

import javax.json.JsonObject;

import com.apicatalog.jsonld.lang.Version;
import com.apicatalog.jsonld.loader.HttpLoader;
import com.apicatalog.jsonld.loader.LoadDocumentCallback;

/**
 * The {@link JsonLdOptions} type is used to pass various options to the
 * {@link JsonLdProcessor} methods.
 * 
 * @see <a href=https://www.w3.org/TR/json-ld11-api/#the-jsonldoptions-type">The
 *      JsonLdOptions Specification.</a>
 * 
 */
public final class JsonLdOptions {

    public enum RdfDirection { I18N_DATATYPE, COMPOUND_LITERAL }
    
    /**
     * The base IRI to use when expanding or compacting the document. 
     * If set, this overrides the input document's IRI.
     */
    private URI base;
    
    /**
     * If set to true, the JSON-LD processor replaces arrays with 
     * just one element with that element during compaction. 
     * If set to false, all arrays will remain arrays 
     * even if they have just one element.
     */
    private boolean compactArrays;
    
    /**
     * Determines if IRIs are compacted relative to the base option 
     * or document location when compacting.
     */
    private boolean compactToRelative;

    /**
     * The callback of the loader to be used to retrieve remote documents and contexts,
     *  implementing the LoadDocumentCallback. If specified, it is used to retrieve 
     *  remote documents and contexts; otherwise, if not specified, 
     *  the processor's built-in loader is used.
     */
    private LoadDocumentCallback documentLoader;
    
    /**
     * A context that is used to initialize the active context when expanding a document.
     */
    private JsonLdContext expandContext;
    
    private boolean extractAllScripts;
    
    private boolean ordered;
    
    private Version processingMode;
    
    private boolean produceGeneralizedRdf;
    
    private RdfDirection rdfDirection;
    
    private boolean useNativeTypes;
    
    private boolean useRdfType;
    
    // Framing https://www.w3.org/TR/json-ld11-framing/#jsonldoptions
    
    private JsonLdEmbed embed;
    
    private boolean explicit;
    
    private boolean omitDefault;
    
    private Boolean omitGraph;
    
    private boolean requiredAll;
    
    public JsonLdOptions() {
        
        // default values
        this.base = null;
        this.compactArrays = true;
        this.compactToRelative = true;
        this.documentLoader = new HttpLoader();
        this.expandContext = null;
        this.extractAllScripts = false;
        this.ordered = false;
        this.processingMode = Version.V1_1;
        this.produceGeneralizedRdf = true;
        this.rdfDirection = null;
        this.useNativeTypes = false;
        this.useRdfType = false;
        
        // framing defaults
        this.embed = JsonLdEmbed.ONCE;
        this.explicit = false;
        this.omitDefault = false;
        this.omitGraph = null;
        this.requiredAll = false;
    }

    public JsonLdOptions(JsonLdOptions options) {
        this.base = options.base;
        this.compactArrays = options.compactArrays;
        this.compactToRelative = options.compactToRelative;
        this.documentLoader = options.documentLoader;
        this.expandContext = options.expandContext;
        this.extractAllScripts = options.extractAllScripts;
        this.ordered = options.ordered;
        this.processingMode = options.processingMode;
        this.produceGeneralizedRdf = options.produceGeneralizedRdf;
        this.rdfDirection = options.rdfDirection;
        this.useNativeTypes = options.useNativeTypes;
        this.useRdfType = options.useRdfType;
        
        // framing
        this.embed = options.embed;
        this.explicit = options.explicit;
        this.omitDefault = options.omitDefault;
        this.omitGraph = options.omitGraph;
        this.requiredAll = options.requiredAll;
    }

    /**
     * The base IRI to use when expanding or
     * <a href="https://www.w3.org/TR/json-ld11-api/#dfn-compact">compacting</a> the
     * document. If set, this overrides the input document's IRI.
     * 
     * @return
     */
    public URI getBase() {
        return base;
    }

    /**
     * If set to true, the {@link JsonLdProcessor} replaces arrays with just one
     * element with that element during
     * <a href="https://www.w3.org/TR/json-ld11-api/#dfn-compact">compaction</a>. If
     * set to false, all arrays will remain arrays even if they have just one
     * element.
     * 
     * @return
     */
    public boolean isCompactArrays() {
        return compactArrays;
    }

    /**
     * Determines if IRIs are compacted relative to the {@link #getBase()} option
     * or document location when
     * <a href="https://www.w3.org/TR/json-ld11-api/#dfn-compact">compacting</a>.
     * 
     * @return
     */
    public boolean isCompactToRelative() {
        return compactToRelative;
    }

    /**
     * The callback of the loader to be used to retrieve remote documents and
     * contexts, implementing the {@link LoadDocumentCallback}. If specified, it is
     * used to retrieve remote documents and contexts; otherwise, if not specified,
     * the processor's built-in loader is used.
     * 
     * @return
     */
    public LoadDocumentCallback getDocumentLoader() {
        return documentLoader;
    }

    /**
     * If set to <code>true</code>, when extracting <a href=
     * "https://www.w3.org/TR/json-ld11-api/#dfn-json-ld-script-element">JSON-LD
     * script elements</a> from HTML, unless a specific
     * <a href="https://tools.ietf.org/html/rfc3986#section-3.5">fragment
     * identifier</a> is targeted, extracts all encountered <a href=
     * "https://www.w3.org/TR/json-ld11-api/#dfn-json-ld-script-element">JSON-LD
     * script elements</a> using an array form, if necessary.
     * 
     * @return
     */
    public boolean isExtractAllScripts() {
        return extractAllScripts;
    }

    /**
     * If set to <code>true</code>, certain algorithm processing steps where
     * indicated are ordered lexicographically. If <code>false</code>, order is not
     * considered in processing.
     * 
     * @return
     */
    public boolean isOrdered() {
        return ordered;
    }

    public Version getProcessingMode() {
        return processingMode;
    }

    public boolean isProduceGeneralizedRdf() {
        return produceGeneralizedRdf;
    }

    public RdfDirection getRdfDirection() {
        return rdfDirection;
    }

    public boolean isUseNativeTypes() {
        return useNativeTypes;
    }

    public boolean isUseRdfType() {
        return useRdfType;
    }
    
    public JsonLdContext getExpandContext() {
        return expandContext;
    }

    public void setBase(URI baseUri) {
        this.base = baseUri;
    }

    public void setCompactArrays(boolean compactArrays) {
        this.compactArrays = compactArrays;
    }

    public void setCompactToRelative(boolean compactToRelative) {
        this.compactToRelative = compactToRelative;
    }

    public void setDocumentLoader(LoadDocumentCallback documentLoader) {
        this.documentLoader = documentLoader;
    }

    public void setExtractAllScripts(boolean extractAllScripts) {
        this.extractAllScripts = extractAllScripts;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public void setProcessingMode(Version processingMode) {
        this.processingMode = processingMode;
    }

    public void setProduceGeneralizedRdf(boolean produceGeneralizedRdf) {
        this.produceGeneralizedRdf = produceGeneralizedRdf;
    }

    public void setRdfDirection(RdfDirection rdfDirection) {
        this.rdfDirection = rdfDirection;
    }

    public void setUseNativeTypes(boolean useNativeTypes) {
        this.useNativeTypes = useNativeTypes;
    }

    public void setUseRdfType(boolean useRdfType) {
        this.useRdfType = useRdfType;
    }

    public void setExpandContext(URI contextLocation) {
        this.expandContext = JsonLdContext.of(contextLocation);
    }
    
    public void setExpandContext(JsonObject contextObject) {
        this.expandContext = JsonLdContext.of(contextObject);
    }
    
    public void setExpandContext(JsonLdContext expandContext) {
        this.expandContext = expandContext;
    }
    
    // Framing
    
    public JsonLdEmbed getEmbed() {
        return embed;
    }
    
    public void setEmbed(JsonLdEmbed embed) {
        this.embed = embed;
    }
    
    public boolean isExplicit() {
        return explicit;
    }
    
    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }
    
    public boolean isOmitDefault() {
        return omitDefault;
    }
    
    public void setOmitDefault(boolean omitDefault) {
        this.omitDefault = omitDefault;
    }
    
    public Boolean isOmitGraph() {
        return omitGraph;
    }
    
    public void setOmitGraph(Boolean omitGraph) {
        this.omitGraph = omitGraph;
    }
    
    public boolean isRequiredAll() {
        return requiredAll;
    }
    
    public void setRequiredAll(boolean requiredAll) {
        this.requiredAll = requiredAll;
    }
}