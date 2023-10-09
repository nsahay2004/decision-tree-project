package sol;

import org.junit.Assert;
import org.junit.Test;
import src.AttributeSelection;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

/**
 * A class to test basic decision tree functionality on a basic training dataset
 */
public class BasicDatasetTest {
    // IMPORTANT: for this filepath to work, make sure the project is open as the top-level directory in IntelliJ
    // (See the first yellow information box in the handout testing section for details)
    String trainingPath = "data/testing_dataset.csv"; // TODO: replace with your own input file
    String targetAttribute = "willDrink"; // TODO: replace with your own target attribute
    TreeGenerator testGenerator;

    String trainingPath2 = "data/elections.csv";
    String targetAttribute2 = "got-votes";


    /**
     * Constructs the decision tree for testing based on the input file and the target attribute.
     */
    @Before
    public void buildTreeForTest() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse(this.trainingPath);
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
        // builds a TreeGenerator object and generates a tree for "foodType"
        this.testGenerator = new TreeGenerator();
//        TODO: Uncomment this once you've implemented generateTree
//        this.testGenerator.generateTree(training, this.targetAttribute);
    }

    /**
     * Tests the expected classification of the "tangerine" row is a fruit
     */
    @Test
    public void testClassification() {
        // makes a new (partial) Row representing the tangerine from the example
        // TODO: make your own rows based on your dataset
        Row virginmargarita = new Row("test row (virginmargarita)");
        virginmargarita.setAttributeValue("type", "mocktail");
        virginmargarita.setAttributeValue("alcohol", "none");
        virginmargarita.setAttributeValue("taste", "sour");
        virginmargarita.setAttributeValue("fruit", "lemon");
        // TODO: make your own assertions based on the expected classifications
        // TODO: Uncomment this once you've implemented getDecision
        // Assert.assertEquals("true", this.testGenerator.getDecision(virginmargarita));
        Row mojito = new Row("test row (mojito)");
        mojito.setAttributeValue("type", "cocktail");
        mojito.setAttributeValue("alcohol", "rum");
        mojito.setAttributeValue("taste", "bitter");
        mojito.setAttributeValue("fruit", "lemon");
        // Assert.assertEquals("true", this.testGenerator.getDecision(mojito));
        Row manhattan = new Row("test row (manhattan)");
        manhattan.setAttributeValue("type", "cocktail");
        manhattan.setAttributeValue("alcohol", "whisky");
        manhattan.setAttributeValue("taste", "bitter");
        manhattan.setAttributeValue("fruit", "lemon");
        // Assert.assertEquals("true", this.testGenerator.getDecision(manhattan))
        Row shirleytemple = new Row("test row (shirleytemple)");
        shirleytemple.setAttributeValue("type", "mocktail");
        shirleytemple.setAttributeValue("alcohol", "none");
        shirleytemple.setAttributeValue("taste", "sour");
        shirleytemple.setAttributeValue("fruit", "orange");
        // Assert.assertEquals("false", this.testGenerator.getDecision(shirleytemple))
        Row tequila = new Row("test row (tequila)");
        tequila.setAttributeValue("type", "shots");
        tequila.setAttributeValue("alcohol", "tequila");
        tequila.setAttributeValue("taste", "bitter");
        tequila.setAttributeValue("fruit", "lemon");
        // Assert.assertEquals("true", this.testGenerator.getDecision(tequila))


        // Assert.assertEquals("true", this.testGenerator.getDecision(tequila))

    }


    @Before
    public void buildTreeForTest2() {
        List<Row> dataObjects2 = DecisionTreeCSVParser.parse(this.trainingPath2);
        List<String> attributeList2 = new ArrayList<>(dataObjects2.get(0).getAttributes());
        Dataset training = new Dataset(attributeList2, dataObjects2, AttributeSelection.ASCENDING_ALPHABETICAL);
        // builds a TreeGenerator object and generates a tree for "foodType"
        this.testGenerator = new TreeGenerator();
//        TODO: Uncomment this once you've implemented generateTree
//        this.testGenerator.generateTree(training, this.targetAttribute2);


    }


    @Test
    public void testClassification2() {
        // makes a new (partial) Row representing the tangerine from the example
        // TODO: make your own rows based on your dataset
        Row candidate1 = new Row("test row (candidate1)");
        candidate1.setAttributeValue("race", "white");
        candidate1.setAttributeValue("gender", "man");
        candidate1.setAttributeValue("age", "old");
        candidate1.setAttributeValue("wealth", "rich");
        candidate1.setAttributeValue("profession", "businessman");
        candidate1.setAttributeValue("education", "Harvard");
        // TODO: make your own assertions based on the expected classifications
        // TODO: Uncomment this once you've implemented getDecision
        // Assert.assertEquals("400-500", this.testGenerator.getDecision(candidate1));
        Row candidate7 = new Row("test row (candidate7)");
        candidate7.setAttributeValue("race", "white");
        candidate7.setAttributeValue("gender", "man");
        candidate7.setAttributeValue("age", "old");
        candidate7.setAttributeValue("wealth", "rich");
        candidate7.setAttributeValue("profession", "politician");
        candidate7.setAttributeValue("education", "Brown");
        // Assert.assertEquals("400-500", this.testGenerator.getDecision(candidate7))


        Row candidate3 = new Row("test row (candidate3)");
        candidate3.setAttributeValue("race", "white");
        candidate3.setAttributeValue("gender", "man");
        candidate3.setAttributeValue("age", "young");
        candidate3.setAttributeValue("wealth", "rich");
        candidate3.setAttributeValue("profession", "politician");
        candidate3.setAttributeValue("education", "Brown");


        // Assert.assertEquals("400-500", this.testGenerator.getDecision(candidate3))
    }
}



