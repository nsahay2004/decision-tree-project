package sol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertFalse;

import com.sun.source.tree.Tree;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.Before;
import org.junit.runners.MethodSorters;
import src.AttributeSelection;
import src.DecisionTreeCSVParser;
import src.Row;

import org.junit.FixMethodOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * A class containing the tests for methods in the TreeGenerator and Dataset classes
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DecisionTreeTest {
    //TODO: Write more unit and system tests! Some basic guidelines that we will be looking for:
    // 1. Small unit tests on the Dataset class testing the IDataset methods
    // 2. Small unit tests on the TreeGenerator class that test the ITreeGenerator methods
    // 3. Tests on your own small dataset (expect 70% accuracy on testing data, 95% on training data)
    // 4. Test on the villains dataset (expect 70% accuracy on testing data, 95% on training data)
    // 5. Tests on the mushrooms dataset (expect 70% accuracy on testing data, 95% on training data)
    // Feel free to write more unit tests for your own helper methods -- more details can be found in the handout!
    private List<Row> fruitDataObjects;
    private List<String> fruitsAttributeList;
    private Dataset fruitsData;

    private List<Row> electionsDataObjects;
    private List<String> electionsAttributeList;
    private Dataset electData;

    private List<Row> whiteElectDataObjects;

    private List<String> whiteElectionsAttributeList;

    private Dataset whiteElectionsData;



    @Before
    public void setup(){
        String fruitstrainingPath = "data/fruits-and-vegetables.csv";
        fruitDataObjects = DecisionTreeCSVParser.parse(fruitstrainingPath);
        fruitsAttributeList = new ArrayList<>(this.fruitDataObjects.get(0).getAttributes());
        fruitsData = new Dataset(this.fruitsAttributeList, this.fruitDataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);

        String electionsTrainingPath = "data/elections.csv";
        electionsDataObjects = DecisionTreeCSVParser.parse(electionsTrainingPath);
        electionsAttributeList = new ArrayList<>(this.electionsDataObjects.get(0).getAttributes());
        electData = new Dataset(this.electionsAttributeList,this.electionsDataObjects,AttributeSelection.ASCENDING_ALPHABETICAL);

        String whiteElectionsTrainingPath = "data/part1elections.csv";
        whiteElectDataObjects = DecisionTreeCSVParser.parse(whiteElectionsTrainingPath);
        whiteElectionsAttributeList = new ArrayList<>(whiteElectDataObjects.get(0).getAttributes());
        whiteElectionsData = new Dataset(whiteElectionsAttributeList,whiteElectDataObjects,AttributeSelection.ASCENDING_ALPHABETICAL);
    }


    @Test
    public void testDatasetsize(){

        Assert.assertEquals(7, this.fruitsData.size());
;
    }
    @Test
    public void testDatasetgetAttributetoSplitOn(){
        ;
        Assert.assertEquals("calories",this.fruitsData.getAttributeToSplitOn());
    }

    @Test
    public void testDistinctHelper(){
        Assert.assertEquals(3, fruitsData.distinctHelper("color").size());
        Assert.assertEquals(2, fruitsData.distinctHelper("highProtein").size());
    }


    @Test
    public void testIsSameValue(){
        Assert.assertTrue(whiteElectionsData.isSameValue("race"));
        Assert.assertFalse(fruitsData.isSameValue("color"));
    }
    @Test
    public void testDatasetPartition(){

        Assert.assertEquals(2,electData.partition("race").size());
        Assert.assertTrue(electData.partition("race").get(0).isSameValue("race"));
        Assert.assertEquals(1,whiteElectionsData.partition("race").size());

    }

    @Test
    public void testMaxRows(){
        List<Dataset> partitionedDatasets = electData.partition("race");
        Assert.assertEquals(0,electData.getMaxRows(partitionedDatasets));
    }

    @Test
    public void testGetDefault(){
        Assert.assertEquals("white",electData.getDefault("race"));
        Assert.assertEquals("high",fruitsData.getDefault("calories"));
    }


    @Test
    public void testGenerateTree(){
        TreeGenerator fruitsTree = new TreeGenerator();
        fruitsTree.generateTree(fruitsData,"foodType");
//        System.out.println(fruitsTree.getRoot().getName());
    }

    /**
     * This test shows syntax for a basic assertEquals assertion -- can be deleted
     */
    @Test
    public void testAssertEqual() {
        assertEquals(2, 1 + 1);
    }

    /**
     * This test shows syntax for a basic assertTrue assertion -- can be deleted
     */
    @Test
    public void testAssertTrue() {
        assertTrue(true);
    }

    /**
     * This test shows syntax for a basic assertFalse assertion -- can be deleted
     */
    @Test
    public void testAssertFalse() {
        assertFalse(false);
    }


}
