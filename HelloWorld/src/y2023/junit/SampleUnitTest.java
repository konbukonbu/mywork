package y2023.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import mockit.Mock;
import mockit.MockUp;

public class SampleUnitTest {

  @Test
  public void test_addMethod() throws Exception {
    long a = 123;
    long b = 234;
    long acturalValue = SampleUtil.addMethod(a, b);
    long expectedVallue = 357;
    System.out.println(acturalValue);
    assertEquals(expectedVallue, acturalValue);
  }

  /**
   * @see https://qiita.com/opengl-8080/items/a49d4dae9067413ccdd6
   * 
   */
  @Test
  public void test_Mock_Cry() throws Exception {
    SampleUtil.cry();

    new MockUp<SampleUtil>() {
      @Mock
      public void cry() {
        System.out.println("ニャーニャー!!");
      }
    };

    SampleUtil.cry();
  }

}
