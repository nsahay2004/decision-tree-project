package sol;

import src.ITreeNode;

/**
 * A class that represents the edge of an attribute node in the decision tree
 */
public class ValueEdge {
    // TODO: add more fields if needed

    private String valueName;
    private ITreeNode child;

    public ValueEdge(String valueName, ITreeNode child){
        this.valueName = valueName;
        this.child = child;
    }

    public String getvalueName() {
        return this.valueName;
    }

    public ITreeNode getChild(){
        return this.child;
    }
}
