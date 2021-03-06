package com.apicatalog.rdf.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.apicatalog.rdf.RdfGraph;
import com.apicatalog.rdf.RdfObject;
import com.apicatalog.rdf.RdfPredicate;
import com.apicatalog.rdf.RdfSubject;
import com.apicatalog.rdf.RdfTriple;

final class RdfGraphImpl implements RdfGraph {

    private final Map<RdfSubject, Map<RdfPredicate, Set<RdfObject>>> index;
    
    private final List<RdfTriple> triples;
    
    protected RdfGraphImpl() {
        this.index = new HashMap<>();
        this.triples = new LinkedList<>();
    }

    public void add(final RdfTriple triple) {
        
        if (triple == null) {
            throw new IllegalArgumentException();
        }
        
        index
            .computeIfAbsent(triple.getSubject(), x -> new HashMap<>())
            .computeIfAbsent(triple.getPredicate(), x -> new HashSet<>())
            .add(triple.getObject());
        
        triples.add(triple);
    }
    
    @Override
    public boolean contains(final RdfTriple triple) {
        
        if (triple == null) {
            throw new IllegalArgumentException();
        }

        return index.containsKey(triple.getSubject()) 
                    && index.get(triple.getSubject()).containsKey(triple.getPredicate())
                    && index.get(triple.getSubject()).get(triple.getPredicate()).contains(triple.getObject())
                    ;
    }

    @Override
    public List<RdfTriple> toList() {
        return triples;
    }
}
