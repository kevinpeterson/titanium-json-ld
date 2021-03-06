package com.apicatalog.jsonld.lang;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;

import com.apicatalog.jsonld.json.JsonUtils;

public final class ListObject {

    private ListObject() {
    }

    /**
     * list object A list object is a map that has a @list key. It may also have
     * an @index key, but no other entries. See the Lists and Sets section of
     * JSON-LD 1.1 for a normative description.
     * 
     * @see <a href="https://www.w3.org/TR/json-ld11/#dfn-list-object">List
     *      Object</a>
     * 
     * @param value
     * @return
     */
    public static final boolean isListObject(JsonValue value) {
        return JsonUtils.isObject(value) 
                    && value.asJsonObject().containsKey(Keywords.LIST)
                    && (value.asJsonObject().size() == 1
                            || (value.asJsonObject().size() == 2 
                                    && value.asJsonObject().containsKey(Keywords.INDEX)
                                    )
                            );
    }

    /*
     * convert expanded value to a list object by first setting it to an array
     * containing only expanded value if it is not already an array, and then by
     * setting it to a map containing the key-value pair @list-expanded value.
     */
    public static final JsonObject toListObject(JsonValue value) {
        if (JsonUtils.isArray(value)) {
            return Json.createObjectBuilder().add(Keywords.LIST, value).build();
        }

        return Json.createObjectBuilder().add(Keywords.LIST, Json.createArrayBuilder().add(value)).build();

    }

}
