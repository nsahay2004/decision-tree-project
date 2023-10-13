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
    /**
     * outcome field of the leaf
     */

    private String outcome;

    /**
     * constructor of decision leaf
     * @param outcome - outcome of the lefa
     */

    public DecisionLeaf(String outcome){
        this.outcome = outcome;
    }

    /**
     *
     * @param forDatum the datum to lookup a decision for
     * @return - decision as string
     */
    @Override
    public String getDecision(Row forDatum){
        return this.outcome;
    }


}
