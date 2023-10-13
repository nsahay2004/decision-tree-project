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

    /**
     * recursive helper method for generateTree
     *
     * @param trainingData - data we want to train on to strucure the tree
     * @param targetAttribute - attribute that's at the end of the tree as the outcome we want
     * @return - a tree based on the data
     */

    public ITreeNode treeHelper(Dataset trainingData, String targetAttribute) {
        if (trainingData.getAttributeList().isEmpty() || trainingData.isSameValue(targetAttribute)) {
            return new DecisionLeaf(trainingData.getDataObjects().get(0).getAttributeValue(targetAttribute));
        } else {
            String attributeSplit = trainingData.getAttributeToSplitOn();
            if (attributeSplit == null) {
                // Handle the case where no attribute is available to split
                return new DecisionLeaf(trainingData.getDefault(targetAttribute));
            }
            List<Dataset> partitionedDatasets = trainingData.partition(attributeSplit);
            String defaultValue = trainingData.getDefault(targetAttribute);
            List<ValueEdge> values = new ArrayList<>();
            for (Dataset i : partitionedDatasets) {
                String valueName = i.getDataObjects().get(0).getAttributeValue(attributeSplit);
                ValueEdge m = new ValueEdge(valueName, this.treeHelper(i, targetAttribute));
                values.add(m);
            }
            return new AttributeNode(attributeSplit, partitionedDatasets.size(), defaultValue, values);
        }
    }

    /**
     * method that generates a tree
     * @param trainingData -   the dataset to train on
     * @param targetAttribute-   the attribute to predict
     */

    @Override
    public void generateTree(Dataset trainingData, String targetAttribute) {
        List<String> copyAttributeList = new ArrayList<>(trainingData.getAttributeList());
        copyAttributeList.remove(targetAttribute); // Remove the target attribute
        Dataset copyTrainingData = new Dataset(copyAttributeList, trainingData.getDataObjects(), trainingData.getSelectionType());
        this.root = this.treeHelper(copyTrainingData, targetAttribute);

    }

    /**
     * method that traverses the tree and returns a decision
     * @param forDatum -  the datum to lookup a decision for
     * @return - decision as a string
     */
    @Override
    public String getDecision(Row forDatum) {
        return this.root.getDecision(forDatum);
    }

    /**
     *
     * @return - root of the tree
     */
    public ITreeNode getRoot() {
        return this.root;
    }

    /**
     * method that determines if a tree is a decision leaf
     * @param trainingData - data tree is based on
     * @param targetAttribute - attribute the tree predicts
     * @return - true or false as to whether the tree is a decision leaf
     */
    public boolean isDecisionLeaf(Dataset trainingData, String targetAttribute){
        if ((trainingData.isSameValue(targetAttribute))){
            return true;
        }
        else{
            return false;
        }
    }

    }