import java.sql.*;
import java.util.ArrayList;

public class AddressService {

    public static boolean addAddress(String street, String city, String state, String zip) {
        String sql = "INSERT INTO addresses (street, city, state, zip) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, street);
            ps.setString(2, city);
            ps.setString(3, state);
            ps.setString(4, zip);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

        public static ArrayList<String[]> getAllAddresses() {
            ArrayList<String[]> list = new ArrayList<>();

            String sql = "SELECT addressID, street, city, state, zip FROM addresses";

            try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    list.add(new String[]{
                        String.valueOf(rs.getInt("addressID")),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip")
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return list;
        }
}
