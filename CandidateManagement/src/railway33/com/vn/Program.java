package railway33.com.vn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class Program {

	public static List<Candidate> listCandidate = new ArrayList<>();
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Get connection ... ");
		Connection conn = Program.getMyConnection();
		registerCandidate(conn);
		getCandidate(conn);
		dispalyInfoCandidate();
		
		conn.close();
	}
	
	public static Connection getMyConnection() throws SQLException, ClassNotFoundException {
		return MySQLConnUtils.getMySQLConnection();
	}

	private static void getCandidate(Connection connection) throws SQLException {
		String sql = "SELECT id, firstName, lastName, email, phone, expInYear, proSkill, graduationRank FROM candidate ";
		PreparedStatement pstm = connection.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
			int id = rs.getInt("id");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String email = rs.getString("email");
			String phone = rs.getString("phone");
			String proSkill = rs.getString("proSkill");
			String graduationRank = rs.getString("graduationRank");
			int expInYear = rs.getInt("expInYear");
			if(proSkill == null) {
				FresherCandidate fresher = new FresherCandidate();
				fresher.setId(id);
				fresher.setEmail(email);
				fresher.setFirstName(firstName);
				fresher.setLastName(lastName);
				fresher.setPhone(phone);
				fresher.setGraduationRank(GraduationRank.valueOf(graduationRank));
				listCandidate.add(fresher);
			} else {
				ExperienceCandidate experienceCandidate = new ExperienceCandidate();
				experienceCandidate.setId(id);
				experienceCandidate.setEmail(email);
				experienceCandidate.setFirstName(firstName);
				experienceCandidate.setLastName(lastName);
				experienceCandidate.setPhone(phone);
				experienceCandidate.setExpInYear(expInYear);
				experienceCandidate.setProSkill(proSkill);
				
				listCandidate.add(experienceCandidate);
			}
		}
	}
	
	private static void registerCandidate(Connection connection) throws SQLException {
		// Step 3: Create a statement object
		String sql = "INSERT INTO candidate(firstName, lastName, email, phone, expInYear, proSkill, graduationRank) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		// input from scanner
		Scanner scanner = new Scanner(System.in);
		System.out.println("Moi ban firstname Ung vien");
		String firstName = scanner.nextLine();
		System.out.println("Moi ban lastName Ung vien");
		String lastName = scanner.nextLine();
		System.out.println("Moi ban phone Ung vien");
		String phone = scanner.nextLine();
		System.out.println("Moi ban email Ung vien");
		String email = scanner.nextLine();
		
		preparedStatement.setString(1, firstName);
		preparedStatement.setString(2, lastName);
		while(!email.contains("@vti.com.vn")) {
			System.out.println("Moi ban email Ung vien");
			email = scanner.nextLine();
		}
		preparedStatement.setString(3, email);
		while(phone.length() < 9 || phone.length() > 12) {
			System.out.println("Moi ban phone Ung vien 9 - 12 ky tu");
			phone = scanner.nextLine();
		}
		preparedStatement.setString(4, phone);
		
		System.out.println("Moi ban chon loai Ung vien");
		System.out.println("1. Ung vien co kinh nghiem");
		System.out.println("2. Fresher");
		int chon = Integer.parseInt(scanner.next());
		switch (chon) {
		case 1:
			Scanner sc = new Scanner(System.in);
			System.out.println("Moi ban proSkill Ung vien");
			String proSkill = sc.nextLine();
			System.out.println("Moi ban expInYear Ung vien");
			int expInYear = Integer.parseInt(sc.next());
			while(expInYear < 0 || expInYear > 10) {
				System.out.println("Moi ban expInYear Ung vien");
				expInYear = Integer.parseInt(sc.next());
			}
			preparedStatement.setInt(5, expInYear);
			preparedStatement.setString(6, proSkill);
			preparedStatement.setString(7, null);
			preparedStatement.executeUpdate();
			// Step 5: Handling Result Set
			System.out.println("Resister successfully...");
			break;
		case 2:
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Moi ban graduationRank Ung vien");
			String graduationRank = sc2.nextLine();
			preparedStatement.setInt(5, 0);
			preparedStatement.setString(6, null);
			while(("Excellence").equals(graduationRank)  || ("Good").equals(graduationRank)
					|| ("Fair").equals(graduationRank) || ("Poor").equals(graduationRank)) {
				System.out.println("Moi ban graduationRank Ung vien");
				graduationRank = sc2.nextLine();
			}
			preparedStatement.setString(7, graduationRank);
			preparedStatement.executeUpdate();
			// Step 5: Handling Result Set
			System.out.println("Resister successfully...");
			break;
		default:
			break;
		}
		
	}
	
	public static void dispalyInfoCandidate() {
		for (Candidate candidate : listCandidate) {
			System.out.println(candidate);
		}
	}

}
