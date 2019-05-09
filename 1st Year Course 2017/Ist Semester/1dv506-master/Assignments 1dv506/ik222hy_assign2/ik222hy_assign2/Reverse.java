package ik222hy_assign2;

public class Reverse {
	public static void main(String[] args) {
		char[] text = { '!', 'y', 's', 'a', 'E', ' ', 's', 'a', 'w', ' ', 's', 'i', 'h', 'T' };
		for (char element : text) {
			System.out.print(element);
		}
		System.out.println("");
		for (int i = text.length - 1; i >= 0; i--) {
			System.out.print(text[i]);
		}
	}
}
