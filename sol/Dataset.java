package sol;

import java.util.Random;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

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
        this.attributeList = new ArrayList<String>();
        this.dataObjects = dataObjects;
        this.attributeSelection = attributeSelection;
    }

    public List<String> getAttributeList(){
        return this.attributeList;
    }

    public List<Row> getDataObjects(){
        return this.dataObjects;
    }

    public AttributeSelection getSelectionType(){
        return this.attributeSelection;
    }

    public int size(){
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

}
