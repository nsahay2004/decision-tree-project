package sol;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import src.ITreeGenerator;
import src.ITreeNode;
import src.Row;

/**
 * A class that implements the ITreeGenerator interface used to generate a decision tree
 */
// TODO: Uncomment this once you've implemented the methods in the ITreeGenerator interface!
public class TreeGenerator /* implements ITreeGenerator<Dataset> */ {
    // TODO: document this field
    private ITreeNode root;
    
    // TODO: Implement methods declared in ITreeGenerator interface!

    public ITreeNode treeHelper(Dataset trainingData,String targetAttribute){
        String attributeSplit = trainingData.getAttributeToSplitOn();
        List<Dataset> partitionedDatasets = trainingData.partition(attributeSplit);
        List<ValueEdge>  Values = new ArrayList<>();
        for (Dataset i: partitionedDatasets){
            if (i.getAttributeList().equals(Collections.emptyList()){
                return DecisionLeaf(i.getDataObjects().get(0).getAttributeValue(targetAttribute);
            }
            else{
        }
    }

}
