/*
 *  Licensed to Peter Karich under one or more contributor license 
 *  agreements. See the NOTICE file distributed with this work for 
 *  additional information regarding copyright ownership.
 * 
 *  Peter Karich licenses this file to you under the Apache License, 
 *  Version 2.0 (the "License"); you may not use this file except 
 *  in compliance with the License. You may obtain a copy of the 
 *  License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.graphhopper.util;

import com.graphhopper.storage.Graph;
import gnu.trove.set.hash.TIntHashSet;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is introduced as a helper to avoid cluttering the Graph interface
 * with all the common methods. Most of the methods are useful for unit tests.
 *
 * @author Peter Karich,
 */
public class GraphUtility {

	public static int count(EdgeIterator iter) {
		return neighbors(iter).size();
	}

	public static List<Integer> neighbors(EdgeIterator iter) {
		List<Integer> list = new ArrayList<Integer>();
		while (iter.next()) {
			list.add(iter.node());
		}
		return list;
	}

	public static int count(RawEdgeIterator iter) {
		int counter = 0;
		while (iter.next()) {
			++counter;
		}
		return counter;
	}

	public static int count(Iterable<?> iter) {
		int counter = 0;
		for (Object o : iter) {
			++counter;
		}
		return counter;
	}

	public static boolean contains(EdgeIterator iter, int... locs) {
		TIntHashSet set = new TIntHashSet();

		while (iter.next()) {
			set.add(iter.node());
		}
		for (int l : locs) {
			if (!set.contains(l))
				return false;
		}
		return true;
	}

	/**
	 * Added this helper method to avoid cluttering the graph interface. Good
	 * idea?
	 */
	public static EdgeIterator getEdges(Graph graph, int index, boolean out) {
		if (out)
			return graph.getOutgoing(index);
		else
			return graph.getIncoming(index);
	}

	public static int getToNode(Graph g, int edge, int endNode) {
		if (EdgeIterator.Edge.isValid(edge)) {
			EdgeIterator iterTo = g.getEdgeProps(edge, endNode);
			return iterTo.node();
		}
		return endNode;
	}

	public static final EdgeSkipIterator EMPTY = new EdgeSkipIterator() {
		@Override
		public int skippedEdge() {
			throw new UnsupportedOperationException("Not supported. Edge is empty.");
		}

		@Override
		public void skippedEdge(int node) {
			throw new UnsupportedOperationException("Not supported. Edge is empty.");
		}

		@Override
		public void distance(double dist) {
			throw new UnsupportedOperationException("Not supported. Edge is empty.");
		}

		@Override
		public void flags(int flags) {
			throw new UnsupportedOperationException("Not supported. Edge is empty.");
		}

		@Override
		public boolean next() {
			throw new UnsupportedOperationException("Not supported. Edge is empty.");
		}

		@Override
		public int edge() {
			throw new UnsupportedOperationException("Not supported. Edge is empty.");
		}

		@Override
		public int baseNode() {
			throw new UnsupportedOperationException("Not supported. Edge is empty.");
		}

		@Override
		public int node() {
			throw new UnsupportedOperationException("Not supported. Edge is empty.");
		}

		@Override
		public double distance() {
			throw new UnsupportedOperationException("Not supported. Edge is empty.");
		}

		@Override
		public int flags() {
			throw new UnsupportedOperationException("Not supported. Edge is empty.");
		}

		@Override
		public boolean isEmpty() {
			return true;
		}
	};
}