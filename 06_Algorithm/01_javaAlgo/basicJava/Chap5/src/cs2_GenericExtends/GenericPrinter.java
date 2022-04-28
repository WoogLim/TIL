package cs2_GenericExtends;

// 제너릭 타입을 사용하겠다는 의미로 타입 파라미터 T이용
// 나중에 쓸때(인스턴스화시) <> 타입을 지정하면 된다. 모든 타입이 올 수 있으므로 T로 정의한다.
public class GenericPrinter<T extends Material> {
	private T material;

	public T getMaterial() {
		return material;
	}

	public void setMaterial(T material) {
		this.material = material;
	}
	
	public String toString() {
		return material.toString();
	}
}
