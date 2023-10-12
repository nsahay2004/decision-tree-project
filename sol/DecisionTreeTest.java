package sol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import src.AttributeSelection;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * A class containing the tests for methods in the TreeGenerator and Dataset classes
 */
public class DecisionTreeTest {
    //TODO: Write more unit and system tests! Some basic guidelines that we will be looking for:
    // 1. Small unit tests on the Dataset class testing the IDataset methods
    // 2. Small unit tests on the TreeGenerator class that test the ITreeGenerator methods
    // 3. Tests on your own small dataset (expect 70% accuracy on testing data, 95% on training data)
    // 4. Test on the villains dataset (expect 70% accuracy on testing data, 95% on training data)
    // 5. Tests on the mushrooms dataset (expect 70% accuracy on testing data, 95% on training data)
    // Feel free to write more unit tests for your own helper methods -- more details can be found in the handout!

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

    @Test
    public void testDatasetsize(){
        String fruitstrainingPath = "data/fruits-and-vegetables.csv";
        List<Row> dataObjects = DecisionTreeCSVParser.parse(fruitstrainingPath);
        List<String> fruitsAttributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset fruits = new Dataset(fruitsAttributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
        Assert.assertEquals(4, fruits.size());
;
    }
    @Test
    public void testDatasetgetAttributetoSplitOn(){
        String fruitstrainingPath = "data/fruits-and-vegetables.csv";
        List<Row> dataObjects = DecisionTreeCSVParser.parse(fruitstrainingPath);
        List<String> fruitsAttributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset fruits = new Dataset(fruitsAttributeList, dataObjects, AttributeSelection.ASCENDING_ALPHABETICAL);
        Assert.assertEquals("calories",fruits.getAttributeToSplitOn());
    }

}
