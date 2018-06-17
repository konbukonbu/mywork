package y2018.exam1;

public abstract class Animal {

	public void method() {
		System.out.println("this.cryを呼ぶで");
		cry();
	}

	protected void cry() {
		System.out.println("アニマルやねん");
	}
}
