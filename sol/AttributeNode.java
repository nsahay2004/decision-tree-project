package sol;

import java.util.List;
import src.ITreeNode;
import src.Row;

/**
 * A class representing an inner node in the decision tree.
 */
// TODO: Uncomment this once you've implemented the methods in the ITreeNode interface!
public class AttributeNode  implements ITreeNode {
    // TODO: add more fields as needed

    private String name;
    private int numberOfEdges;

    private String defaultValue;

    private List<ValueEdge> outgoingEdges;

    public AttributeNode(String name, int numberOfEdges, String defaultValue, List<ValueEdge> outgoingEdges){
        this.name = name;
        this.numberOfEdges = numberOfEdges;
        this.defaultValue = defaultValue;
        this.outgoingEdges = outgoingEdges;
    }

    public String getDecision(Row forDatum) {
        String targetValue = forDatum.getAttributeValue(this.name);
        if (this.outgoingEdges.contains(targetValue)) {
            for (ValueEdge o : this.outgoingEdges) {
                if (o.getValuename() == targetValue) {
                    return o.getChild().getDecision(forDatum);
                }
            }
        } else {
            return this.defaultValue;
        }
    }


    }


    // TODO: implement the ITreeNode interface
}
