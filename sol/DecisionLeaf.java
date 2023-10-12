package sol;

import src.ITreeNode;
import src.Row;

/**
 * A class representing a leaf in the decision tree.
 */
// TODO: Uncomment this once you've implemented the methods in the ITreeNode interface!
public class DecisionLeaf  implements ITreeNode {
    // TODO: add fields as needed

    // TODO: Implement the ITreeNode interface

    private String outcome;

    public DecisionLeaf(String outcome){
        this.outcome = outcome;
    }

    public String getDecision(Row forDatum){
        return this.outcome;
    }
}
