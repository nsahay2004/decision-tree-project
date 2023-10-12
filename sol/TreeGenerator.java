package sol;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sun.jdi.Value;
import src.ITreeGenerator;
import src.ITreeNode;
import src.Row;

/**
 * A class that implements the ITreeGenerator interface used to generate a decision tree
 */
// TODO: Uncomment this once you've implemented the methods in the ITreeGenerator interface!
public class TreeGenerator  implements ITreeGenerator<Dataset>  {
    // TODO: document this field
    private ITreeNode root;
    
    // TODO: Implement methods declared in ITreeGenerator interface!



    public ITreeNode treeHelper(Dataset trainingData,String targetAttribute){
        if (trainingData.getAttributeList().equals(Collections.emptyList())) {
            return new DecisionLeaf(trainingData.getDataObjects().get(0).getAttributeValue(targetAttribute));
        }
        else {
            String attributeSplit = trainingData.getAttributeToSplitOn();
            List<Dataset> partitionedDatasets = trainingData.partition(attributeSplit);
            String defaultValue = trainingData.getDefault(targetAttribute);
            List<ValueEdge>  values = new ArrayList<>();
            for (Dataset i: partitionedDatasets){
                String valueName = i.getDataObjects().get(0).getAttributeValue(attributeSplit);
                ValueEdge m = new ValueEdge(valueName,treeHelper(i,targetAttribute));
                values.add(m);
        }
        return new AttributeNode(attributeSplit,partitionedDatasets.size(),defaultValue,values);
        }

    }

    public void generateTree(Dataset trainingData, String targetAttribute){
        List<String> copyAttributeList = new ArrayList<>();
        Dataset copytrainingData = new Dataset(copyAttributeList,trainingData.getDataObjects(),trainingData.getSelectionType());
        List<String> withoutTargetAttribute = trainingData.getAttributeList();
        int index = withoutTargetAttribute.indexOf(targetAttribute);
        withoutTargetAttribute.remove(index);
        for (String l: withoutTargetAttribute){
            copyAttributeList.add(l);
        }
        this.root = treeHelper(copytrainingData,targetAttribute);
    }

    }