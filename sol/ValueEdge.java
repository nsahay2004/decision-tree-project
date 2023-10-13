package sol;

import src.ITreeNode;

/**
 * A class that represents the edge of an attribute node in the decision tree
 */
public class ValueEdge {
    // TODO: add more fields if needed

    /**
     * name of the valueedge
     */
    private String valueName;

    /**
     * child of the valueedge that leads to next node or leaf
     */
    private ITreeNode child;

    /**
     * constructor for ValueEdge
     *
     * @param valueName - the name of the edge
     * @param child - the child of the edge
     *
     */

    public ValueEdge(String valueName, ITreeNode child){
        this.valueName = valueName;
        this.child = child;
    }

    /**
     * getter for ValueName
     * @return - String Value
     */

    public String getValueName() {
        return this.valueName;
    }

    /**
     * getter for child
     * @return - ITreeNode
     */

    public ITreeNode getChild(){
        return this.child;
    }
}
