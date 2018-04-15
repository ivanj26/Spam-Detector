public class DriverRegex1 {
	public static void main(String[] args)	{
		Regex r = new Regex();
		r.proccess("IvaM J merupakan mahasiswa iTb jurusan Infrmatika angkatan 2016", "informatika itb ivan");
		//ivan -> I nya jadi uppercase dan typo satu huruf dari n jadi M
		//itb -> T nya jadi uppercase
		//informatika -> typo satu huruf dari o jadi tidak ada
		System.out.println(r.matches());
	}
}
