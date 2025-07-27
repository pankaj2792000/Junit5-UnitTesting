package suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
//@SelectClasses({ClassATest.class, ClassBTest.class,ClassCTest.class})
//@SelectPackages("suite")
@SelectPackages({"annotation","suite"})
public class TestSuite {


}
