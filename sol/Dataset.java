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
    /**
     * list of attributes in the dataset
     */
    private List<String> attributeList;
    /**
     * list of rows in the dataset
     */
    private List<Row> dataObjects;

    /**
     * way of selecting the attribute
     */
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

    /**
     * getter
     * @return - list of strings -> attributes
     */

    @Override
    public List<String> getAttributeList() {
        return this.attributeList;
    }

    /**
     * getter
     * @return - list of rows
     */

    @Override
    public List<Row> getDataObjects() {
        return this.dataObjects;
    }

    /**
     * getter
     * @return - enum
     */
    @Override
    public AttributeSelection getSelectionType() {
        return this.attributeSelection;
    }

    /**
     *
     * @return - number of rows in dataset
     */
    @Override
    public int size() {
        return this.dataObjects.size();
    }

    //    TODO: Uncomment this once you've completed the constructor!

    /**
     * gets the attribute to split on based on selection type
     * @return - string  attribute to split on
     */


    public String getAttributeToSplitOn() {
        switch (this.attributeSelection) {
            case ASCENDING_ALPHABETICAL -> {
                return this.attributeList.stream().sorted().toList().get(0);
            }
            case DESCENDING_ALPHABETICAL -> {
                return this.attributeList.stream().sorted().toList().get(this.attributeList.size() - 1);
            }
            case RANDOM -> {
                Random random = new Random();
                int upperBound = this.attributeList.size();
                int randomNum = random.nextInt(upperBound);
                return this.attributeList.get(randomNum); // Get the attribute at the random index
            }
        }
        throw new RuntimeException("Non-Exhaustive Switch Case");
    }

    /**
     *
     * @param attributeName - name of attribute
     * @return - list of unique attributes as strings
     */
    public  List<String> distinctHelper(String attributeName) {
        List<String> distinctValues = new ArrayList<>();
        for (Row r : this.dataObjects) {
            String value = r.getAttributeValue(attributeName);
            if (!distinctValues.contains(value)) {
                distinctValues.add(value);
            }

        }
        return distinctValues;
    }

    /**
     *
     * @param attribute - list of attributes it wants to copy
     * @return - copied list
     */
    private List<String> copyList(List<String> attribute) {
        List<String> copyAttributeList = new ArrayList<String>();
        for (String a : attribute) {
            copyAttributeList.add(a);
        }
        return copyAttributeList;
    }

    /**
     * method that helps fiilter one dataset to create one element of the larger partition method
     * @param selectedAttribute - attribute to split on
     * @param targetedValue - value for target Attribute
     * @return - filtered dataset
     */
    public Dataset partitionHelper(String selectedAttribute, String targetedValue) {

        List<Row> datasetRows = new ArrayList<Row>();
        for (int i = 0; i < this.size(); i++) {
            if (this.dataObjects.get(i).getAttributeValue(selectedAttribute).equals(targetedValue)) {
                datasetRows.add(this.dataObjects.get(i));
            }
        }
        List<String> copyList = this.copyList(this.attributeList);
        copyList.remove(selectedAttribute);
        Dataset datasetFiltered = new Dataset(copyList, datasetRows, this.attributeSelection);
        return datasetFiltered;
    }


    /**
     * method that creates a list of partitioned datsets that are the result of the main dataset being split
     * on one attribute
     * @param splitAttribute - attribute to split on
     * @return - a list of datasets
     */
    public List<Dataset> partition(String splitAttribute) {
        List<String> distinctValues = this.distinctHelper(splitAttribute);
        List<Dataset> partitionedDatasets = new ArrayList<>();
        for (String v : distinctValues) {
            Dataset m = this.partitionHelper(splitAttribute, v);
            partitionedDatasets.add(m);
        }
        return partitionedDatasets;
    }


    /**
     * gets the dataset in a list with the maximum number of rows
     * @param parts - list of datasets
     * @return - index of the dataset with max rows
     */
    public int getMaxRows(List<Dataset> parts) {
        int max = -1;
        int index = 0;

        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).size() >= max) {
                max = parts.get(i).size();
                index = i;


            }
        }
        return index;
    }

    /**
     * Method that obtains the default of a particular attribute in a dataset based on the outcome of the majority of rows
     * @param targetAttribute - attribute that we want to decide the default for
     * @return - default string
     */
    public String getDefault(String targetAttribute) {
        List<Dataset> splitTargetList = this.partition(targetAttribute);
        return splitTargetList.get(this.getMaxRows(splitTargetList)).dataObjects.get(0).getAttributeValue(targetAttribute);


    }

    /**
     * method that determines if values of an attribute are the same for all the rows in the dataset
     * @param attribute - checks the values of this attrbute
     * @return - true or false if they are same or not
     */
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






