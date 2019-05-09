package ik222hy_assignment3;

public class SweID {

	public static String getFirstPart(String Id) {

		return Id.substring(0, 6);
	}

	public static String getSecondPart(String Id) {

		return Id.substring(7, Id.length());
	}

	public static boolean isFemale(String Id) {
		String str = "" + Id.charAt(Id.length() - 1);
		return Integer.parseInt(str) % 2 == 0;
	}

	public static boolean isMale(String Id) {
		String str = "" + Id.charAt(Id.length() - 1);
		return Integer.parseInt(str) % 2 == 1;
	}

	public static boolean areEqual(String Id, String corespondID) {
		if (Id.equals(corespondID)) {
			return true;
		}
		return false;
	}

	public static boolean isCorrect(String Id) {
		String SweId = getFirstPart(Id) + getSecondPart(Id);
		int[] arrId = new int[SweId.length()];
		for (int i = 0; i < SweId.length(); i++) {
			String sId = "" + SweId.charAt(i);
			int convert = Integer.parseInt(sId);
			arrId[i] = convert;
		}

		if (SweId.length() != 10) {
			return false;
		}
		for (int i = 0; i < arrId.length; i++) { // negative value//

			if (arrId[i] < 0) {
				return false;
			}
		}
		if (arrId[2] == 0 && arrId[3] > 9) { // check MM (value 9)
			return false;
		}
		if (arrId[2] > 1) { // max value 1st digit1

			return false;
		}
		if (arrId[2] == 1 && arrId[3] > 2) { // digit1 max=val2
			return false;
		}
		if (arrId[4] > 3) {

			return false;
		}
		if (arrId[4] < 3 && arrId[5] > 9) { // second digit is 9//
			return false;
		}
		if (arrId[4] == 3 && arrId[5] > 1) { // 31 days
			return false;
		}

		int sum = 0;
		for (int i = 0; i <= arrId.length - 2; i += 2) { // sum odd digits//
			int temp = ((arrId[i] * 2) % 10) + ((arrId[i] * 2) / 10);
			sum = sum + temp;
		}

		for (int i = 1; i <= arrId.length - 2; i += 2) {
			sum = sum + arrId[i]; // sums//
		}

		int checkSum = 10 - (sum % 10); // checksum procedure//
		if (checkSum == 10) {
			checkSum = checkSum % 10;
		}
		if (arrId[arrId.length - 1] == checkSum) { // last digit=checksum

			return true;
		} else {
			return false;

		}

	}

}
