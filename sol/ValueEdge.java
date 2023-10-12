package sol;

import src.ITreeNode;

/**
 * A class that represents the edge of an attribute node in the decision tree
 */
public class ValueEdge {
    // TODO: add more fields if needed

    private String valuename;
    private ITreeNode child;

    public ValueEdge(String valuename, ITreeNode child){
        this.valuename = valuename;
        this.child = child;
    }

    public String getValuename() {
        return this.valuename;
    }

    public ITreeNode getChild(){
        return this.child;
    }
}
