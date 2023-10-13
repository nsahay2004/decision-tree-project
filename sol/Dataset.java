package sol;

import java.util.*;
import java.util.Random;


import src.AttributeSelection;
import src.IDataset;
import src.Row;


/**
 * A class representing a training dataset for the decision tree
 */
// TODO: Uncomment this once you've implemented the methods in the IDataset interface!
public class Dataset  implements IDataset {
    private List<String> attributeList;
    private List<Row> dataObjects;
    private AttributeSelection attributeSelection;

    /**
     * Constructor for a Dataset object
     *
     * @param attributeList      - a list of attributes
     * @param dataObjects        -  a list of rows
     * @param attributeSelection - an enum for which way to select attributes
     */
    public Dataset(List<String> attributeList, List<Row> dataObjects, AttributeSelection attributeSelection) {
        // TODO: implement the constructor! (Hint: take a look at `getAttributeToSplitOn`)
        this.attributeList = new ArrayList<String>(attributeList);
        this.dataObjects = new ArrayList<Row>(dataObjects);
        this.attributeSelection = attributeSelection;
    }

    public List<String> getAttributeList() {
        return this.attributeList;
    }

    public List<Row> getDataObjects() {
        return this.dataObjects;
    }

    public AttributeSelection getSelectionType() {
        return this.attributeSelection;
    }


    public int size() {
        return this.attributeList.size();
    }

    //    TODO: Uncomment this once you've completed the constructor!
    public String getAttributeToSplitOn() {
        switch (this.attributeSelection) {
            case ASCENDING_ALPHABETICAL -> {
                return this.attributeList.stream().sorted().toList().get(0);
            }
            case DESCENDING_ALPHABETICAL -> {
                return this.attributeList.stream().sorted().toList().get(this.attributeList.size() - 1);
            }
            case RANDOM -> {
                // TODO: Implement random attribute selection!
                Random random = new Random();
                int upperBound = this.attributeList.size();
                int randomNum = random.nextInt(upperBound);


                return this.attributeList.stream().sorted().toList().get(randomNum);

            }
        }
        throw new RuntimeException("Non-Exhaustive Switch Case");
    }


    public List<String> distinctHelper(String attributeName) {
        List<String> distinctValues = new ArrayList<>();
        for (Row r : this.dataObjects) {
            String value = r.getAttributeValue(attributeName);
            if (!distinctValues.contains(value)) {
                distinctValues.add(value);
            }

        }
        return distinctValues;
    }

    public Dataset partitionHelper(String selectedAttribute, String targetedValue) {

        List<Row> datasetRows = new ArrayList<Row>();
        for (int i = 0; i < this.dataObjects.size(); i++) {
            if (this.dataObjects.get(i).getAttributeValue(selectedAttribute).equals(targetedValue)) {
                datasetRows.add(this.dataObjects.get(i));
            }
        }
        List<String> copyList = this.copyList(this.attributeList);
        copyList.remove(selectedAttribute);
        Dataset datasetFiltered = new Dataset(copyList, datasetRows, this.attributeSelection);
        return datasetFiltered;
    }


    public List<String> copyList(List<String> attribute) {
        List<String> copyAttributeList = new ArrayList<String>();
        for (String a : attribute) {
            copyAttributeList.add(a);
        }
        return copyAttributeList;
    }

    public List<Dataset> partition(String splitAttribute) {
        List<String> distinctValues = this.distinctHelper(splitAttribute);
        List<Dataset> partitionedDatasets = new ArrayList<>();
        for (String v : distinctValues) {
            Dataset m = partitionHelper(splitAttribute, v);
            partitionedDatasets.add(m);
        }
        return partitionedDatasets;
    }

    public int lengthRows() {
        return this.dataObjects.size();
    }

    public int getMaxRows(List<Dataset> parts) {
        int max = -1;
        int index = 0;

        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).dataObjects.size() >= max) {
                max = parts.get(i).dataObjects.size();
                index = i;


            }
        }
        return index;
    }

    public String getDefault(String targetAttribute) {
        List<Dataset> splitTargetList = this.partition(targetAttribute);
        return splitTargetList.get(getMaxRows(splitTargetList)).dataObjects.get(0).getAttributeValue(targetAttribute);


    }

    public boolean isSameValue(String attribute) {
        String sameAttributeValue = this.dataObjects.get(0).getAttributeValue(attribute);
        String answer = "true";
        for (Row r : this.dataObjects) {
            if (!r.getAttributeValue(attribute) .equals(sameAttributeValue)) {
                ;
                answer = "false";
            }
        }
        if (answer.equals("true")) {
            return true;
        }

        else{
            return false;
        }

    }
}






