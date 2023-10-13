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
    /**
     * name of the attribute
     */

    private String name;

    /**
     * number of ValueEdges
     */

    private  int numberOfEdges;

    /**
     * default value  of the attribute
     */

    private String defaultValue;

    /**
     * list of edges
     */

    private List<ValueEdge> outgoingEdges;


    /**
     * constructor for AttributeNode
     *
     * @param name - attribute name
     * @param numberOfEdges - no. of edges
     * @param defaultValue - default value
     * @param outgoingEdges - list of valueEdges
     */
    public AttributeNode(String name, int numberOfEdges, String defaultValue, List<ValueEdge> outgoingEdges){
        this.name = name;
        this.numberOfEdges = numberOfEdges;
        this.defaultValue = defaultValue;
        this.outgoingEdges = outgoingEdges;
    }

    /**
     *
     * @param forDatum the datum to lookup a decision for
     * @return - string decision
     */

    @Override
    public String getDecision(Row forDatum) {
        String targetValue = forDatum.getAttributeValue(this.name);
            for (ValueEdge o : this.outgoingEdges) {
                if (o.getValueName().equals(targetValue)){
                    return o.getChild().getDecision(forDatum);
                }
            }

            return this.defaultValue;




    }

    /**
     * * gets number of edges
     * @return - number of edges
     */
    public int getNumberOfEdges() {
        return this.numberOfEdges;
    }

    /**
     *
     * sets number of edges
     */

    public void setNumberOfEdges(int numberOfEdges) {
        this.numberOfEdges = numberOfEdges;
    }




    // TODO: implement the ITreeNode interface
}
