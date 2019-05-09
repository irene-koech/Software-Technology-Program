package fixtures;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import api.*;

@RunWith(Suite.class)
@SuiteClasses({RemoveBookResourceTest.class,GetBooksResourceTest.class})
public class apiSuit{

}
