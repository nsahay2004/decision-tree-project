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
    /**
     * all the names as fields
     */
    private List<Row> fruitDataObjects;
    private List<String> fruitsAttributeList;
    private Dataset fruitsData;

    private List<Row> electionsDataObjects;
    private List<String> electionsAttributeList;
    private Dataset electData;

    private List<Row> whiteElectDataObjects;

    private List<String> whiteElectionsAttributeList;

    private Dataset whiteElectionsData;

    private List<Row> drinksDataObjects;

    private List<String> drinksAttributeList;

    private Dataset drinksData;

    private List<Row> oneRowDataObjects;

    private List<String> oneRowAttributeList;

    private Dataset oneRowData;

    private List<Row> multipleRowsDataObjects;

    private List<String> multipleRowsAttributeList;

    private Dataset multipleRowsData;

    private TreeGenerator fruitsTree;

    private TreeGenerator electionsTree;

    private TreeGenerator drinksTree;

    /**
     * setup for all the data and trees used
     */

    @Before
    public void setup(){
        /**
         * fruits and vegetables data and tree
         */
        String fruitstrainingPath = "data/fruits-and-vegetables.csv";
        this.fruitDataObjects = DecisionTreeCSVParser.parse(fruitstrainingPath);
        this.fruitsAttributeList = new ArrayList<>(this.fruitDataObjects.get(0).getAttributes());
        this.fruitsData = new Dataset(this.fruitsAttributeList, this.fruitDataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
        this.fruitsTree = new TreeGenerator();
        this.fruitsTree.generateTree(this.fruitsData, "foodType");

        /**
         * small dataset for testng and tree -> elections
         */

        String electionsTrainingPath = "data/elections.csv";
        this.electionsDataObjects = DecisionTreeCSVParser.parse(electionsTrainingPath);
        this.electionsAttributeList = new ArrayList<>(this.electionsDataObjects.get(0).getAttributes());
        this.electData = new Dataset(this.electionsAttributeList,this.electionsDataObjects,AttributeSelection.ASCENDING_ALPHABETICAL);
        this.electionsTree = new TreeGenerator();
        this.electionsTree.generateTree(this.electData,"got-votes");

        /**
         * smaller dataset for testing -> whiteelections
         */

        String whiteElectionsTrainingPath = "data/part1elections.csv";
        this.whiteElectDataObjects = DecisionTreeCSVParser.parse(whiteElectionsTrainingPath);
        this.whiteElectionsAttributeList = new ArrayList<>(this.whiteElectDataObjects.get(0).getAttributes());
        this.whiteElectionsData = new Dataset(this.whiteElectionsAttributeList,this.whiteElectDataObjects,AttributeSelection.ASCENDING_ALPHABETICAL);

        /**
         * small dataset for testng and tree -> drinks
         */

        String drinksTrainingPath = "data/testing_dataset.csv";
        this.drinksDataObjects = DecisionTreeCSVParser.parse(drinksTrainingPath);
        this.drinksAttributeList = new ArrayList<>(this.drinksDataObjects.get(0).getAttributes());
        this.drinksData = new Dataset(this.drinksAttributeList,this.drinksDataObjects,AttributeSelection.DESCENDING_ALPHABETICAL);
        this.drinksTree = new TreeGenerator();
        this.drinksTree.generateTree(this.drinksData,"willdrink");

        /**
         * one Row edge case data
         */

        String oneRowTrainingPath = "data/oneRow.csv";
        this.oneRowDataObjects = DecisionTreeCSVParser.parse(oneRowTrainingPath);
        this.oneRowAttributeList = new ArrayList<>(this.oneRowDataObjects.get(0).getAttributes());
        this.oneRowData = new Dataset(this.oneRowAttributeList,this.oneRowDataObjects,AttributeSelection.DESCENDING_ALPHABETICAL);

        /**
         * multiple Rows with same value for target attribute edge case data
         */

        String multipleRowsTrainingPath = "data/multipleRowsSame.csv";
        this.multipleRowsDataObjects = DecisionTreeCSVParser.parse(multipleRowsTrainingPath);
        this.multipleRowsAttributeList= new ArrayList<>(this.multipleRowsDataObjects.get(0).getAttributes());
        this.multipleRowsData = new Dataset(this.multipleRowsAttributeList,this.multipleRowsDataObjects,AttributeSelection.DESCENDING_ALPHABETICAL);


    }

    /**
     * test for size method of dataset
     */
    @Test
    public void testDatasetsize(){

        Assert.assertEquals(7, this.fruitsData.size());
;
    }

    /**
     * test for getAttributetoSplitOn
     */
    @Test
    public void testDatasetgetAttributetoSplitOn(){
        ;
        Assert.assertEquals("calories",this.fruitsData.getAttributeToSplitOn());
    }

    /**
     * test for distinctHelper
     */

    @Test
    public void testDistinctHelper(){
        Assert.assertEquals(3, fruitsData.distinctHelper("color").size());
        Assert.assertEquals(2, fruitsData.distinctHelper("highProtein").size());
    }

    /**
     * test for isSameValue
     */
    @Test
    public void testIsSameValue(){
        Assert.assertTrue(whiteElectionsData.isSameValue("race"));
        Assert.assertFalse(fruitsData.isSameValue("color"));
    }

    /**
     * test for partition method
     */
    @Test
    public void testDatasetPartition(){

        Assert.assertEquals(2,electData.partition("race").size());
        Assert.assertTrue(electData.partition("race").get(0).isSameValue("race"));
        Assert.assertEquals(1,whiteElectionsData.partition("race").size());

    }

    /**
     * test for getMaxRows helper method
     */
    @Test
    public void testMaxRows(){
        List<Dataset> partitionedDatasets = this.electData.partition("race");
        Assert.assertEquals(0,this.electData.getMaxRows(partitionedDatasets));
    }

    /**
     * test for getDefault
     */
    @Test
    public void testGetDefault(){
        Assert.assertEquals("white",this.electData.getDefault("race"));
        Assert.assertEquals("high",this.fruitsData.getDefault("calories"));
    }

    /**
     * test for standard get decision
     */
    @Test
    public void testGetDecision(){

        Row tangerine = new Row("tangerine");
        tangerine.setAttributeValue("color", "orange");
        tangerine.setAttributeValue("highProtein", "false");
        tangerine.setAttributeValue("calories", "high");
        Assert.assertEquals("fruit",this.fruitsTree.getDecision(tangerine));

        Row manhattan= new Row("manhattan");
        manhattan.setAttributeValue("type","cocktail");
        manhattan.setAttributeValue("taste","bitter");
        manhattan.setAttributeValue("fruit","lemon");
        Assert.assertEquals("true",this.drinksTree.getDecision(manhattan));

        Row candidate1 = new Row("candidate)");
        candidate1.setAttributeValue("race", "white");
        candidate1.setAttributeValue("gender", "man");
        candidate1.setAttributeValue("age", "old");
        candidate1.setAttributeValue("wealth", "rich");
        candidate1.setAttributeValue("profession", "businessman");
        candidate1.setAttributeValue("education", "Harvard");
        Assert.assertEquals("400-500",this.electionsTree.getDecision(candidate1));


    }

    /**
     * test for value not in the list, where default is invoked
     * edge case
     */
    @Test
    public void getDecisionDefaultCases(){
        Row broccoli = new Row("brocolli");
        broccoli.setAttributeValue("color", "green");
        broccoli.setAttributeValue("highProtein", "true");
        broccoli.setAttributeValue("calories", "medium");
        Assert.assertEquals("vegetable",this.fruitsTree.getDecision(broccoli));


        Row brownCandidate = new Row("brownCandidate");
        brownCandidate.setAttributeValue("race", "white");
        brownCandidate.setAttributeValue("gender", "man");
        brownCandidate.setAttributeValue("age", "old");
        brownCandidate.setAttributeValue("wealth", "rich");
        brownCandidate.setAttributeValue("profession", "businessman");
        brownCandidate.setAttributeValue("education", "Brown");
        Assert.assertEquals("400-500", this.electionsTree.getDecision(brownCandidate));


        Row sangria = new Row("sangria");
        sangria.setAttributeValue("type","mocktail");
        sangria.setAttributeValue("taste","bitter");
        sangria.setAttributeValue("fruit","apple");
        Assert.assertEquals("true",this.drinksTree.getDecision(sangria));
    }

    /**
     * test for edge case - when data is one row
     */
    @Test
    public void oneRowDataTree(){
        TreeGenerator oneRowTree = new TreeGenerator();
        oneRowTree.generateTree(this.oneRowData,"willdrink");
        Assert.assertTrue(oneRowTree.isDecisionLeaf(this.oneRowData,"willdrink"));

    }

    /**
     * test for edge case - when all the rows in the data have the same value for target attribute
     */

    @Test
    public void multipleRowsSameValue(){
        TreeGenerator multipleRowsTree = new TreeGenerator();
        multipleRowsTree.generateTree(this.multipleRowsData,"got-votes");
        Assert.assertTrue(multipleRowsTree.isDecisionLeaf(this.multipleRowsData,"got-votes"));


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
